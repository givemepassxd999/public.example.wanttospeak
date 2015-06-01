package com.wanttospeak.combination;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.givemepass.wanttospeak.R;
import com.wanttospeak.cache.DataHelper;
import com.wanttospeak.cache.MyItemList;
import com.wanttospeak.dao.ItemDao;
import com.wanttospeak.dao.MultipleChoiceDao;
import com.wanttospeak.dialog.CommonDialog;
import com.wanttospeak.util.ImageHelper;

/**
 * Created by givemepass on 2015/6/1.
 */
public class TwoChoiceDetailDialog extends CommonDialog{

	private MultipleChoiceDao twoChoiceObj;

	private ImageView topImg, bottomImg;

	private String topItemId, bottomItemId;

	private String currentPersonId;

	private ItemDao topItem, bottomItem;

	private Bitmap topBmp, bottomBmp;

	private TextView topTextView, bottomTextView;

	public TwoChoiceDetailDialog(Context context, MultipleChoiceDao obj) {
		super(context);
		setContextView(R.layout.two_choice_detail_dialog);
		twoChoiceObj = obj;
		initData();
		initView();
	}

	private void initView(){
		topImg = (ImageView) findViewById(R.id.two_choice_detail_top_img);
		bottomImg = (ImageView) findViewById(R.id.two_choice_detail_bottom_img);
		topImg.setImageBitmap(topBmp);
		bottomImg.setImageBitmap(bottomBmp);

		topTextView = (TextView) findViewById(R.id.two_choice_detail_top_text);
		bottomTextView = (TextView) findViewById(R.id.two_choice_detail_bottom_text);
		topTextView.setText(topItem.getItemName());
		bottomTextView.setText(bottomItem.getItemName());
	}

	private void initData(){
		currentPersonId = DataHelper.getCurrentPersonId();
		topItemId = twoChoiceObj.getArrayData(0);
		bottomItemId = twoChoiceObj.getArrayData(1);
		topItem = MyItemList.getItemObjByPersonIdAndItemId(currentPersonId, topItemId);
		bottomItem = MyItemList.getItemObjByPersonIdAndItemId(currentPersonId, bottomItemId);

		topBmp = ImageHelper.resize(topItem.getPhotoPath(), 500, 500);
		bottomBmp = ImageHelper.resize(bottomItem.getPhotoPath(), 500, 500);
	}
}
