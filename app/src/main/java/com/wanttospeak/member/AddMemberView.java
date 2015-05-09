package com.wanttospeak.member;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.givemepass.wanttospeak.R;

/**
 * Created by givemepass on 2015/5/9.
 */
public class AddMemberView extends RelativeLayout {
    public AddMemberView(Context context) {
        super(context);
        View mainView = LayoutInflater.from(context).inflate(R.layout.add_new_member, null);

        addView(mainView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }
}
