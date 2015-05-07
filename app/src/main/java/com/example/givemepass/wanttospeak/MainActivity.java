package com.example.givemepass.wanttospeak;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.androidquery.AQuery;


public class MainActivity extends ActionBarActivity {
    private AQuery aq;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aq = new AQuery(this);
        initView();
    }

    private void initView() {
        setupDrawer();
    }

    private void setupDrawer(){
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        aq.id(R.id.drawer_switch).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSideMenuOpen()) {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                }else {
                    mDrawerLayout.openDrawer(aq.id(R.id.left_drawer).getView());
                }
            }
        });
    }

    public boolean isSideMenuOpen(){
        return mDrawerLayout.isDrawerOpen(aq.id(R.id.left_drawer).getView());
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
