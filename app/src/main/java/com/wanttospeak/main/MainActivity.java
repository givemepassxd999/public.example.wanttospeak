package com.wanttospeak.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.example.givemepass.wanttospeak.R;
import com.wanttospeak.cache.Constant;
import com.wanttospeak.cache.DataHelper;
import com.wanttospeak.dialog.DataDialog;
import com.wanttospeak.items.ItemDetailListDialog;
import com.wanttospeak.slidemenu.SlideMenuView;
import com.wanttospeak.util.IdGenerator;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    private AQuery aq;
    private DrawerLayout mDrawerLayout;
    private LinearLayout mSlideContainer;
    private Context mContext;
    private GridView mGridView;
    private BaseAdapter mBaseAdapter;

    //TODO: need move to dialog, plz ignore now (by Polly)
    private final int REQUEST_TAKE_PHOTO = 1;
    private File photoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //�@�}�l�N��]�wperson id
        DataHelper.setCurrentPersonId("123");
        aq = new AQuery(this);
        mContext = this;
        initView();
    }

    private void initView() {
        setupDrawer();

        aq.id(R.id.add_item).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
//                new DataDialog(mContext).show();
            }
        });

        mSlideContainer = (LinearLayout)aq.id(R.id.slidemenu_container).getView();
        mSlideContainer.addView(new SlideMenuView(this),
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        mGridView = (GridView) findViewById(R.id.choice_grid);
        mBaseAdapter = new ChoiceGridAdapter();
        mGridView.setAdapter(mBaseAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        new ItemDetailListDialog(mContext, Constant.TWO_OPTIONS).show();
                        break;
                    case 1:
                        new ItemDetailListDialog(mContext, Constant.THREE_OPTIONS).show();
                        break;
                    case 2:
                        new ItemDetailListDialog(mContext, Constant.FOUR_OPTIONS).show();
                        break;
                }
            }
        });
    }

    //TODO: need move to dialog, plz ignore now (by Poll
    private File createImageFile() throws IOException {
        String imageFileName = "JPEG_" + IdGenerator.createId();
        File storageDir = new File(Environment.getExternalStorageDirectory() + "/WantToSpeak");
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }

        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }

    //TODO: need move to dialog, plz ignore now (by Poll
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Log.e(getPackageName(), "Failed to create photo file.");
            }

            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
//          setImageBitmap(BitmapFactory.decodeFile(photoFile.getPath()));
        }
    }

    private void setupDrawer(){
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        aq.id(R.id.drawer_switch).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSideMenuOpen()) {
                    mDrawerLayout.closeDrawer(Gravity.START);
                }else {
                    mDrawerLayout.openDrawer(mSlideContainer);
                }
            }
        });
    }

    private boolean isSideMenuOpen(){
        return mDrawerLayout.isDrawerOpen(mSlideContainer);
    }

    private class ChoiceGridAdapter extends BaseAdapter{
        private ChoiceGridAdapter() {
        }

        @Override
        public int getCount() {
            return 3;
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
            View convertView = view;
            Holder holder;
            if(null == convertView){
                convertView = LayoutInflater.from(mContext).inflate(R.layout.main_choice_layout, null);
                holder = new Holder();

                holder.img = (ImageView) convertView.findViewById(R.id.choice_grid_img);
                holder.text = (TextView) convertView.findViewById(R.id.choice_grid_text);

                convertView.setTag(holder);
            } else{
                holder = (Holder) convertView.getTag();
            }
            switch(i){
                case 0:
                    holder.img.setImageResource(R.drawable.two_fingers);
                    holder.text.setText(mContext.getString(R.string.two_option));
                    break;
                case 1:
                    holder.img.setImageResource(R.drawable.three_fingers);
                    holder.text.setText(mContext.getString(R.string.three_option));
                    break;
                case 2:
                    holder.img.setImageResource(R.drawable.four_fingers);
                    holder.text.setText(mContext.getString(R.string.four_option));
                    break;
            }
            return convertView;
        }

        class Holder{
            ImageView img;

            TextView text;
        }
    }
}
