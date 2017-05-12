package com.example.administrator.day36;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    private LinearLayout button;
    private PopupWindow popupWindow;
    private String TAG=Main2Activity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {
        button = (LinearLayout) findViewById(R.id.btn);
        button.setOnClickListener(this);
//        button.setOnTouchListener(this);
    }

    @Override
    public void onClick(View v) {
        if (popupWindow!=null&&popupWindow.isShowing()){
            popupWindow.dismiss();
            Log.e("onClick: ","不显示" );
        }else {
            View view = LayoutInflater.from(this).inflate(R.layout.popupwindow, null);
            popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            popupWindow.setTouchable(true);//设置PopupWindow可触摸
            popupWindow.setFocusable(false);//获取焦点
            popupWindow.setOutsideTouchable(true);//设置非PopupWindow区域是否可触摸
            //设置popwindow出现和消失动画
            popupWindow.setAnimationStyle(R.style.anim_menu_bottombar);
            Log.e("onClick: ","显示" );
            popupWindow.showAsDropDown(button);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.e(TAG, "onTouch: ");
        return false;
    }

    /**
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent: ");
        return super.onTouchEvent(event);
    }

    /**
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "dispatchTouchEvent: ");
        return super.dispatchTouchEvent(ev);
    }
}
