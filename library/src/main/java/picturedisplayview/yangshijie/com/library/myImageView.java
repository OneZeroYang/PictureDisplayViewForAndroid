package picturedisplayview.yangshijie.com.library;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import picturedisplayview.yangshijie.com.library.utils.ThreadUtil;
import picturedisplayview.yangshijie.com.library.utils.ToastUtils;

public class myImageView extends ImageView {
    private Context  context;

    private boolean isUp;

    public myImageView(Context context) {
        super(context);
        this.context=context;
    }

    public myImageView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }

    public myImageView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
    }

    public myImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context=context;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                isUp=false;
                ThreadUtil.runOnUIThread(()->{
                    if (!isUp){
                        ToastUtils.showToast(context,"长按触发！！");
                    }
                },500);
                break;
            case MotionEvent.ACTION_UP:
                isUp=true;
                break;
        }
        return super.onTouchEvent(event);
    }
}
