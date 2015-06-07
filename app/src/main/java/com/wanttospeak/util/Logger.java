package com.wanttospeak.util;

import android.util.Log;

/**
 * Created by givemepass on 2015/5/27.
 */
public class Logger{

	public final static boolean DEBUG = true;

	public static void e(String msg){
		if(DEBUG) {
			Log.e("", msg);
		}
	}

	public static void i(String msg){
		if(DEBUG) {
			Log.i("", msg);
		}
	}
}
