package com.wanttospeak.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;

import java.io.File;

/**
 * Created by givemepass on 2015/5/26.
 */
public class ImageHelper {

	public static Bitmap resize(String pathName, int maxWidth) throws Exception{
		// Setting bitmap option
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;

		// Get photo size
		BitmapFactory.decodeFile(pathName, options);
		float photoW = options.outWidth;
		float photoH = options.outHeight;
		// Get image scale size
		int scale = 1;
		if ((maxWidth > 1)) {
			if (photoW > 2*maxWidth || photoH>2*maxWidth) {
				scale = Math.max(Math.round(photoW / maxWidth), Math.round(photoH / maxWidth));
			}
		}
		// Change factory options
		options.inSampleSize = scale;
		options.inJustDecodeBounds = false;

		// Create image bitmap
		Bitmap bitmap = BitmapFactory.decodeFile(pathName, options);
		bitmap = resize(bitmap, maxWidth);
		int bWidth = bitmap.getWidth();
		int bHeight = bitmap.getHeight();
		return bitmap;
	}

	public static Bitmap resize(Bitmap bitmap, int maxWidth) {
		if(bitmap==null){
			return null;
		}
		int bWidth = bitmap.getWidth();
		int bHeight = bitmap.getHeight();

		if(bWidth > maxWidth && bWidth > bHeight){
			float ratio = (float)bHeight/(float)bWidth;
			bitmap = Bitmap.createScaledBitmap(bitmap, maxWidth, (int)(maxWidth*ratio), true);
		}else if(bHeight > maxWidth && bHeight > bWidth){
			float ratio = (float)bWidth/(float)bHeight;
			bitmap = Bitmap.createScaledBitmap(bitmap, (int)(maxWidth*ratio), maxWidth, true);
		}
		return bitmap;
	}

	public static Bitmap resize(String pathName, int width, int height) {
		// Setting bitmap option
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		// Get photo size
		BitmapFactory.decodeFile(pathName, options);
		float photoW = options.outWidth;
		float photoH = options.outHeight;
		float ratio = photoH/photoW;

		// Get image scale size
		int scale = 1;
		if ((width > 1) || (height > 1)) {
			if (photoW > width) {
				scale = Math.max(Math.round(photoW / width), Math.round(photoH / height));
			}
		}

		// Change factory options
		options.inSampleSize = scale;
		options.inJustDecodeBounds = false;

		// Create image bitmap
		Bitmap bitmap = BitmapFactory.decodeFile(pathName, options);

		return bitmap;
	}

	public static Bitmap rotate(Bitmap bitmap, int rotate) {
		Matrix matrix = new Matrix();
		matrix.postRotate(rotate);

		int width = bitmap.getWidth();
		int height = bitmap.getHeight();

		Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
		return newBitmap;
	}

	public static int getCameraPhotoOrientation(Context context, String pathName) {
		int rotate = 0;

		try {
			context.getContentResolver().notifyChange(Uri.parse(pathName), null);
			File imageFile = new File(pathName);
			ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());

			int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
				case ExifInterface.ORIENTATION_ROTATE_270:
					rotate = 270;
					break;

				case ExifInterface.ORIENTATION_ROTATE_180:
					rotate = 180;
					break;

				case ExifInterface.ORIENTATION_ROTATE_90:
					rotate = 90;
					break;
			}
		} catch (Exception e) {

		}

		return rotate;
	}

	public static Bitmap fixBitmapRotate(Context context, String pathName, Bitmap bitmap) {
		int rotate = getCameraPhotoOrientation(context, pathName);

		if (0 != rotate) {
			bitmap = rotate(bitmap, rotate);
		}

		return bitmap;
	}
}
