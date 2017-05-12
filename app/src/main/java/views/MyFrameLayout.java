package views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

/**
 * 自定义的事件处理的MyFrameLayout
 * Created by Administrator on 2016/7/18.
 */
public class MyFrameLayout extends FrameLayout {
    private PopupWindow popupWindow;
    private String TAG=MyFrameLayout.class.getSimpleName();

    public MyFrameLayout(Context context) {
        this(context,null);
    }

    public MyFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setPopupwindow(PopupWindow popupwindow){
        this.popupWindow=popupwindow;
    }

    /**
     * 事件拦断后不会再调用onTouchEvent方法
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Log.e(TAG , "dispatchTouchEvent: 111111111");
        if (null!=popupWindow&&popupWindow.isShowing()){
//            Log.e(TAG , "dispatchTouchEvent: 2222222222");
            popupWindow.dismiss();
            return true;
        }else {
//            Log.e(TAG , "dispatchTouchEvent: 3333333333");
            return super.dispatchTouchEvent(ev);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return super.onInterceptTouchEvent(ev);
    }
}
