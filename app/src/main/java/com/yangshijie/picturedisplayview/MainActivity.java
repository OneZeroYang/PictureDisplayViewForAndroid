package com.yangshijie.picturedisplayview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import picturedisplayview.yangshijie.com.library.BaseMyHolder;
import picturedisplayview.yangshijie.com.library.ItemTouchAdapter;
import picturedisplayview.yangshijie.com.library.MyItemTouchHandler;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    private ItemTouchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);

       List<String> data=new ArrayList<>();
       for (int a=0;a<20;a++){
           data.add(a+"");
       }


        adapter=new ItemTouchAdapter(this) {
            @Override
            protected int getView() {
                return R.layout.test;
            }

            @Override
            protected BaseMyHolder initView(View view) {
                return new BaseMyHolder(view) {
                    @Override
                    protected void BindMyHolder(View itemView) {
                        initTextView(new int[]{R.id.tv});
                    }
                };
            }

            @Override
            protected void BindView(BaseMyHolder holder, int position) {
                holder.textViews[0].setText(data.get(position));
            }

            @Override
            protected int setItemCount() {
                return data.size();
            }

            @Override
            public void onItemMove(int fromPosition, int toPosition) {
                Collections.swap(data,fromPosition,toPosition);
            }

            @Override
            public void onItemRemove(int position) {
                data.remove(position);
            }
        };

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);

        mRecyclerView.setLayoutManager(gridLayoutManager);

       // MyItemTouchHandler myItemTouchHandler = new MyItemTouchHandler(adapter);

        ItemTouchHelper  itemTouchHelper = new ItemTouchHelper(new MyItemTouchHandler(adapter));
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        mRecyclerView.setAdapter(adapter);
    }
}
