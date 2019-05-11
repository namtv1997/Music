package vn.com.namhenry.box_box_music.Activity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import vn.com.namhenry.box_box_music.Activity.Activity.DanhsachbaihatActivity;
import vn.com.namhenry.box_box_music.Activity.Model.Quangcao;
import vn.com.namhenry.box_box_music.R;

public class BannerAdapter extends PagerAdapter {
    Context context;
    ArrayList<Quangcao> arrayListbanner;

    @Override
    public int getCount() {
        return arrayListbanner.size();
    }

    public BannerAdapter(Context context, ArrayList<Quangcao> arrayListbanner) {
        this.context = context;
        this.arrayListbanner = arrayListbanner;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view ==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.dong_banner,null);
        ImageView imgbackgroundbanner =view.findViewById(R.id.imageviewbackgroundbanner);
        ImageView imgsongbanner =view.findViewById(R.id.imageviewbanner);
        TextView txttitlesongbanner =view.findViewById(R.id.textviewtitlebannerbaihat);
        TextView txtnoidung =view.findViewById(R.id.textviewnoidung);
        Picasso.with(context).load(arrayListbanner.get(position).getHinhAnh()).into(imgbackgroundbanner);
        Picasso.with(context).load(arrayListbanner.get(position).getHinhBaiHat()).into(imgsongbanner);
        txttitlesongbanner.setText(arrayListbanner.get(position).getTenBaiHat());
        txtnoidung.setText(arrayListbanner.get(position).getNoiDung());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent=new Intent(context, DanhsachbaihatActivity.class);
                intent.putExtra("banner",arrayListbanner.get(position));

                context.startActivity(intent);
            }
        });
        container.addView(view);
        return view;
    }
     //khi vuot page đen page cuoi cùng mà ko cho finish thi bi loi nen phải dung destroyItem
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
