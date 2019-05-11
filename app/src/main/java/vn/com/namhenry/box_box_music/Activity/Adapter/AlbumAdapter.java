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
import vn.com.namhenry.box_box_music.Activity.Model.Album;
import vn.com.namhenry.box_box_music.R;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder>{
    Context context;
    ArrayList<Album> mangalbum;

    public AlbumAdapter(Context context, ArrayList<Album> mangalbum) {
        this.context = context;
        this.mangalbum = mangalbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.dong_album,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album=mangalbum.get(position);
        holder.txttenalbum.setText(album.getTenAlbum());
        holder.txtcasialbum.setText(album.getTenCaSiAlbum());
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imghinhalbum);

    }

    @Override
    public int getItemCount() {
        return mangalbum.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imghinhalbum;
        TextView txttenalbum,txtcasialbum;
        public ViewHolder(View itemView) {
            super(itemView);
            imghinhalbum=itemView.findViewById(R.id.imageviewalbum);
            txttenalbum=itemView.findViewById(R.id.textviewtenalbum);
            txtcasialbum=itemView.findViewById(R.id.textviewtencasialbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("album",mangalbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
