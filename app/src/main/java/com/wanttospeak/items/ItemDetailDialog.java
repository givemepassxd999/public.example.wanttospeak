package com.wanttospeak.items;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.givemepass.wanttospeak.R;
import com.wanttospeak.cache.DataHelper;
import com.wanttospeak.cache.MyItemList;
import com.wanttospeak.dao.ItemDao;
import com.wanttospeak.dialog.CommonDialog;
import com.wanttospeak.util.Convert;
import com.wanttospeak.util.ImageHelper;
import com.wanttospeak.util.RecorderHelper;

/**
 * Created by givemepass on 2015/6/7.
 */
public class ItemDetailDialog extends CommonDialog{

	private int position;

	private Context mContext;

	private ImageView img;

	private TextView text;

	public ItemDetailDialog(Context context, int pos) {
		super(context);
		mContext = context;
		setContextView(R.layout.item_detail);
		position = pos;
		final ItemDao item = MyItemList.getItemListByPersonId(DataHelper.getCurrentPersonId()).get(pos);

		img = (ImageView) findViewById(R.id.item_detail_img);
		text = (TextView) findViewById(R.id.item_detail_text);
		DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
		Bitmap bmp = ImageHelper.resize(item.getPhotoPath(), metrics.widthPixels, metrics.heightPixels);

		metrics = mContext.getResources().getDisplayMetrics();
		ViewGroup.LayoutParams layoutParams = img.getLayoutParams();
		layoutParams.width = metrics.widthPixels;
		layoutParams.height = (int) Convert.convertDpToPixel(200, mContext);

		img.setLayoutParams(layoutParams);
		img.setImageBitmap(bmp);
		text.setText(item.getItemName());

		img.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				RecorderHelper.playRecord(item.getRecordPath());
			}
		});
	}
}
