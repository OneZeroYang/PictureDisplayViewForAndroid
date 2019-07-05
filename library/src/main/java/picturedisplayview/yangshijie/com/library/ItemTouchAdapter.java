package picturedisplayview.yangshijie.com.library;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class ItemTouchAdapter<T> extends MyItemTouchHandler.ItemTouchAdapterImpl {
    private Context context;
    private List<T> list;
    private boolean isHaveLastView=false;
    private int lastView=R.layout.lastview;

    private PDPViewCall call;

    public ItemTouchAdapter(Context context, List<T> list){
        this.context=context;
        this.list=list;
    }

    @Override
    public void setCall(PDPViewCall call) {
        super.setCall(call);
        this.call=call;
    }

    public List<T> getList() {
        return list;
    }

    public void addItme(List<T> list){

        for (int a=0;a<list.size();a++){
            this.list.add(0,list.get(a));
        }
        for(int a=0;a<this.list.size();a++){
            notifyItemChanged(a);
        }
        if (call!=null){
            call.onUpdate(this.list);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position==list.size()-1){
            return 1;
        }else {
            return 0;
        }
    }

    @NonNull
    @Override
    public BaseMyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i==1){
            if (lastView()!=0){
                View view = LayoutInflater.from(context).inflate(lastView(),viewGroup, false);
                return new LastMyHolder(view);
            }
        }
        View view = LayoutInflater.from(context).inflate(getView(),viewGroup, false);
        return new BaseMyHolder(view);
    }

    protected abstract int lastView();


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
