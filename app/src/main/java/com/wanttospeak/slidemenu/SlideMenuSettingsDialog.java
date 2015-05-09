package com.wanttospeak.slidemenu;

import android.content.Context;
import android.graphics.Color;

import com.wanttospeak.dialog.CommonDialog;

/**
 * Created by givemepass on 2015/5/9.
 */
public class SlideMenuSettingsDialog extends CommonDialog{

    private Context mContext;
    public SlideMenuSettingsDialog(Context context) {
        super(context);
        mContext = context;
        setNaviBackgroundColor(Color.parseColor("#FFBBDD"));
    }
}
