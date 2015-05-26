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
import com.wanttospeak.dao.ItemDao;
import com.wanttospeak.dao.TwoChoiceDao;
import com.wanttospeak.dialog.CommonDialog;
import com.wanttospeak.items.ItemListDialog;
import com.wanttospeak.util.ImageHelper;

/**
 * Created by givemepass on 2015/5/20.
 */
public class TwoChoiceDialog extends CommonDialog {
	private View addNewLeftItem;

	private ImageView leftItemView;

	private View addNewRightItem;

	private ImageView rightItemView;

	private Context mContext;

	private Button saveButton;

	private TwoChoiceDao twoChoice;

	private EditText combinationText;

	private final int LEFT = 0;

	private final int RIGHT = 1;

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
		twoChoice = new TwoChoiceDao();

		addNewLeftItem.setOnClickListener(new MyClickListener(LEFT));
		addNewRightItem.setOnClickListener(new MyClickListener(RIGHT));

		saveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
			if (TextUtils.isEmpty(combinationText.getText())) {
				Toast.makeText(mContext, R.string.add_combination_name, Toast.LENGTH_SHORT).show();
				return;
			}
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

										String itemPicPath = item.getPhotoPath();
										try {
											Bitmap b = ImageHelper.resize(itemPicPath, 200);
											switch (position){
												case LEFT:
													twoChoice.setLeftItemId(item.getItemId());
													leftItemView.setImageBitmap(b);
													break;
												case RIGHT:
													twoChoice.setRightItemId(item.getItemId());
													rightItemView.setImageBitmap(b);
													break;
											}

										} catch (Exception e) {
											e.printStackTrace();
										}
									}
								});
								mItemListDialog.show();
								break;
							case 1:

								break;
						}
					}
				});
			builder.show();
		}
	}

}
