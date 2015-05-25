package com.wanttospeak.items;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.givemepass.wanttospeak.R;
import com.j256.ormlite.dao.Dao;
import com.wanttospeak.cache.DataHelper;
import com.wanttospeak.cache.MyItemList;
import com.wanttospeak.dao.DatabaseHelper;
import com.wanttospeak.dao.ItemDao;
import com.wanttospeak.dialog.CommonDialog;
import com.wanttospeak.util.IdGenerator;
import com.wanttospeak.util.NoticeCenter;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class ItemMakerDialog extends CommonDialog {
    public static final int REQUEST_TAKE_PHOTO = 1;
    public static final int RESULT_LOAD_IMAGE = 2;
    private Activity activity;
    private File photoFile;
    private File recordFile;
    private ItemDao item;
    private MediaRecorder mediaRecorder;

    public ItemMakerDialog(Activity activity) {
        super(activity);
        this.activity = activity;
        setContextView(R.layout.add_new_item);

        item = new ItemDao();

        setupRecordFile();
        setupPicturePicker();
        setupRecorder();
        setupSaveButton();

        NoticeCenter.getInstance().setOnNewPictureReady(new NoticeCenter.OnNewPictureReadyListener() {
            @Override
            public void notifyNewPictureReady() {
                notifiNewPictureReady();
            }
        });
        NoticeCenter.getInstance().setOnGalleryPictureReady(new NoticeCenter.OnGalleryPictureReadyListener() {
            @Override
            public void notifyGalleryPictureReady(Uri uri) {
                notifiGalleryPictureReady(uri);
            }
        });

    }

    private void setupRecorder() {
        Button recordButton = (Button) findViewById(R.id.bt_record);
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.isSelected()) {
                    v.setBackgroundResource(R.drawable.record);
                    stopRecord();
                    v.setSelected(false);
                } else {
                    v.setBackgroundResource(R.drawable.finish);
                    doRecording();
                    v.setSelected(true);
                }
            }
        });
        Button deleteButton = (Button) findViewById(R.id.bt_delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recordFile != null) {
                    if (recordFile.delete()) {
                        recordFile = null;
                        Toast.makeText(activity, "刪除成功，請重新錄音 :)", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(activity, "刪除失敗，請再試一次 :(", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        Button runButton = (Button) findViewById(R.id.bt_run);
        runButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recordFile != null) {
                    playRecord();
                } else {
                    Toast.makeText(activity, "噢喔！你忘記錄音了喔 :錄音擋已存在", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void dismiss() {
//        NoticeCenter.getInstance().setOnGalleryPictureReady(null);
//        NoticeCenter.getInstance().setOnNewPictureReady(null);
        super.dismiss();
    }

    private void setupSaveButton() {
        final Button saveButton = (Button) findViewById(R.id.add_item_button);
        final EditText nameTextView = (EditText) findViewById(R.id.add_item_name);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(item.getPhotoPath())){
                    Toast.makeText(activity, "你忘記拍照囉 :)", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(item.getRecordPath())) {
                    Toast.makeText(activity, "你忘記錄音囉 :)", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(nameTextView.getText().toString())) {
                    Toast.makeText(activity, "你忘記輸入名稱囉 :)", Toast.LENGTH_SHORT).show();
                }else {
                    // save item name
                    item.setItemName(nameTextView.getText().toString());
                    //save item to cache
                    MyItemList.addPersonalItem(DataHelper.getCurrentPersonId(), item);
                    // save item to db
                    try {
                        Dao<ItemDao, String> mItemDao = DatabaseHelper.getInstance().getItemDao();
                        mItemDao.create(item);
                    } catch (SQLException e) {
                        Toast.makeText(activity, activity.getString(R.string.add_item_fail),
                                Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                    NoticeCenter.getInstance().notifySaveNewItem();
                    Toast.makeText(activity, activity.getString(R.string.add_item_success),
                            Toast.LENGTH_SHORT).show();
                    dismiss();
                }
            }
        });
    }

    private void setupPicturePicker() {
        RelativeLayout pictureSelector = (RelativeLayout) findViewById(R.id.rl_avatar_layout);
        pictureSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle(activity.getString(R.string.picture_chooser_name))
                        .setItems(new String[]{activity.getString(R.string.gallery), activity.getString(R.string.take_picture)}, new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        dispatchChooseGalleryIntent();
                                        break;
                                    case 1:
                                        dispatchTakePictureIntent();
                                        break;
                                }
                            }
                        });
                builder.show();
            }
        });
    }

    public void notifiNewPictureReady() {
        item.setPhotoPath(photoFile.getAbsolutePath());
        showPhotoView();
    }

    public void notifiGalleryPictureReady(Uri uri) {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = activity.getContentResolver().query(uri, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        item.setPhotoPath(cursor.getString(columnIndex));
        cursor.close();
        showPhotoView();
    }

    private void showPhotoView() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap photo = BitmapFactory.decodeFile(item.getPhotoPath(), options);
        ImageView photoView = (ImageView) findViewById(R.id.add_item_picture);
        photoView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        photoView.setImageBitmap(photo);
    }

    private void notifiRecordReady() {
        item.setRecordPath(recordFile.getAbsolutePath());
    }

    private void setupRecordFile() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            recordFile = createRecordFile();
        } catch (IOException e) {
            Log.e(activity.getPackageName(), "Failed to create record file.");
        }

        mediaRecorder.setOutputFile(recordFile.getAbsolutePath());
    }

    private void doRecording() {
        if(mediaRecorder == null) setupRecordFile();
        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
        }catch (IOException e) {
            Log.e(activity.getPackageName(), e.getMessage(), e);
        }
    }

    private void stopRecord() {
        mediaRecorder.stop();
        mediaRecorder.release();
        notifiRecordReady();
    }

    private void playRecord() {
        final MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(recordFile.getAbsolutePath());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            try {
                photoFile = createPhotoFile();
            } catch (IOException ex) {
                Log.e(activity.getPackageName(), "Failed to create photo file.");
            }

            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                activity.startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private void dispatchChooseGalleryIntent() {
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    private File createPhotoFile() throws IOException {
        String imageFileName = "JPEG_" + IdGenerator.createId();
        File storageDir = new File(Environment.getExternalStorageDirectory() + "/WantToSpeak/Picture");
        if (!storageDir.exists()) {
            if(!storageDir.mkdirs()) {
                Log.e(activity.getPackageName(), "Failed to create photo folder.");
            }
        }

        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }

    private File createRecordFile() throws IOException {
        String imageFileName = "RECORD_" + IdGenerator.createId();
        File storageDir = new File(Environment.getExternalStorageDirectory() + "/WantToSpeak/Record");
        if (!storageDir.exists()) {
            if(!storageDir.mkdirs()) {
                Log.e(activity.getPackageName(), "Failed to create record folder.");
            }
        }

        return File.createTempFile(imageFileName, ".3gp", storageDir);
    }
}
