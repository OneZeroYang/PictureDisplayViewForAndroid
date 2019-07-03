package picturedisplayview.yangshijie.com.library;

import java.util.List;

public interface PDPViewCall {
    void addImage();
    void onImageEvent(BaseMyHolder holder,int p);
    void onUpdate(List<Object> list);
}
