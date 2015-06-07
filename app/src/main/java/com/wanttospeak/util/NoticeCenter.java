package com.wanttospeak.util;

import android.net.Uri;

import com.wanttospeak.dao.ItemDao;

import java.util.ArrayList;

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

    private static ArrayList<OnSaveNewItemListener> mOnSaveNewItemListenerList = new ArrayList<OnSaveNewItemListener>();

    public interface OnSaveNewItemListener{
        public void notifySaveNewItem(ItemDao item);
    }

    public void setOnSaveNewItemListener(OnSaveNewItemListener listener){
        if(listener != null) {
            mOnSaveNewItemListenerList.add(listener);
        }
    }

    public void removeOnSaveNewItemListener(OnSaveNewItemListener listener){
        if(listener != null){
            mOnSaveNewItemListenerList.remove(listener);
        }
    }

    public void notifySaveNewItemFinished(ItemDao item){
        for(OnSaveNewItemListener l : mOnSaveNewItemListenerList){
           if(l != null){
               l.notifySaveNewItem(item);
           }
        }
    }

}
