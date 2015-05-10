package com.wanttospeak.items;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.example.givemepass.wanttospeak.R;
import com.wanttospeak.cache.Constant;
import com.wanttospeak.dialog.CommonDialog;

/**
 * Created by givemepass on 2015/5/10.
 */
public class ItemDetailListDialog extends CommonDialog{

    private Context mContext;

    public ItemDetailListDialog(Context context, int type) {
        super(context);
        mContext = context;
        setNaviBackgroundColor(Color.parseColor("#FFBBDD"));
        setNaviAddVisible(View.VISIBLE);
        switch(type){
            case Constant.TWO_OPTIONS:
                setNaviBackText(context.getString(R.string.two_option));
                break;
            case Constant.THREE_OPTIONS:
                setNaviBackText(context.getString(R.string.three_option));
                break;
            case Constant.FOUR_OPTIONS:
                setNaviBackText(context.getString(R.string.four_option));
                break;
        }

    }
}
