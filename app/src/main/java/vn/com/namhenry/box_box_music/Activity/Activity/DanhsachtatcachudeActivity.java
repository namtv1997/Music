package vn.com.namhenry.box_box_music.Activity.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.namhenry.box_box_music.Activity.Adapter.DanhsachtatcachudeAdapter;
import vn.com.namhenry.box_box_music.Activity.Model.ChuDe;
import vn.com.namhenry.box_box_music.Activity.Service.APIService;
import vn.com.namhenry.box_box_music.Activity.Service.Dataservice;
import vn.com.namhenry.box_box_music.R;

public class DanhsachtatcachudeActivity extends AppCompatActivity {
    RecyclerView recyclerViewtatcachude;
    Toolbar toolbartatcachude;
    DanhsachtatcachudeAdapter danhsachtatcachudeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtatcachude);
        init();
        GetData();
    }

    private void GetData() {
        Dataservice dataservice = APIService.getservice();
        Call<List<ChuDe>> callback = dataservice.GetAllChuDe();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> mangchude = (ArrayList<ChuDe>) response.body();
                danhsachtatcachudeAdapter = new DanhsachtatcachudeAdapter(DanhsachtatcachudeActivity.this,mangchude);
                recyclerViewtatcachude.setLayoutManager(new GridLayoutManager(DanhsachtatcachudeActivity.this,1));
                recyclerViewtatcachude.setAdapter(danhsachtatcachudeAdapter);
            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewtatcachude = findViewById(R.id.recycleviewallchude);
        toolbartatcachude = findViewById(R.id.toolbarallchude);
        setSupportActionBar(toolbartatcachude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Chủ Đề");
        toolbartatcachude.setTitleTextColor(getResources().getColor(R.color.mautim));
        toolbartatcachude.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
