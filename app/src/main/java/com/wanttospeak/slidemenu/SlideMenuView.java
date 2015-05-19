package com.wanttospeak.slidemenu;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.givemepass.wanttospeak.R;
import com.wanttospeak.member.MemberTabDialog;

/**
 * Created by givemepass on 2015/5/9.
 */
public class SlideMenuView extends RelativeLayout {

    private View mainView;

    private ListView mListView;

    private BaseAdapter mAdapter;

    private Context mContext;

    private View settingsLayout;

    private View addMember;

    private View deleteMember;

    public SlideMenuView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    private void initView(){
        mainView = LayoutInflater.from(mContext).inflate(R.layout.slidemenu_layout, null);
        this.addView(mainView, LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        settingsLayout = findViewById(R.id.slidemenu_settings_layout);
        settingsLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                view.setEnabled(false);
                new SlideMenuSettingsDialog(mContext).show();

                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        view.setEnabled(true);
                    }
                }, 300);
            }
        });

        addMember = findViewById(R.id.slidemenu_add_member_layout);
        deleteMember = findViewById(R.id.slidemenu_del_member_layout);
        addMember.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                new MemberTabDialog(mContext).show();
            }
        });
        deleteMember.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                MemberTabDialog mMemberTabDialog = new MemberTabDialog(mContext);
                mMemberTabDialog.changedTab(1);
                mMemberTabDialog.show();
            }
        });
    }

    private class SlideMenuAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            return null;
        }
    }
}
