package com.wanttospeak.combination;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.givemepass.wanttospeak.R;
import com.wanttospeak.cache.MultipleChoice;
import com.wanttospeak.dao.TwoChoiceDao;
import com.wanttospeak.dialog.CommonDialog;

/**
 * Created by givemepass on 2015/5/20.
 */
public class TwoChoiceDialog extends CommonDialog{
    private View addNewLeftItem;

    private Context mContext;

    private Button saveButton;

    private MultipleChoice twoChoice;

    private EditText combinationText;

    private String leftItemId, rightItemId;

    public TwoChoiceDialog(Context context, int type, String combinationId) {
        super(context);
        mContext = context;
        setContextView(R.layout.two_choice_dialog);
//        setNaviActionIcon(R.drawable.item_save);
//        setNaviActionVisible(View.VISIBLE);
        addNewLeftItem = findViewById(R.id.two_choice_left);
        saveButton = (Button) findViewById(R.id.two_choice_add_item_button);
        combinationText = (EditText) findViewById(R.id.two_choice_add_item_name);
        twoChoice = new TwoChoiceDao();

        addNewLeftItem.setOnClickListener(new View.OnClickListener() {
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

                                        break;
                                    case 1:

                                        break;
                                }
                            }
                        });
                builder.show();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(combinationText.getText())){
                    Toast.makeText(mContext, R.string.add_combination_name, Toast.LENGTH_SHORT).show();
                    return;
                } else if(leftItemId == null || rightItemId == null){
                    return;
                }
            }
        });

    }
}
