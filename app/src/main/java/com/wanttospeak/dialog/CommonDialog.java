package com.wanttospeak.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.givemepass.wanttospeak.R;

public abstract class CommonDialog extends Dialog {
    private Context mContext;
    private View naviBar;

    private TextView naviBackText;
    private View naviAction;

    private LinearLayout mainLayout;
    public CommonDialog(Context context) {
        super(context, android.R.style.Theme_Light);
        mContext = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().getAttributes().windowAnimations = R.style.dialog_animation;
        setContentView(R.layout.dialog_base);

        initView();
    }

    private void initView() {
        naviBar = findViewById(R.id.navi_bar);
        View back = findViewById(R.id.navi_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackKeyDown();
            }
        });
        naviBackText = (TextView) findViewById(R.id.navi_back_text);
        naviAction = findViewById(R.id.navi_action);
        mainLayout = (LinearLayout) findViewById(R.id.dialog_layout);
    }

    protected void setNaviActionVisible(int visible){
        naviAction.setVisibility(visible);
    }

    protected void setNaviActionIcon(int resourceId){
        naviAction.setBackgroundResource(resourceId);
    }

    protected void setOnNaviActionListener(View.OnClickListener listener){
        naviAction.setOnClickListener(listener);
    }

    protected void setNaviBackText(String text){
        naviBackText.setText(text);
    }

    protected void setContextView(int viewId){
        mainLayout.addView(LayoutInflater.from(mContext).inflate(viewId, null), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    protected void setContextViewBackground(int color){
        mainLayout.setBackgroundColor(color);
    }

    protected void setNaviBackgroundColor(int color){
        naviBar.setBackgroundColor(color);
    }

    protected void onBackKeyDown(){
        dismiss();
    }
}
