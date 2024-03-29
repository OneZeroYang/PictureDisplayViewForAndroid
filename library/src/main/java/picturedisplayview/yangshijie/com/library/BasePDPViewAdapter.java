package picturedisplayview.yangshijie.com.library;

import android.view.View;

import java.util.List;

/**
 *
 * @param <T>
 */
public abstract class BasePDPViewAdapter<T> {
    protected List<T> data=null;
    private int view;

    public List<T> getData() {
        return data;
    }

    public void removeData(int a){
        data.remove(a);
    }

    public int getView() {
        return view;
    }

    public BasePDPViewAdapter( List<T> data) {
       this.data=data;
        view=setView();
    }
    protected abstract int setView();
    public abstract void BindView(BaseMyHolder holder, int position);
}
