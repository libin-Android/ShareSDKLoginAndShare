package com.example.administrator.day36;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adapters.MyViewPagerAdapter;
import fragments.MoveFragment;
import fragments.MoveFragment2;
import fragments.MoveFragment3;
import views.MyFrameLayout;

/**
 * 1、事件分发过程中，父控件在dispatchTouchEvent方法中返回的值为true时，不在向内部传递事件即事件被消费了（父控件的onTouchEvent方法也不执行），
 *                  当返回值为false时，事件继续向内部传递；
 *                  （当dispatchTouchEvent和onTouchEvent返回值为默认时）事件隧道式传递到最里面的view，然后再冒泡式到最外面的view处理事件；
 * 事件隧道式传递，冒泡式处理
 *
 *2、当给view设置setOnTouchListener监听，实现onTouch方法（默认情况返回值为false，但是drawerLayout默认返回true），在onTouch里面返回false时候才会调用view的onTouchEvent方法
 *      默认情况下在xml定义的view的onTouchEvent方法会默认执行（即view被绑定了onTouch方法）
 *3、 onTouch事件中：down事件返回值标记此次事件是否为点击事件（返回false，是点击事件；返回true，不记为点击事件），而up事件标记此次事件结束时间，也就是判断是否为长按。
        当down返回false，标记此次事件为点击事件，而up返回了true，则表示此次事件一直没有结束，也就是一直长按下去了，达到长按临界时间后，自然触发长按事件，而onClick事件没有触发到

 *4、执行的顺序是
 * 父控件的dispatchTouchEvent—>子控件的dispatchTouchEvent-->子控件的onTouch-->子控件的onTouchEvent-->子控件的onclick方法
 *
 *
 */
public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, View.OnClickListener, AdapterView.OnItemClickListener {

    private DrawerLayout drawerLayout;
    private ImageView imageView2;
    private PopupWindow popupWindow;
    private ViewPager viewPager;
    private String TAG=MainActivity.class.getSimpleName();
    private ListView myListView;
    private MyFrameLayout myFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.main_drawerLayout);
        myFrameLayout = ((MyFrameLayout) findViewById(R.id.main_frameLayout));
        ImageView imageView1 = (ImageView) findViewById(R.id.main_imageView1);
        imageView2 = (ImageView) findViewById(R.id.main_imageView2);
        /**
         * 给listview加header
         */
        myListView = (ListView) findViewById(R.id.main_listview);
        TextView textView=new TextView(this);
        textView.setText("浏览足迹");
        textView.setGravity(Gravity.CENTER);
        myListView.addHeaderView(textView);
        myListView.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,new String[]{"sss","ssss"}));
        myListView.setOnItemClickListener(this);
        /**
         * 给图片加监听事件
         */
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        //设置drawerLayout不可以侧滑显示
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        List<Fragment> list=new ArrayList<>();
        MoveFragment fragment1=new MoveFragment();
        fragment1.setText("第一个页面");
        list.add(fragment1);
        MoveFragment2 fragment2=new MoveFragment2();
        fragment2.setText("第二个页面");
        list.add(fragment2);
        MoveFragment3 fragment3=new MoveFragment3();
        fragment3.setText("第三个页面");
        list.add(fragment3);
        List<String> list_title=new ArrayList<>();
        list_title.add("商品");
        list_title.add("详情");
        list_title.add("评论");
        //设置TabLayout的模式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
        MyViewPagerAdapter adapter=new MyViewPagerAdapter(getSupportFragmentManager(),list,list_title);
        viewPager.setAdapter(adapter);
        tabLayout.addTab(tabLayout.newTab().setText(list_title.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(list_title.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(list_title.get(2)));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(this);
    }

    /**
     * --------------------------TabLayout监听事件-------------------------------------
     * @param tab
     */
    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    /**
     * 事件的监听方法
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_imageView1:
                    drawerLayout.openDrawer(Gravity.RIGHT);
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN,GravityCompat.END);    //解除锁定
                break;
            case R.id.main_imageView2:
                if (popupWindow!=null&&popupWindow.isShowing()){
                    popupWindow.dismiss();
                }else {
                    View view = LayoutInflater.from(this).inflate(R.layout.popupwindow, null);
                    popupWindow=new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT ,true);
                    setTouchListener(view);//给popupwindow设置监听的方法
                    myFrameLayout.setPopupwindow(popupWindow);
                    popupWindow.setTouchable(true);//设置PopupWindow可触摸
                    popupWindow.setFocusable(false);//获取焦点
                    popupWindow.setOutsideTouchable(false);//设置非PopupWindow区域是否可触摸
                    //设置popwindow出现和消失动画
                    popupWindow.setAnimationStyle(R.style.anim_menu_bottombar);
                    popupWindow.showAsDropDown(imageView2);
                }
                /**
                 * MainActivity为测试用的activity
                 */
//                Intent intent=new Intent(this,Main2Activity.class);
//                startActivity(intent);
                break;
            case R.id.popup_imageView1:
                Intent intent=new Intent(this,Main2Activity.class);
                popupWindow.dismiss();
                startActivity(intent);
                break;
        }
    }

    /**
     * 给popupwindow的item设置监听的方法
     * @param view
     */
    private void setTouchListener(View view) {
        ImageView imageView1 = (ImageView) view.findViewById(R.id.popup_imageView1);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.popup_imageView2);
        ImageView imageView3 = (ImageView) view.findViewById(R.id.popup_imageView3);
        ImageView imageView4 = (ImageView) view.findViewById(R.id.popup_imageView4);
        ImageView imageView5 = (ImageView) view.findViewById(R.id.popup_imageView5);
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);
        imageView4.setOnClickListener(this);
        imageView5.setOnClickListener(this);
    }

    /**
     * listview的item监听事件
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(this,Main2Activity.class);
        //设置drawerLayout不可以侧滑显示
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        startActivity(intent);
    }
}
