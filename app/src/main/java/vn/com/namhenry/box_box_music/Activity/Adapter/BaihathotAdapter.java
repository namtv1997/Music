package vn.com.namhenry.box_box_music.Activity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.namhenry.box_box_music.Activity.Activity.PlayNhacActivity;
import vn.com.namhenry.box_box_music.Activity.Model.Baihat;
import vn.com.namhenry.box_box_music.Activity.Service.APIService;
import vn.com.namhenry.box_box_music.Activity.Service.Dataservice;
import vn.com.namhenry.box_box_music.R;

public class BaihathotAdapter extends RecyclerView.Adapter<BaihathotAdapter.ViewHolder> {
    Context context;
    ArrayList<Baihat> baihatArrayList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_bai_hat_hot, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Baihat baihat = baihatArrayList.get(position);
        holder.txtcasi.setText(baihat.getCasi());
        holder.txtten.setText(baihat.getTenBaiHat());
        Picasso.with(context).load(baihat.getHinhBaiHat()).into(holder.imghinh);
    }

    @Override
    public int getItemCount() {
        return baihatArrayList.size();
    }

    public BaihathotAdapter(Context context, ArrayList<Baihat> baihatArrayList) {
        this.context = context;
        this.baihatArrayList = baihatArrayList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtten, txtcasi;
        ImageView imghinh, imgluotthich;

        public ViewHolder(View itemView) {
            super(itemView);
            txtten = itemView.findViewById(R.id.textviewtenbaihathot);
            txtcasi = itemView.findViewById(R.id.textviewcasibaihathot);
            imghinh = itemView.findViewById(R.id.imageviewbaihathot);
            imgluotthich = itemView.findViewById(R.id.imageviewluotthich);
            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imgluotthich.setImageResource(R.drawable.iconloved);
                    Dataservice dataservice = APIService.getservice();
                    Call<String> callback=dataservice.UpdateLuotThich("1",baihatArrayList.get(getPosition()).getIdBaiHat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua=response.body();
                            if (ketqua.equals("ok")){
                                Toast.makeText(context, "Đã thích", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context, "Bị Lỗi", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imgluotthich.setEnabled(false);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc",baihatArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}

