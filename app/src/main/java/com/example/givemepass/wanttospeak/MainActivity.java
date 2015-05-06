package com.example.givemepass.wanttospeak;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        public View getView(int i, View view, ViewGroup viewGroup) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }
    }
}
