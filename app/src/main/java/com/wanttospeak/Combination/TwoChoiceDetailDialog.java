package com.wanttospeak.combination;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.givemepass.wanttospeak.R;
import com.wanttospeak.cache.DataHelper;
import com.wanttospeak.cache.MyItemList;
import com.wanttospeak.dao.ItemDao;
import com.wanttospeak.dao.MultipleChoiceDao;
import com.wanttospeak.dialog.CommonDialog;
import com.wanttospeak.util.Convert;
import com.wanttospeak.util.ImageHelper;
import com.wanttospeak.util.Logger;

import java.io.IOException;

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

	private DisplayMetrics metrics;

	public TwoChoiceDetailDialog(Context context, MultipleChoiceDao obj) {
		super(context);
		setContextView(R.layout.two_choice_detail_dialog);
		twoChoiceObj = obj;
		initView();
		initData();

	}

	private void playRecord(String path) {
		final MediaPlayer mediaPlayer = new MediaPlayer();
		try {
			mediaPlayer.setDataSource(path);
			mediaPlayer.prepare();
			mediaPlayer.start();
		} catch (IOException e) {
			Logger.e(e.getMessage());
			e.printStackTrace();
		}
		mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				mediaPlayer.stop();
				mediaPlayer.release();
			}
		});
	}

	private void initView(){
		topImg = (ImageView) findViewById(R.id.two_choice_detail_top_img);
		bottomImg = (ImageView) findViewById(R.id.two_choice_detail_bottom_img);

		topTextView = (TextView) findViewById(R.id.two_choice_detail_top_text);
		bottomTextView = (TextView) findViewById(R.id.two_choice_detail_bottom_text);


		topImg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				playRecord(topItem.getRecordPath());
			}
		});

		bottomImg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				playRecord(bottomItem.getRecordPath());
			}
		});

		metrics = mContext.getResources().getDisplayMetrics();
		ViewGroup.LayoutParams layoutParams = topImg.getLayoutParams();
		layoutParams.width = metrics.widthPixels;
		layoutParams.height = (int) Convert.convertDpToPixel(200, mContext);

		topImg.setLayoutParams(layoutParams);
		bottomImg.setLayoutParams(layoutParams);

	}

	private void initData(){
		currentPersonId = DataHelper.getCurrentPersonId();
		topItemId = twoChoiceObj.getArrayData(0);
		bottomItemId = twoChoiceObj.getArrayData(1);
		topItem = MyItemList.getItemObjByPersonIdAndItemId(currentPersonId, topItemId);
		bottomItem = MyItemList.getItemObjByPersonIdAndItemId(currentPersonId, bottomItemId);

		topBmp = ImageHelper.resize(topItem.getPhotoPath(), metrics.widthPixels, metrics.heightPixels);
		bottomBmp = ImageHelper.resize(bottomItem.getPhotoPath(), metrics.widthPixels, metrics.heightPixels);

		topImg.setImageBitmap(topBmp);
		bottomImg.setImageBitmap(bottomBmp);

		topTextView.setText(topItem.getItemName());
		bottomTextView.setText(bottomItem.getItemName());

	}
}
