package com.wanttospeak.combination;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import com.example.givemepass.wanttospeak.R;
import com.wanttospeak.dialog.CommonDialog;

/**
 * Created by givemepass on 2015/5/20.
 */
public class TwoChoiceDialog extends CommonDialog{
    private View addNewTopItem;

    private Context mContext;
    public TwoChoiceDialog(Context context, int type, String combinationId) {
        super(context);
        mContext = context;
        setContextView(R.layout.two_choice_dialog);
        addNewTopItem = findViewById(R.id.two_choice_top_item);
        addNewTopItem.setOnClickListener(new View.OnClickListener() {
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
    }
}
