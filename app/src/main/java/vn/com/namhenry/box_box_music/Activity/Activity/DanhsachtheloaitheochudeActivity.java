package vn.com.namhenry.box_box_music.Activity.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.namhenry.box_box_music.Activity.Adapter.DanhsachtatcachudeAdapter;
import vn.com.namhenry.box_box_music.Activity.Adapter.DanhsachtheloaitheochudeAdapter;
import vn.com.namhenry.box_box_music.Activity.Model.ChuDe;
import vn.com.namhenry.box_box_music.Activity.Model.TheLoai;
import vn.com.namhenry.box_box_music.Activity.Service.APIService;
import vn.com.namhenry.box_box_music.Activity.Service.Dataservice;
import vn.com.namhenry.box_box_music.R;

public class DanhsachtheloaitheochudeActivity extends AppCompatActivity {
ChuDe chuDe;
RecyclerView recyclerViewtheloaitheochude;
Toolbar toolbartheloaitheochude;
DanhsachtheloaitheochudeAdapter danhsachtheloaitheochudeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtheloaitheochude);
        GetIntent();
        init();
        GetData();
    }

    private void GetData() {
        Dataservice dataservice= APIService.getservice();
        Call<List<TheLoai>> callback=dataservice.GetTheloaitheochude(chuDe.getIdChuDe());
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> mangtheloai= (ArrayList<TheLoai>) response.body();
             danhsachtheloaitheochudeAdapter=new DanhsachtheloaitheochudeAdapter(DanhsachtheloaitheochudeActivity.this,mangtheloai);
             recyclerViewtheloaitheochude.setLayoutManager(new GridLayoutManager(DanhsachtheloaitheochudeActivity.this,2));
             recyclerViewtheloaitheochude.setAdapter(danhsachtheloaitheochudeAdapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewtheloaitheochude=findViewById(R.id.recyclerviewtheloaitheochude);
        toolbartheloaitheochude=findViewById(R.id.toolbartheloaitheochude);
        setSupportActionBar(toolbartheloaitheochude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTenChuDe());
        toolbartheloaitheochude.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void GetIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("chude")){
            chuDe= (ChuDe) intent.getSerializableExtra("chude");


        }
    }
}
