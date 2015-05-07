package com.example.givemepass.wanttospeak;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


public class MainActivity extends ActionBarActivity {

    private DrawerLayout mDrawerLayout;
    private View mNaviSwitchDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNaviSwitchDrawer = findViewById(R.id.drawer_switch);
        mNaviSwitchDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSideMenuOpen()){
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                }
            }
        });
    }

    public boolean isSideMenuOpen(){
        return mDrawerLayout.isDrawerOpen(Gravity.LEFT);
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
