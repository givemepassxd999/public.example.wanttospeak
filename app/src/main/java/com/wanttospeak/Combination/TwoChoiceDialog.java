package com.wanttospeak.combination;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.givemepass.wanttospeak.R;
import com.j256.ormlite.dao.Dao;
import com.wanttospeak.cache.DataHelper;
import com.wanttospeak.cache.MyCombination;
import com.wanttospeak.dao.DatabaseHelper;
import com.wanttospeak.dao.ItemDao;
import com.wanttospeak.dao.MultipleChoiceDao;
import com.wanttospeak.items.ItemListDialog;
import com.wanttospeak.items.ItemMakerDialog;
import com.wanttospeak.main.MainActivity;
import com.wanttospeak.util.ImageHelper;
import com.wanttospeak.util.NoticeCenter;

import java.sql.SQLException;

/**
 * Created by givemepass on 2015/5/20.
 */
public class TwoChoiceDialog extends CombinationListDialog {

	private View addNewLeftItem;

	private ImageView leftItemView;

	private View addNewRightItem;

	private ImageView rightItemView;

	private Context mContext;

	private Button saveButton;

	private MultipleChoiceDao twoChoice;

	private EditText combinationText;

	private final int LEFT = 0;

	private final int RIGHT = 1;

	private NoticeCenter.OnSaveNewItemListener mOnSaveNewItemListener;

	public TwoChoiceDialog(Context context, int type, String combinationId) {
		super(context);
		mContext = context;
		setContextView(R.layout.two_choice_dialog);
		addNewLeftItem = (View) findViewById(R.id.two_choice_left);
		leftItemView = (ImageView) findViewById(R.id.two_choice_left_item);
		addNewRightItem = (View) findViewById(R.id.two_choice_right);
		rightItemView = (ImageView) findViewById(R.id.two_choice_right_item);

		saveButton = (Button) findViewById(R.id.two_choice_add_item_button);
		combinationText = (EditText) findViewById(R.id.two_choice_add_item_name);
		twoChoice = new MultipleChoiceDao();

		addNewLeftItem.setOnClickListener(new MyClickListener(LEFT));
		addNewRightItem.setOnClickListener(new MyClickListener(RIGHT));

		saveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (TextUtils.isEmpty(combinationText.getText())) {
					Toast.makeText(mContext, R.string.add_combination_name, Toast.LENGTH_SHORT).show();
					return;
				} else if(twoChoice.getArrayData(0) == null) {
					Toast.makeText(mContext, R.string.plz_select_right_item, Toast.LENGTH_SHORT).show();
					return;
				} else if(twoChoice.getArrayData(1) == null){
					Toast.makeText(mContext, R.string.plz_select_left_item, Toast.LENGTH_SHORT).show();
					return;
				} else{
					twoChoice.setChoiceName(combinationText.getText().toString());
				}

				Toast.makeText(mContext, R.string.save_item_combination_success, Toast.LENGTH_SHORT).show();
				MyCombination.putItemCombination(DataHelper.getCurrentPersonId(), twoChoice);
				try {
					Dao<MultipleChoiceDao, String> twoChoiceObjectDao = DatabaseHelper.getInstance().getMultipleChoiceDao();
					twoChoiceObjectDao.create(twoChoice);

				} catch (SQLException e) {
					Toast.makeText(mContext, mContext.getString(R.string.add_item_fail),
							Toast.LENGTH_SHORT).show();
					e.printStackTrace();
				}
				Toast.makeText(mContext, mContext.getString(R.string.add_item_success),
						Toast.LENGTH_SHORT).show();
				if(mOnSaveFinishedListener != null){
					mOnSaveFinishedListener.OnSaveFinished();
				}
				dismiss();
			}
		});

	}

	private class MyClickListener implements View.OnClickListener{
		private int position;
		public MyClickListener(int pos){
			position = pos;
		}
		@Override
		public void onClick(View view) {
			AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
			builder.setTitle(mContext.getString(R.string.select_item))
				.setItems(new String[]{mContext.getString(R.string.select_from_list),
					mContext.getString(R.string.add_new_item)}, new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					switch (which) {
						case 0:
							ItemListDialog mItemListDialog = new ItemListDialog(mContext);
							mItemListDialog.setOnChoiceCompleteListener(new ItemListDialog.OnChoiceCompleteListener() {
								@Override
								public void onChoiceCompleted(ItemDao item) {
									setItem(item, position);
								}
							});
							mItemListDialog.show();
							break;
						case 1:
							ItemMakerDialog itemMakerDialog = new ItemMakerDialog((MainActivity) mContext);
							mOnSaveNewItemListener = new NoticeCenter.OnSaveNewItemListener() {
								@Override
								public void notifySaveNewItem(ItemDao item) {
									setItem(item, position);
								}
							};
							NoticeCenter.getInstance().setOnSaveNewItemListener(mOnSaveNewItemListener);
							itemMakerDialog.show();
							break;
					}
					}});
			builder.show();
		}
	}
	private void setItem(ItemDao item, int position){
		String itemPicPath = item.getPhotoPath();
		try {
			Bitmap b = ImageHelper.resize(itemPicPath, 200);
			switch (position){
				case LEFT:
					twoChoice.setArrayData(item.getItemId(), 0);
					leftItemView.setImageBitmap(b);
					break;
				case RIGHT:
					twoChoice.setArrayData(item.getItemId(), 1);
					rightItemView.setImageBitmap(b);
					break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void dismiss() {
		NoticeCenter.getInstance().removeOnSaveNewItemListener(mOnSaveNewItemListener);
		super.dismiss();
	}
}
