package picturedisplayview.yangshijie.com.library;

import android.support.v7.widget.RecyclerView;
 public interface MyItemTouchHandlerCallback {
    void onMove(RecyclerView.ViewHolder viewHolder, int actionState);
    void onPutDown(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder);
}
