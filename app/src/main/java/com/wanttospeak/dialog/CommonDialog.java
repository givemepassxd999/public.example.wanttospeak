package com.wanttospeak.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.example.givemepass.wanttospeak.R;

public abstract class CommonDialog extends Dialog {

    private Context mContext;

    private View back;

	public CommonDialog(Context context) {
        super(context, android.R.style.Theme_Light);
        mContext = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().getAttributes().windowAnimations = R.style.dialog_animation;
        setContentView(R.layout.dialog_layout);

        back = (View) findViewById(R.id.navi_back);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackKeyDown();

            }
        });
    }
    protected void setMainView(int view_id){
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.dialog_layout);
        mainLayout.addView(LayoutInflater.from(mContext).inflate(view_id, null));
    }

    protected void onBackKeyDown(){
        dismiss();
    }
}
