package picturedisplayview.yangshijie.com.library;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

public abstract class ItemTouchAdapter<T> extends MyItemTouchHandler.ItemTouchAdapterImpl {
    private Context context;
    private List<T> list;
    public ItemTouchAdapter(Context context, List<T> list){
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public BaseMyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(getView(),viewGroup, false);
        return new BaseMyHolder(view);
    }
    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (list==null){
            throw new RuntimeException("ItemTouchAdapter<T> 数据源为空！");
        }
        Collections.swap(list,fromPosition,toPosition);
    }
    @Override
    public void onItemRemove(int position) {
        if (list==null){
            throw new RuntimeException("ItemTouchAdapter<T> 数据源为空！");
        }
        list.remove(position);
    }
    @Override
    public void onBindViewHolder(@NonNull BaseMyHolder baseMyHolder, int i) {
        BindView(baseMyHolder, i);
    }
    @Override
    public int getItemCount() {
        if (list==null){
            throw new RuntimeException("ItemTouchAdapter<T> 数据源为空！");
        }
        return list.size();
    }



    protected abstract int getView();
    protected abstract void BindView(BaseMyHolder holder, int position);
}
