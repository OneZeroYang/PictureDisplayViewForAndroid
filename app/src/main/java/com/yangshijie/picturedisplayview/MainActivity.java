package com.yangshijie.picturedisplayview;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.ImageView;

import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.util.ArrayList;
import java.util.List;



import picturedisplayview.yangshijie.com.library.ItemTouchAdapter;
import picturedisplayview.yangshijie.com.library.BaseMyHolder;
import picturedisplayview.yangshijie.com.library.PDPView;
import picturedisplayview.yangshijie.com.library.PDPViewAdapter;
import picturedisplayview.yangshijie.com.library.PDPViewCall;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int REQUEST_CODE_CHOOSE = 0xff1;
    private RecyclerView mRecyclerView;
    private ItemTouchAdapter<Uri> adapter;
    private PDPView<Uri> myPDPView;
    private  PDPViewAdapter<Uri> pdpViewAdapter;
    private List<Uri> data=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myPDPView = (PDPView) findViewById(R.id.myPDPView);
        pdpViewAdapter = new PDPViewAdapter(this, data);
        myPDPView.setAdapter(pdpViewAdapter);
        myPDPView.setCall(new PDPViewCall<Uri>() {
            @Override
            public void addImage() {
                Log.i(TAG, "addImage: ");
                openCamera();
            }

            @Override
            public void onImageEvent(BaseMyHolder holder, int p) {
                Log.i(TAG, "onImageEvent: ");
            }

            @Override
            public void onUpdate(List<Uri> list) {
                Log.i(TAG, "onUpdate: "+list);

            }
        });

    }

    private void openCamera() {
        Matisse.from(MainActivity.this)
                .choose(MimeType.of(MimeType.JPEG)) // 选择 mime 的类型
                .countable(true)
                .maxSelectable(9) // 图片选择的最多数量
                //.gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f) // 缩略图的比例
                .imageEngine(new GlideEngine()) // 使用的图片加载引擎
                .forResult(REQUEST_CODE_CHOOSE);
    }
    List<Uri> mSelected;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);
            Log.d("Matisse", "mSelected: " + mSelected);
            pdpViewAdapter.addItme(mSelected);
        }
    }
}
