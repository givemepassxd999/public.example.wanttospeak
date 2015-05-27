package com.wanttospeak.combination;

import android.content.Context;

import com.wanttospeak.dialog.CommonDialog;

/**
 * Created by givemepass on 2015/5/27.
 */
public class CombinationListDialog extends CommonDialog{

	protected OnSaveFinishedListener mOnSaveFinishedListener;

	public void setOnSaveFinishedListener(OnSaveFinishedListener listener){
		mOnSaveFinishedListener = listener;
	}

	public void notifySaveFinished(){
		if(mOnSaveFinishedListener != null){
			mOnSaveFinishedListener.OnSaveFinished();
		}
	}

	public interface OnSaveFinishedListener{
		public void OnSaveFinished();
	}

	public CombinationListDialog(Context context) {
		super(context);
	}
}
