package picturedisplayview.yangshijie.com.library;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import java.util.List;

public class PDPView<T> extends RelativeLayout implements MyItemTouchHandlerCallback {
    private RecyclerView recyclerView;
    private Context context;
    private BasePDPViewAdapter<T> adapter;
    private RecyclerView.LayoutManager layout;
    private ItemTouchAdapter itemTouchAdapter;
    private final static String TAG = "PDPView:";
    private float AnimationCoefficient = 1.3f;
    private int AnimationTime = 200;
    private boolean isOpenAnimation = true;
    private int lastView = R.layout.lastview;
    private  MyItemTouchHandler myItemTouchHandler;
    private PDPViewCall call;


    public void setCall(PDPViewCall call) {
        this.call = call;
    }

    public void setLastView(int lastView) {
        this.lastView = lastView;
    }

    public PDPView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public PDPView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public PDPView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        recyclerView = new RecyclerView(context);
        recyclerView.setLayoutParams(new RecyclerView.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        this.addView(recyclerView);
    }

    public void setAdapter(BasePDPViewAdapter adapter) {
        if (adapter == null) {
            return;
        }
        if (adapter instanceof PDPViewAdapter) {
            this.layout = new GridLayoutManager(context, 4);
            PDPViewAdapterCall pdpViewAdapterCall = new PDPViewAdapterCall() {
                @Override
                public void add() {
                    //添加图片
                    if (call != null) {
                        call.addImage();
                    }
                }

                @Override
                public void event(BaseMyHolder holder, int view, int p) {
                    if (view == R.id.xx) {
                        itemTouchAdapter.onItemRemove(p);
                        itemTouchAdapter.notifyItemRemoved(p);
                        // adapter.removeData(p);
                        //移除图片
                        onUpdateCall();
                    } else if (view == R.id.image) {
                        //点击图片
                        //itemTouchAdapter.onBindViewHolder();
                        if (call != null) {
                            call.onImageEvent(holder, p);
                        }
                    }
                }

                @Override
                public void addItme(List d) {
                    if (itemTouchAdapter!=null){
                        List<T> d1 =d;
                        itemTouchAdapter.addItme(d1);
                    }
                }


            };
            ((PDPViewAdapter) adapter).setCall(pdpViewAdapterCall);
        }
        this.adapter = adapter;
        initDateView();
    }

    public void setLayoutManager(RecyclerView.LayoutManager layout) {
        if (layout == null) {
            return;
        }
        this.layout = layout;
        initDateView();
    }

    private void initDateView() {
        if (adapter != null & layout != null) {
            initItemTouchAdapter();
            updateView();
        } else {
            Log.d(TAG, "BasePDPViewAdapter or RecyclerView.LayoutManager is null");
        }
    }

    private void updateView() {
        recyclerView.setLayoutManager(layout);
        myItemTouchHandler = new MyItemTouchHandler(itemTouchAdapter, this);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(myItemTouchHandler);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(itemTouchAdapter);
        onUpdateCall();
    }

    private void onUpdateCall(){
        if (call!=null){
            call.onUpdate(itemTouchAdapter.getList());
        }
    }

    private void initItemTouchAdapter() {

        itemTouchAdapter = new ItemTouchAdapter(context, adapter.getData()) {
            @Override
            protected int lastView() {
                if (adapter instanceof PDPViewAdapter) {
                    return R.layout.lastview;
                }
                return 0;
            }
            @Override
            protected int getView() {
                return adapter.getView();
            }

            @Override
            protected void BindView(BaseMyHolder holder, int position) {
                adapter.BindView(holder, position);
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
        if (isOpenAnimation) {
            ScaleAnimation animation = new ScaleAnimation(1f, AnimationCoefficient, 1f, AnimationCoefficient, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(AnimationTime);
            animation.setFillAfter(true);
            viewHolder.itemView.setAnimation(animation);
            animation.start();
        }
    }

    @Override
    public void onPutDown(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (isOpenAnimation) {
            ScaleAnimation animation = new ScaleAnimation(AnimationCoefficient, 1f, AnimationCoefficient, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(AnimationTime);
            animation.setFillAfter(true);
            viewHolder.itemView.setAnimation(animation);
            animation.start();
        }
        onUpdateCall();
    }


}
