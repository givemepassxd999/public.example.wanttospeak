package com.wanttospeak.items;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.givemepass.wanttospeak.R;
import com.wanttospeak.cache.Constant;
import com.wanttospeak.cache.DataHelper;
import com.wanttospeak.cache.MulitipleChoice;
import com.wanttospeak.cache.MyCombination;
import com.wanttospeak.dialog.CommonDialog;

import java.util.ArrayList;

/**
 * Created by givemepass on 2015/5/10.
 */
public class ItemDetailListDialog extends CommonDialog{

    private Context mContext;

    private ListView mListView;

    private ItemDetailListAdapter mAdapter;

    private int type;

    public ItemDetailListDialog(Context context, int type) {
        super(context);
        mContext = context;
        this.type = type;
        initView(type);

    }

    private void initView(int type){
        setContextView(R.layout.item_detail_list);
        setNaviBackgroundColor(Color.parseColor("#FFBBDD"));
        setNaviAddVisible(View.VISIBLE);
        switch(type){
            case Constant.TWO_OPTIONS:
                setNaviBackText(mContext.getString(R.string.two_option));
                break;
            case Constant.THREE_OPTIONS:
                setNaviBackText(mContext.getString(R.string.three_option));
                break;
            case Constant.FOUR_OPTIONS:
                setNaviBackText(mContext.getString(R.string.four_option));
                break;
        }

        mListView = (ListView) findViewById(R.id.item_detail_list);
        mAdapter = new ItemDetailListAdapter();
        mListView.setAdapter(mAdapter);
    }

    private class ItemDetailListAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            ArrayList<MulitipleChoice> list = MyCombination.getItemsCombinationList(DataHelper.getCurrentPersonId(), type);
            if(null != list) {
                return list.size();
            } else{
                return 0;
            }
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
