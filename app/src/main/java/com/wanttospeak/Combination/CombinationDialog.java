package com.wanttospeak.combination;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.givemepass.wanttospeak.R;
import com.wanttospeak.cache.Constant;
import com.wanttospeak.cache.MultipleChoice;
import com.wanttospeak.cache.MyCombination;
import com.wanttospeak.dialog.CommonDialog;

import java.util.ArrayList;

/**
 * Created by givemepass on 2015/5/20.
 */
public class CombinationDialog extends CommonDialog{
    private int type;

    private Context mContext;

    private ListView mListView;

    private ArrayList<MultipleChoice> combinationList;
    public CombinationDialog(Context context, final int type) {
        super(context);
        mContext = context;
        this.type = type;
        setContextView(R.layout.combination_list);
        setNaviActionVisible(View.VISIBLE);
        setNaviActionIcon(R.drawable.add);
        setOnNaviActionClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (type) {
                    case Constant.TWO_OPTIONS:
                        new TwoChoiceDialog(mContext, type, null).show();
                        break;
                    case Constant.THREE_OPTIONS:
                        new ThreeChoiceDialog(mContext, type, null).show();
                        break;
                    case Constant.FOUR_OPTIONS:
                        new FourChoiceDialog(mContext, type, null).show();
                        break;
                }
            }
        });
        combinationList = MyCombination.getItemsCombinationList("123", type);

        mListView = (ListView) findViewById(R.id.combination_list);


    }

    private class CombinationListAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return combinationList.size();
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
            View v = view;
            Holder holder;
            if(v == null){
                v = LayoutInflater.from(mContext).inflate(R.layout.combination_item, null);
                holder = new Holder();

                holder.text = (TextView) v.findViewById(R.id.combination_item_text);
                v.setTag(holder);
            } else{
                holder = (Holder) v.getTag();
            }
            holder.text.setText(combinationList.get(i).getChoiceName());
            return v;
        }

        class Holder {
            TextView text;
        }
    }
}
