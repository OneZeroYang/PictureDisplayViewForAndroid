package picturedisplayview.yangshijie.com.library;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PDPViewAdapter<T> extends BasePDPViewAdapter<T> {
   // private List<T> data;
    private T t;
    private Context context;

    private PDPViewAdapterCall<T> call;

    public void setCall(PDPViewAdapterCall call) {
        this.call = call;
    }

    public PDPViewAdapter(Context context,List<T> data) {
        super(data);
        t=(T)new Object();
        this.context=context;
        data.add(t);
        this.data = data;
    }



    @Override
    protected int setView() {
        return R.layout.pdpview_itme;
    }

    public void addItme(List<T> d){
        if (call!=null){
            call.addItme(d);
        }
    }

    @Override
    public void BindView(BaseMyHolder holder, int position) {
        if (holder instanceof LastMyHolder) {
            View view = holder.findView(R.id.add_image);
            view .setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   if (call!=null){
                       call.add();
                   }
               }
           });

        } else {
            View view = holder.findView(R.id.xx);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (call!=null){
                        call.event(holder,R.id.xx,position);
                    }
                }
            });
            ImageView imageView = holder.findView(R.id.image);
           // imageView.setImageResource(R.drawable.x);
            Glide.with(context).load((Uri) data.get(position)).into(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (call!=null){
                        call.event(holder,R.id.image,position);
                    }
                }
            });
        }
    }


}
