package com.example.givemepass.wanttospeak;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.androidquery.AQuery;
import com.example.wanttospeak.CommonDialog;


public class MainActivity extends AppCompatActivity {
    private AQuery aq;
    private DrawerLayout mDrawerLayout;
    private Context mContext;

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
                new CommonDialog(mContext).show();
            }
        });
    }

    private void setupDrawer(){
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        aq.id(R.id.drawer_switch).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSideMenuOpen()) {
                    mDrawerLayout.closeDrawer(Gravity.START);
                }else {
                    mDrawerLayout.openDrawer(aq.id(R.id.left_drawer).getView());
                }
            }
        });
    }

    private boolean isSideMenuOpen(){
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
