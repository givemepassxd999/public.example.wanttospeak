package com.wanttospeak.dialog;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.example.givemepass.wanttospeak.R;
import com.wanttospeak.util.IdGenerator;

import java.io.File;
import java.io.IOException;

public class ItemMakerDialog extends CommonDialog {
    public static final int REQUEST_TAKE_PHOTO = 1;
    private Activity activity;
    private File photoFile;

    public ItemMakerDialog(Activity activity) {
        super(activity);
        this.activity = activity;

        setContextView(R.layout.dialog_data);
        dispatchTakePictureIntent();
    }

    public void notifiPictureReady() {

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Log.e(activity.getPackageName(), "Failed to create photo file.");
            }

            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                activity.startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private File createImageFile() throws IOException {
        String imageFileName = "JPEG_" + IdGenerator.createId();
        File storageDir = new File(Environment.getExternalStorageDirectory() + "/WantToSpeak");
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }

        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }
}
