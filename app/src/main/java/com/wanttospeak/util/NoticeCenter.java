package com.wanttospeak.util;

import android.net.Uri;

/**
 * Created by givemepass on 2015/5/24.
 */
public class NoticeCenter {

    private static NoticeCenter mNoticeCenter;

    public static NoticeCenter getInstance(){
        if(mNoticeCenter == null){
            mNoticeCenter = new NoticeCenter();
        }
        return mNoticeCenter;
    }

    private OnNewPictureReadyListener mOnNewPictureReadyListener;

    public interface OnNewPictureReadyListener{
        public void notifyNewPictureReady();
    }

    public void setOnNewPictureReady(OnNewPictureReadyListener listener){
        mOnNewPictureReadyListener = listener;
    }

    public void notifyOnNewPictureReady(){
        if(mOnNewPictureReadyListener != null){
            mOnNewPictureReadyListener.notifyNewPictureReady();
        }
    }



    private OnGalleryPictureReadyListener mOnGalleryPictureReadyListener;

    public interface OnGalleryPictureReadyListener{
        public void notifyGalleryPictureReady(Uri uri);
    }

    public void setOnGalleryPictureReady(OnGalleryPictureReadyListener listener){
        mOnGalleryPictureReadyListener = listener;
    }

    public void notyfyOnGalleryPictureReady(Uri uri){
        if(mOnGalleryPictureReadyListener != null){
            mOnGalleryPictureReadyListener.notifyGalleryPictureReady(uri);
        }
    }

    private OnSaveNewItemListener mOnSaveNewItemListener;

    public interface OnSaveNewItemListener{
        public void notifySaveNewItem();
    }

    public void setOnSaveNewItemListener(OnSaveNewItemListener listener){
        mOnSaveNewItemListener = listener;
    }

    public void notifySaveNewItem(){
        if(mOnSaveNewItemListener != null){
            mOnSaveNewItemListener.notifySaveNewItem();
        }
    }

}
