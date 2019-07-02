package picturedisplayview.yangshijie.com.library;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

public class PDPView extends RelativeLayout implements MyItemTouchHandlerCallback{
    private RecyclerView recyclerView;
    private Context context;
    private BasePDPViewAdapter adapter;
    private RecyclerView.LayoutManager layout;
    private ItemTouchAdapter itemTouchAdapter;
    private final static String TAG="PDPView:";
    private float AnimationCoefficient=1.3f;
    private int  AnimationTime=200;

    private boolean isOpenAnimation=true;

    public PDPView(Context context) {
        super(context);
        this.context=context;
        init();
    }

    public PDPView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }

    public PDPView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        init();
    }
    private void init(){
        recyclerView=new RecyclerView(context);
        recyclerView.setLayoutParams(new RecyclerView.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        this.addView(recyclerView);
    }
    public void setAdapter(BasePDPViewAdapter adapter){
        if (adapter==null){
            return;
        }
        this.adapter=adapter;
        initDateView();
    }
    public void setLayoutManager(RecyclerView.LayoutManager layout){
        if (layout==null){
            return;
        }
        this.layout=layout;
        initDateView();
    }
    private void initDateView() {
        if (adapter!=null&layout!=null){
            initItemTouchAdapter();
            updateView();
        }else {
            Log.d(TAG,"BasePDPViewAdapter or RecyclerView.LayoutManager is null");
        }
    }
    private void updateView() {
        recyclerView.setLayoutManager(layout);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new MyItemTouchHandler(itemTouchAdapter,this));
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(itemTouchAdapter);
    }
    private void initItemTouchAdapter() {
        itemTouchAdapter=new ItemTouchAdapter(context,adapter.getData()) {
            @Override
            protected int getView() {
                return adapter.getView();
            }

            @Override
            protected void BindView(BaseMyHolder holder, int position) {
                adapter.BindView(holder,position);
            }
        };
    }

    public void setAnimationCoefficient(float animationCoefficient) {
        AnimationCoefficient = animationCoefficient;
    }

    public void setAnimationTime(int animationTime) {
        AnimationTime = animationTime;
    }

    public void setOpenAnimation(boolean openAnimation) {
        isOpenAnimation = openAnimation;
    }

    @Override
    public void onMove(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (isOpenAnimation){
            ScaleAnimation animation=new ScaleAnimation(1f,AnimationCoefficient,1f,AnimationCoefficient,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
            animation.setDuration(AnimationTime);
            animation.setFillAfter(true);
            viewHolder.itemView.setAnimation(animation);
            animation.start();
        }
    }

    @Override
    public void onPutDown(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (isOpenAnimation){
            ScaleAnimation animation=new ScaleAnimation(AnimationCoefficient,1f,AnimationCoefficient,1f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
            animation.setDuration(AnimationTime);
            animation.setFillAfter(true);
            viewHolder.itemView.setAnimation(animation);
            animation.start();
        }
    }


}
