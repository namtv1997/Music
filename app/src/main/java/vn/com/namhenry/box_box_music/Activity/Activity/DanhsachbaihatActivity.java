package vn.com.namhenry.box_box_music.Activity.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.StrictMode;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.namhenry.box_box_music.Activity.Adapter.Danhsachbaihatadapter;
import vn.com.namhenry.box_box_music.Activity.Model.Album;
import vn.com.namhenry.box_box_music.Activity.Model.Baihat;
import vn.com.namhenry.box_box_music.Activity.Model.Playlist;
import vn.com.namhenry.box_box_music.Activity.Model.Quangcao;
import vn.com.namhenry.box_box_music.Activity.Model.TheLoai;
import vn.com.namhenry.box_box_music.Activity.Service.APIService;
import vn.com.namhenry.box_box_music.Activity.Service.Dataservice;
import vn.com.namhenry.box_box_music.R;

public class
DanhsachbaihatActivity extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerViewdanhsachbaihat;
    android.support.v7.widget.Toolbar toolbar;
   // ImageView imgdanhsachcakhuc;
    ArrayList<Baihat> mangbaihat;
    Quangcao quangcao;
    Playlist playlist;
    TheLoai theLoai;
    Album album;
    Danhsachbaihatadapter danhsachbaihatadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachbaihat);
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        DataIntent();
        anhXa();
        init();
        if (quangcao != null && !quangcao.getTenBaiHat().equals("")) {
            setValueInView(quangcao.getTenBaiHat(), quangcao.getHinhBaiHat());
            GetDataQuangcao(quangcao.getID());
        }
        if (playlist != null && !playlist.getTen().equals("")) {
            setValueInView(playlist.getTen(), playlist.getHinhPlaylist());
            GetDataPlaylist(playlist.getIdPlaylist());
        }
        if (theLoai != null && !theLoai.getTenTheLoai().equals("")) {
            setValueInView(theLoai.getTenTheLoai(), theLoai.getHinhTheLoai());
            GetDataTheLoai(theLoai.getIdTheLoai());
        }
        if (album != null && !album.getTenAlbum().equals("")) {
            setValueInView(album.getTenAlbum(), album.getHinhAlbum());
            GetDataAlbum(album.getIdAlbum());
        }
    }

    private void GetDataAlbum(String idAlbum) {
        Dataservice dataservice = APIService.getservice();
        Call<List<Baihat>> callback = dataservice.GetDanhsachbaihattheoalbum(idAlbum);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat = (ArrayList<Baihat>) response.body();
                danhsachbaihatadapter = new Danhsachbaihatadapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatadapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }


    private void GetDataTheLoai(String idTheLoai) {
        Dataservice dataservice = APIService.getservice();
        Call<List<Baihat>> callback = dataservice.GetDanhsachbaihattheotheloai(idTheLoai);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat = (ArrayList<Baihat>) response.body();
                danhsachbaihatadapter = new Danhsachbaihatadapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatadapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void GetDataPlaylist(String idPlaylist) {
        Dataservice dataservice = APIService.getservice();
        Call<List<Baihat>> callback = dataservice.GetDanhsachbaihattheoplaylist(idPlaylist);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat = (ArrayList<Baihat>) response.body();
                danhsachbaihatadapter = new Danhsachbaihatadapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatadapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void setValueInView(String ten, String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        try {
            URL url = new URL(hinh);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                collapsingToolbarLayout.setBackground(bitmapDrawable);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
      //  Picasso.with(this).load(hinh).into(imgdanhsachcakhuc);
    }

    private void GetDataQuangcao(String id) {
        Dataservice dataservice = APIService.getservice();
        Call<List<Baihat>> callback = dataservice.GetDanhsachbaihattheoquangcao(id);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat = (ArrayList<Baihat>) response.body();
                danhsachbaihatadapter = new Danhsachbaihatadapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatadapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        floatingActionButton.setEnabled(false);
    }

    private void anhXa() {
        coordinatorLayout = findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);
        floatingActionButton = findViewById(R.id.floatingactionbutton);
        recyclerViewdanhsachbaihat = findViewById(R.id.recycledanhsachbaihat);
        toolbar = findViewById(R.id.toolbardanhsach);
     //   imgdanhsachcakhuc = findViewById(R.id.imageviewdanhsachcakhuc);

    }

    private void DataIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("banner")) {
                quangcao = (Quangcao) intent.getSerializableExtra("banner");
            }
            if (intent.hasExtra("itemplaylist")) {
                playlist = (Playlist) intent.getSerializableExtra("itemplaylist");
            }
            if (intent.hasExtra("idtheloai")) {
                theLoai = (TheLoai) intent.getSerializableExtra("idtheloai");
            }
            if (intent.hasExtra("album")) {
                album = (Album) intent.getSerializableExtra("album");
            }
        }
    }

    private void eventClick() {
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent=new Intent(DanhsachbaihatActivity.this,PlayNhacActivity.class)  ;
              intent.putExtra("cacbaihat",mangbaihat);
              startActivity(intent);
            }
        });
    }
}
