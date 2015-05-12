package com.wanttospeak.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.wanttospeak.items.Item;

import com.example.givemepass.wanttospeak.R;
import com.wanttospeak.util.IdGenerator;

import java.io.File;
import java.io.IOException;

public class ItemMakerDialog extends CommonDialog {
    public static final int REQUEST_TAKE_PHOTO = 1;
    public static final int RESULT_LOAD_IMAGE = 2;
    private Activity activity;
    private File photoFile;
    private File recordFile;
    private Item item;
    private MediaRecorder mediaRecorder;

    public ItemMakerDialog(Activity activity) {
        super(activity);
        this.activity = activity;
        setContextView(R.layout.add_new_item);

        item = new Item();
        mediaRecorder = new MediaRecorder();

        setupPicturePicker();
        setupRecorder();
        setupSaveButton();
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
                    if(recordFile == null) {
                        v.setBackgroundResource(R.drawable.finish);
                        dispatchRecordIntent();
                        v.setSelected(true);
                    }else {
                        Toast.makeText(activity, "record exist! u can't try again!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        Button runButton = (Button) findViewById(R.id.bt_run);
        runButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playRecord();
            }
        });
    }

    private void setupSaveButton() {
        Button saveButton = (Button) findViewById(R.id.add_item_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save item to itemlists
            }
        });
    }

    private void setupPicturePicker() {
        FrameLayout view = (FrameLayout) findViewById(R.id.avatar_layout);
        view.setOnClickListener(new View.OnClickListener() {
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
    }

    public void notifiGalleryPictureReady(Uri uri) {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = activity.getContentResolver().query(uri, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        item.setPhotoPath(cursor.getString(columnIndex));
        cursor.close();
    }

    private void notifiRecordReady() {
        item.setRecordPath(recordFile.getAbsolutePath());
    }

    private void dispatchRecordIntent() {
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            recordFile = createRecordFile();
        } catch (IOException e) {
            Log.e(activity.getPackageName(), "Failed to create record file.");
        }

        mediaRecorder.setOutputFile(this.recordFile.getAbsolutePath());
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
    }

    private void playRecord() {
        if(recordFile == null) return;
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
            storageDir.mkdirs();
        }

        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }

    private File createRecordFile() throws IOException {
        String imageFileName = "RECORD_" + IdGenerator.createId();
        File storageDir = new File(Environment.getExternalStorageDirectory() + "/WantToSpeak/Record");
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }

        return File.createTempFile(imageFileName, ".3gp", storageDir);
    }
}
