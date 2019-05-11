package vn.com.namhenry.box_box_music.Activity.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragmentArrayList=new ArrayList<>();
    private ArrayList<String> stringArrayList=new ArrayList<>();
    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return stringArrayList.size();
    }
    public void addfragment(Fragment fragment,String title){
        fragmentArrayList.add(fragment);
        stringArrayList.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return stringArrayList.get(position);
    }
}
