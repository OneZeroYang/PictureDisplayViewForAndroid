package picturedisplayview.yangshijie.com.library;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

public abstract class ItemTouchAdapter extends MyItemTouchHandler.ItemTouchAdapterImpl {
    private Context context;



    public ItemTouchAdapter(Context context){
        this.context=context;
    }

//    @Override
//    public void onItemMove(int fromPosition, int toPosition) {
//        //移动时交换位置
//        Collections.swap(,fromPosition,toPosition);
//    }

    @NonNull
    @Override
    public BaseMyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(getView(),viewGroup, false);
        return initView(view);
    }

    protected abstract int getView();
    protected abstract BaseMyHolder initView(View view);
    protected abstract void BindView(BaseMyHolder holder, int position);
    protected abstract int setItemCount();


    @Override
    public void onBindViewHolder(@NonNull BaseMyHolder baseMyHolder, int i) {
        BindView(baseMyHolder, i);
    }

    @Override
    public int getItemCount() {
        return setItemCount();
    }


}
