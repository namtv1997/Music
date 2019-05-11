package vn.com.namhenry.box_box_music.Activity.Service;

public class APIService {
    private static String base_url="https://boxboxmusic.000webhostapp.com/Server/";
    public static Dataservice getservice(){
        return APIRetrofitClient.getClient(base_url).create(Dataservice.class);
    }
}
