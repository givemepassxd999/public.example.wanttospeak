package com.wanttospeak.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.androidquery.AQuery;
import com.example.givemepass.wanttospeak.R;
import com.wanttospeak.slidemenu.SlideMenuView;


public class MainActivity extends AppCompatActivity {
    private AQuery aq;
    private DrawerLayout mDrawerLayout;
    private Context mContext;

    private SlideMenuView mSlideMenuView;

    private LinearLayout mSlideContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        aq = new AQuery(this);
        initView();
    }

    private void initView() {
        setupDrawer();
        aq.id(R.id.add_item).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mSlideMenuView = new SlideMenuView(this);
        mSlideContainer = (LinearLayout) findViewById(R.id.slidemenu_container);
        mSlideContainer.addView(mSlideMenuView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
    }

    private void setupDrawer(){
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        aq.id(R.id.drawer_switch).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSideMenuOpen()) {
                    mDrawerLayout.closeDrawer(Gravity.START);
                }else {
                    mDrawerLayout.openDrawer(mSlideContainer);
                }
            }
        });
    }

    private boolean isSideMenuOpen(){
        return mDrawerLayout.isDrawerOpen(mSlideContainer);
    }

    private class MyGridAdapter extends BaseAdapter{
        private MyGridAdapter() {
        }

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
