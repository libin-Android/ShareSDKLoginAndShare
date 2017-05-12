package adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * viewpager的adapter
 *
 * Created by 李膑 on 2016/7/14.
 */
public class MyViewPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> list;
    private List<String> list_Title; //tab名的列表
    public MyViewPagerAdapter(FragmentManager fm,List<Fragment> list,List<String> list_Title) {
        super(fm);
        this.list=list;
        this.list_Title=list_Title;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list_Title.get(position);
    }
}
