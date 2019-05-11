package vn.com.namhenry.box_box_music.Activity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import vn.com.namhenry.box_box_music.Activity.Activity.DanhsachtatcachudeActivity;
import vn.com.namhenry.box_box_music.Activity.Activity.DanhsachtheloaitheochudeActivity;
import vn.com.namhenry.box_box_music.Activity.Model.ChuDe;
import vn.com.namhenry.box_box_music.R;

public class DanhsachtatcachudeAdapter extends RecyclerView.Adapter<DanhsachtatcachudeAdapter.ViewHoldel> {
    Context context;
    ArrayList<ChuDe> mangchude;

    public DanhsachtatcachudeAdapter(Context context, ArrayList<ChuDe> mangchude) {
        this.context = context;
        this.mangchude = mangchude;
    }

    @NonNull
    @Override
    public ViewHoldel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_cac_chu_de, parent, false);

        return new ViewHoldel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoldel holder, int position) {
        ChuDe chuDe = mangchude.get(position);
        Picasso.with(context).load(chuDe.getHinhChuDe()).into(holder.imgchude);
    }

    @Override
    public int getItemCount() {
        return mangchude.size();
    }

    public class ViewHoldel extends RecyclerView.ViewHolder {
        ImageView imgchude;

        public ViewHoldel(View itemView) {
            super(itemView);
            imgchude = itemView.findViewById(R.id.imageviewdongcacchude);
            imgchude.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, DanhsachtheloaitheochudeActivity.class);
                    intent.putExtra("chude",mangchude.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
