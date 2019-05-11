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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import vn.com.namhenry.box_box_music.Activity.Activity.DanhsachbaihatActivity;
import vn.com.namhenry.box_box_music.Activity.Model.TheLoai;
import vn.com.namhenry.box_box_music.R;

public class DanhsachtheloaitheochudeAdapter extends RecyclerView.Adapter<DanhsachtheloaitheochudeAdapter.ViewHoldel> {
    Context context;
    ArrayList<TheLoai> theLoaiArrayList;

    public DanhsachtheloaitheochudeAdapter(Context context, ArrayList<TheLoai> theLoaiArrayList) {
        this.context = context;
        this.theLoaiArrayList = theLoaiArrayList;
    }

    @NonNull
    @Override

    public ViewHoldel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_the_loai_theo_chu_de,parent,false);

        return new ViewHoldel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoldel holder, int position) {
        TheLoai theLoai=theLoaiArrayList.get(position);
        Picasso.with(context).load(theLoai.getHinhTheLoai()).into(holder.imghinhnen);
        holder.txttentheloai.setText(theLoai.getTenTheLoai());

    }

    @Override
    public int getItemCount() {
        return theLoaiArrayList.size();
    }

    public class ViewHoldel extends RecyclerView.ViewHolder{
       ImageView imghinhnen;
       TextView txttentheloai;
        public ViewHoldel(View itemView) {
            super(itemView);
            imghinhnen=itemView.findViewById(R.id.imageviewtheloaitheochude);
            txttentheloai=itemView.findViewById(R.id.textviewtentheloaitheochude);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("idtheloai",theLoaiArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }
}
