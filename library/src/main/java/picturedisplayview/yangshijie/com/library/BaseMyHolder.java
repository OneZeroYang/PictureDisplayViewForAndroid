package picturedisplayview.yangshijie.com.library;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

public class BaseMyHolder extends RecyclerView.ViewHolder {
    private Map<Integer, View> map;
    private View itemView;

    public BaseMyHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        map = new HashMap<>();

    }
    /**
     * 查找View
     * @param id
     * @param <T>
     * @return
     */
    public <T extends View> T findView(@IdRes int id) {
        if (map == null || itemView == null) {
            throw new RuntimeException("BaseMyHolder is init ?");
        }
        if (map.get(id) == null) {
            map.put(id, itemView.findViewById(id));
        }
        return (T) map.get(id);
    }

    /**
     * 绑定View
     * @param id
     */
    public void bindView(@IdRes int... id) {
        if (map == null || itemView == null) {
            throw new RuntimeException("BaseMyHolder is init ?");
        }
        for (int arg : id) {
            if (map.get(id) == null)map.put(arg,itemView.findViewById(arg));
        }
    }
}

