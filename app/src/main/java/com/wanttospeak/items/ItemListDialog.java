package com.wanttospeak.items;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.givemepass.wanttospeak.R;
import com.wanttospeak.cache.DataHelper;
import com.wanttospeak.cache.MyItemList;
import com.wanttospeak.dao.ItemDao;
import com.wanttospeak.dialog.CommonDialog;
import com.wanttospeak.main.MainActivity;
import com.wanttospeak.util.NoticeCenter;

import java.util.ArrayList;

/**
 * Created by givemepass on 2015/5/24.
 */
public class ItemListDialog extends CommonDialog{

    private ListView mListView;

    private ArrayList<ItemDao> itemList;

    private ItemListAdapter mItemListAdapter;

    private OnChoiceCompleteListener mOnChoiceCompleteListener;

    private NoticeCenter.OnSaveNewItemListener mOnSaveNewItemListener;

    public interface OnChoiceCompleteListener{
        public void onChoiceCompleted(ItemDao item);
    }

    public void setOnChoiceCompleteListener(OnChoiceCompleteListener listener){
        mOnChoiceCompleteListener = listener;
    }

    public ItemListDialog(Context context) {
        super(context);
        setContextView(R.layout.item_list_dialog);
        mListView = (ListView) findViewById(R.id.item_list);

        itemList = MyItemList.getItemListByPersonId(DataHelper.getCurrentPersonId());
        mItemListAdapter = new ItemListAdapter();
        mListView.setAdapter(mItemListAdapter);

        setNaviActionIcon(R.drawable.add_item);
        setNaviActionVisible(View.VISIBLE);
        setOnNaviActionClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemMakerDialog itemMakerDialog = new ItemMakerDialog((MainActivity) mContext);
                itemMakerDialog.show();
            }
        });
        mOnSaveNewItemListener = new NoticeCenter.OnSaveNewItemListener() {
            @Override
            public void notifySaveNewItem(ItemDao item) {
                itemList = MyItemList.getItemListByPersonId(DataHelper.getCurrentPersonId());
                mItemListAdapter.notifyDataSetChanged();
            }
        };
        NoticeCenter.getInstance().setOnSaveNewItemListener(mOnSaveNewItemListener);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                new ItemDetailDialog(mContext, i).show();
                //從清單選取
                if(mOnChoiceCompleteListener != null){
                    mOnChoiceCompleteListener.onChoiceCompleted(itemList.get(i));
                    dismiss();
                }
            }
        });
    }

    @Override
    public void dismiss() {
        NoticeCenter.getInstance().removeOnSaveNewItemListener(mOnSaveNewItemListener);
        super.dismiss();
    }

    private class ItemListAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            if(itemList != null){
                return itemList.size();
            } else {
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
            View v = view;
            Holder holder;
            if(v == null){
                v = LayoutInflater.from(mContext).inflate(R.layout.itemlist_dialog_item, null);

                holder = new Holder();
                holder.textView = (TextView) v.findViewById(R.id.itemlist_dialog_item);
                v.setTag(holder);
            } else{
                holder = (Holder) v.getTag();
            }
            holder.textView.setText(itemList.get(i).getItemName());
            return v;
        }

        class Holder{
            TextView textView;
        }
    }
}
