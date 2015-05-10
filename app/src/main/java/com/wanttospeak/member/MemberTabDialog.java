package com.wanttospeak.member;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.givemepass.wanttospeak.R;

import java.util.ArrayList;

/**
 * Created by givemepass on 2015/5/9.
 */
public class MemberTabDialog extends TabDialog{

    private Context mContext;

    public MemberTabDialog(Context context) {
        super(context);
        mContext = context;
        setNaviBackgroundColor(Color.parseColor("#FFBBDD"));
        setContextViewBackground(Color.parseColor("#dddddd"));
    }

    @Override
    public ArrayList<View> getMainViewList(Context context) {
        AddMemberView mAddMemberView = new AddMemberView(context);

        RemoveMemberView mRemoveMemberView = new RemoveMemberView(context);
        ArrayList<View> mainViewList = new ArrayList<View>();
        //要按照順序加入
        mainViewList.add(mAddMemberView);
        mainViewList.add(mRemoveMemberView);

        return mainViewList;
    }

    @Override
    public View createTabView(Context context, int index) {
        View v = null;
        switch(index) {
            case 0:
                v = LayoutInflater.from(context).inflate(R.layout.tab_view, null, false);
                TextView addTab = (TextView) v.findViewById(R.id.tab_text);
                addTab.setText(context.getString(R.string.slide_menu_member_add));
                View addIcon = v.findViewById(R.id.tab_icon);
                addIcon.setBackgroundResource(R.drawable.add_new_member);
                break;
            case 1:
                v = LayoutInflater.from(context).inflate(R.layout.tab_view, null, false);
                TextView removeTab = (TextView) v.findViewById(R.id.tab_text);
                removeTab.setText(context.getString(R.string.slide_menu_member_remove));
                View removeIcon = v.findViewById(R.id.tab_icon);
                removeIcon.setBackgroundResource(R.drawable.remove_member);
                break;
        }
        return v;
    }
}
