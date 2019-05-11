package vn.com.namhenry.box_box_music.Activity.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vn.com.namhenry.box_box_music.Activity.Adapter.MainViewPagerAdapter;
import vn.com.namhenry.box_box_music.Activity.Fragment.Fragment_Tim_Kiem;
import vn.com.namhenry.box_box_music.Activity.Fragment.Fragment_Trang_Chu;
import vn.com.namhenry.box_box_music.R;

public class MainActivity extends AppCompatActivity {
TabLayout tabLayout;
ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        init();
    }

    private void init() {
        MainViewPagerAdapter mainViewPagerAdapter=new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addfragment(new Fragment_Trang_Chu(),"Trang Chủ");
        mainViewPagerAdapter.addfragment(new Fragment_Tim_Kiem(),"Tìm Kiếm");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconsearch);
    }

    private void anhxa() {
        tabLayout=findViewById(R.id.myTabLayout);
        viewPager=findViewById(R.id.myViewpaper);
    }
}
