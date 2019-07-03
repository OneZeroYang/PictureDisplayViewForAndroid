package picturedisplayview.yangshijie.com.library;

import java.util.List;

public interface PDPViewAdapterCall<T> {
    void add();
    void event(BaseMyHolder holder,int view,int p);
    void addItme(List<T> d);
}
