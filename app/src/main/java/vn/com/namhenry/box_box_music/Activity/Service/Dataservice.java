package vn.com.namhenry.box_box_music.Activity.Service;

import android.os.StrictMode;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import vn.com.namhenry.box_box_music.Activity.Model.Album;
import vn.com.namhenry.box_box_music.Activity.Model.Baihat;
import vn.com.namhenry.box_box_music.Activity.Model.ChuDe;
import vn.com.namhenry.box_box_music.Activity.Model.Playlist;
import vn.com.namhenry.box_box_music.Activity.Model.Quangcao;
import vn.com.namhenry.box_box_music.Activity.Model.TheLoai;
import vn.com.namhenry.box_box_music.Activity.Model.TheLoaitrongngay;

public interface Dataservice {
    @GET("songbanner.php")
    Call<List<Quangcao>> GetDataBanner();

    @GET("playlistforcurrentday.php")
    Call<List<Playlist>> GetPlaylistCurrentDay();

    @GET("chudevatheloaitrongngay.php")
    Call<TheLoaitrongngay> GetCategoryMusic();

    @GET("albumhot.php")
    Call<List<Album>> GetAlbumHot();

    @GET("baihatduocthich.php")
    Call<List<Baihat>> GetBaiHatHot();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetDanhsachbaihattheoquangcao(@Field("id") String id);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetDanhsachbaihattheoplaylist(@Field("idPlaylist") String idPlaylist);

    @GET("danhsachcacplaylist.php")
    Call<List<Playlist>> GetDanhsachcacPlaylist();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetDanhsachbaihattheotheloai(@Field("idTheLoai") String idTheLoai);

    @GET("tatcachude.php")
    Call<List<ChuDe>> GetAllChuDe();

    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<TheLoai>> GetTheloaitheochude(@Field("idChude") String idChude);

    @GET("tatcaalbum.php")
    Call<List<Album>> GetAllAlbum();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetDanhsachbaihattheoalbum(@Field("idAlbum") String idAlbum);

    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> UpdateLuotThich(@Field("luotthich") String luotthich, @Field("idBaiHat") String idBaiHat);

    @FormUrlEncoded
    @POST("searchbaihat.php")
   Call<List<Baihat>> GetSearchBaiHat(@Field("tukhoa") String tukhoa);
}

