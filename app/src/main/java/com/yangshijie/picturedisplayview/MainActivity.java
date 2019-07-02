package com.yangshijie.picturedisplayview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;


import picturedisplayview.yangshijie.com.library.BasePDPViewAdapter;
import picturedisplayview.yangshijie.com.library.ItemTouchAdapter;
import picturedisplayview.yangshijie.com.library.MyItemTouchHandler;
import picturedisplayview.yangshijie.com.library.BaseMyHolder;
import picturedisplayview.yangshijie.com.library.PDPView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    private ItemTouchAdapter adapter;
    private PDPView myPDPView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> data=new ArrayList<>();
       for (int a=0;a<20;a++){
           data.add(a+"");
       }


        myPDPView = (PDPView) findViewById(R.id.myPDPView);

        myPDPView.setAdapter(new BasePDPViewAdapter(data) {
            @Override
            protected int setView() {
                return  R.layout.test;
            }
            @Override
            public void BindView(BaseMyHolder holder, int position) {
                ImageView view = (ImageView) holder.findView(R.id.image);
                view.setImageResource(R.mipmap.timg);
            }
        });


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        myPDPView.setLayoutManager(gridLayoutManager);





//       mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
//
//
//
//
//        adapter=new ItemTouchAdapter(this,data) {
//            @Override
//            protected int getView() {
//                return R.layout.test;
//            }
//
//            @Override
//            protected void BindView(BaseMyHolder holder, int position) {
//                ImageView view = (ImageView) holder.findView(R.id.image);
//                view.setImageResource(R.mipmap.timg);
//            }
//        };
//
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
//        mRecyclerView.setLayoutManager(gridLayoutManager);
//        ItemTouchHelper  itemTouchHelper = new ItemTouchHelper(new MyItemTouchHandler(adapter));
//        itemTouchHelper.attachToRecyclerView(mRecyclerView);
//        mRecyclerView.setAdapter(adapter);
    }

}
