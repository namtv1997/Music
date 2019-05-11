package vn.com.namhenry.box_box_music.Activity.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.namhenry.box_box_music.Activity.Activity.DanhsachbaihatActivity;
import vn.com.namhenry.box_box_music.Activity.Activity.DanhsachtatcachudeActivity;
import vn.com.namhenry.box_box_music.Activity.Activity.DanhsachtheloaitheochudeActivity;
import vn.com.namhenry.box_box_music.Activity.Model.ChuDe;
import vn.com.namhenry.box_box_music.Activity.Model.TheLoai;
import vn.com.namhenry.box_box_music.Activity.Model.TheLoaitrongngay;
import vn.com.namhenry.box_box_music.Activity.Service.APIService;
import vn.com.namhenry.box_box_music.Activity.Service.Dataservice;
import vn.com.namhenry.box_box_music.R;

public class fragment_chude_theloai extends Fragment {
    View view;
    HorizontalScrollView horizontalScrollView;
    TextView txtxemthemchudetheloai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chude_theloai, container, false);
        horizontalScrollView = view.findViewById(R.id.horizontalScrollView);
        txtxemthemchudetheloai = view.findViewById(R.id.textviewxemthem);
        txtxemthemchudetheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), DanhsachtatcachudeActivity.class);
                startActivity(intent);
            }
        });
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getservice();
        Call<TheLoaitrongngay> callback = dataservice.GetCategoryMusic();
        callback.enqueue(new Callback<TheLoaitrongngay>() {
            @Override
            public void onResponse(Call<TheLoaitrongngay> call, Response<TheLoaitrongngay> response) {
                TheLoaitrongngay theLoaitrongngay=response.body();
                final ArrayList<ChuDe> chuDeArrayList=new ArrayList<>();
                chuDeArrayList.addAll(theLoaitrongngay.getChuDe());
                final ArrayList<TheLoai> theLoaiArrayList=new ArrayList<>();
                theLoaiArrayList.addAll(theLoaitrongngay.getTheLoai());
                LinearLayout linearLayout =new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams  layout=new LinearLayout.LayoutParams(580,250);
                layout.setMargins(10,20,10,30);
                for (int i=0;i<(chuDeArrayList.size());i++){
                    CardView cardView=new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView=new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (chuDeArrayList.get(i).getHinhChuDe()!=null){
                        Picasso.with(getActivity()).load(chuDeArrayList.get(i).getHinhChuDe()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    final int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent  intent=new Intent(getActivity(), DanhsachtheloaitheochudeActivity.class);
                            intent.putExtra("chude",chuDeArrayList.get(finalI));
                            startActivity(intent);
                        }
                    });
                }
                for (int j=0;j<(chuDeArrayList.size());j++){
                    CardView cardView=new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView=new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (theLoaiArrayList.get(j).getHinhTheLoai()!=null){
                        Picasso.with(getActivity()).load(theLoaiArrayList.get(j).getHinhTheLoai()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);

                    final int finalJ = j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent  intent=new Intent(getActivity(), DanhsachbaihatActivity.class);
                            intent.putExtra("idtheloai",theLoaiArrayList.get(finalJ
                            ));
                            startActivity(intent);
                        }
                    });
                }
                horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<TheLoaitrongngay> call, Throwable t) {

            }
        });

    }
}
