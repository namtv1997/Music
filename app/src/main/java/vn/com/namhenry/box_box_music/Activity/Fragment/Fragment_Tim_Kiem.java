package vn.com.namhenry.box_box_music.Activity.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.namhenry.box_box_music.Activity.Adapter.SearchBaiHatAdapter;
import vn.com.namhenry.box_box_music.Activity.Model.Baihat;
import vn.com.namhenry.box_box_music.Activity.Service.APIService;
import vn.com.namhenry.box_box_music.Activity.Service.Dataservice;
import vn.com.namhenry.box_box_music.R;

public class Fragment_Tim_Kiem extends Fragment{
    View view;
    Toolbar toolbar;
    RecyclerView recyclerViewsearchbaihat;
    TextView txtkhongcodulieu;
    SearchBaiHatAdapter searchBaiHatAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_timkiem,container,false);
        toolbar=view.findViewById(R.id.toolbarsearchbaihat);
        recyclerViewsearchbaihat=view.findViewById(R.id.recyclerviewsearchbaihat);
        txtkhongcodulieu=view.findViewById(R.id.textviewkhongcodulieu);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("");
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_view,menu);
        MenuItem menuItem=menu.findItem(R.id.menu_search);
        SearchView searchView= (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                SearchTuKhoaBaiHat(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
    private void SearchTuKhoaBaiHat(String query){
        Dataservice dataservice= APIService.getservice();
        Call<List<Baihat>> callback=dataservice.GetSearchBaiHat(query);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                ArrayList<Baihat> mangbaihat= (ArrayList<Baihat>) response.body();
                if (mangbaihat.size()>0){
                    searchBaiHatAdapter=new SearchBaiHatAdapter(getActivity(),mangbaihat);
                    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity()) ;
                    recyclerViewsearchbaihat.setLayoutManager(linearLayoutManager);
                    recyclerViewsearchbaihat.setAdapter(searchBaiHatAdapter);
                    txtkhongcodulieu.setVisibility(View.GONE);
                    recyclerViewsearchbaihat.setVisibility(View.VISIBLE);
                }else {
                    recyclerViewsearchbaihat.setVisibility(View.GONE);
                    txtkhongcodulieu.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }
}
