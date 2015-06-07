package com.wanttospeak.util;

import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by givemepass on 2015/6/7.
 */
public class RecorderHelper {
	public static void playRecord(String path) {
		final MediaPlayer mediaPlayer = new MediaPlayer();
		try {
			mediaPlayer.setDataSource(path);
			mediaPlayer.prepare();
			mediaPlayer.start();
		} catch (IOException e) {
			Logger.e(e.getMessage());
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
}
