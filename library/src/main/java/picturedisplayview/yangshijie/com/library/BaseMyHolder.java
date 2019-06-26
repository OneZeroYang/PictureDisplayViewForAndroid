package picturedisplayview.yangshijie.com.library;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public abstract class BaseMyHolder extends RecyclerView.ViewHolder {
    public TextView[] textViews;
    public EditText[] editTexts;
    public LinearLayout[] linearLayouts;
    public RelativeLayout[]relativeLayouts;
    public Button[] buttons;
    public ImageView[] imageViews;

    public RecyclerView[] recyclerViews;

    private View itemView;

    public View[] view;




    protected void initView(int[] a){
        view=new View[a.length];
        for (int b =0;b<a.length;b++){
            view[b]=itemView.findViewById(a[b]);
        }
    }

    public BaseMyHolder(View itemView) {
        super(itemView);
        this.itemView=itemView;
        //IntiMyHolder();
        BindMyHolder(itemView);
    }

   // protected abstract void IntiMyHolder();

    protected abstract void BindMyHolder(View itemView);

    protected void initTextView(int[] a){
        textViews=new TextView[a.length];
        for (int b =0;b<a.length;b++){
            textViews[b]=itemView.findViewById(a[b]);
        }
    }

    protected void initEditText(int[] a){
        editTexts=new EditText[a.length];
        for (int b =0;b<a.length;b++){
            editTexts[b]=itemView.findViewById(a[b]);
        }
    }
    protected void initLinearLayout(int[] a){
        linearLayouts=new LinearLayout[a.length];
        for (int b =0;b<a.length;b++){
            linearLayouts[b]=itemView.findViewById(a[b]);
        }
    }
    protected void initRelativeLayout(int[] a){
        relativeLayouts=new RelativeLayout[a.length];
        for (int b =0;b<a.length;b++){
            relativeLayouts[b]=itemView.findViewById(a[b]);
        }
    } protected void initButton(int[] a){
        buttons=new Button[a.length];
        for (int b =0;b<a.length;b++){
            buttons[b]=itemView.findViewById(a[b]);
        }
    }
    protected void initImageView(int[] a){
        imageViews=new ImageView[a.length];
        for (int b =0;b<a.length;b++){
            imageViews[b]=itemView.findViewById(a[b]);
        }
    }

    protected void initRecyclerView(int[] a){
        recyclerViews=new RecyclerView[a.length];
        for (int b =0;b<a.length;b++){
            recyclerViews[b]=itemView.findViewById(a[b]);
        }
    }




}
