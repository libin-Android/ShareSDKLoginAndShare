package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.day36.R;

import java.util.ArrayList;
import java.util.List;

import adapters.MyViewPagerAdapter;

/**
 * Created by Administrator on 2016/7/14.
 */
public class MoveFragment3 extends Fragment {
    private static String str;


    public void setText(String str){
        this.str=str;
    }
    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout view = ((LinearLayout) inflater.inflate(R.layout.fragment_item3, null));
        initView(view);
        return view;
    }

    private void initView(LinearLayout view) {
        ViewPager viewPager = (ViewPager)view.findViewById(R.id.fragment3_viewpager);
        TabLayout tabLayout = (TabLayout)view.findViewById(R.id.fragment3_tablayout);
        List<Fragment> list=new ArrayList<>();
        MoveFragment fragment1=new MoveFragment();
        fragment1.setText("全部页面");
        list.add(fragment1);
        MoveFragment fragment2=new MoveFragment();
        fragment2.setText("好评页面");
        list.add(fragment2);
        MoveFragment fragment3=new MoveFragment();
        fragment3.setText("中评页面");
        list.add(fragment3);
        MoveFragment fragment4=new MoveFragment();
        fragment4.setText("差评页面");
        list.add(fragment4);
        /**
         * tablayout要添加的tab信息
         */
        List<String> list_title=new ArrayList<>();
        list_title.add("全部");
        list_title.add("好评");
        list_title.add("中评");
        list_title.add("差评");
        //设置TabLayout的模式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
        MyViewPagerAdapter adapter=new MyViewPagerAdapter(getChildFragmentManager(),list,list_title);
        viewPager.setAdapter(adapter);
        tabLayout.addTab(tabLayout.newTab().setText(list_title.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(list_title.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(list_title.get(2)));
        tabLayout.addTab(tabLayout.newTab().setText(list_title.get(3)));
        tabLayout.setupWithViewPager(viewPager);
    }
}
