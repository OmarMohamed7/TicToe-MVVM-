
package androidx.databinding;
import com.example.tictoc.BR;
import android.util.SparseArray;
class DataBinderMapperImpl extends androidx.databinding.DataBinderMapper {
    public DataBinderMapperImpl() {
    }
    @Override
    public androidx.databinding.ViewDataBinding getDataBinder(androidx.databinding.DataBindingComponent bindingComponent, android.view.View view, int layoutId) {
        switch(layoutId) {
                case com.example.tictoc.R.layout.activity_main:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/activity_main_0".equals(tag)) {
                            return new com.example.tictoc.databinding.ActivityMainBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for activity_main is invalid. Received: " + tag);
                }
        }
        return null;
    }
    @Override
    public androidx.databinding.ViewDataBinding getDataBinder(androidx.databinding.DataBindingComponent bindingComponent, android.view.View[] views, int layoutId) {
        switch(layoutId) {
        }
        return null;
    }
    @Override
    public int getLayoutId(String tag) {
        if (tag == null) {
            return 0;
        }
        final int code = tag.hashCode();
        switch(code) {
            case 423753077: {
                if(tag.equals("layout/activity_main_0")) {
                    return com.example.tictoc.R.layout.activity_main;
                }
                break;
            }
        }
        return 0;
    }
    @Override
    public String convertBrIdToString(int id) {
        final String value = InnerBrLookup.sKeys.get(id);
        return value;
    }
    private static class InnerBrLookup {
        static final SparseArray<String> sKeys = new SparseArray<>();
        static {
                sKeys.put(0, "_all");
                sKeys.put(0, "_all");
                sKeys.put(1, "gameViewModel");
        }
    }
}