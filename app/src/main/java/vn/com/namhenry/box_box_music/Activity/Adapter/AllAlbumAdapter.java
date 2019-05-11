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

public class AllAlbumAdapter extends RecyclerView.Adapter<AllAlbumAdapter.ViewHoldel> {
    Context context;
    ArrayList<Album> albumArrayList;

    @NonNull
    @Override
    public ViewHoldel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_all_album, parent, false);

        return new ViewHoldel(view);
    }

    public AllAlbumAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoldel holder, int position) {
        Album album = albumArrayList.get(position);
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imgallalbum);
        holder.txttenallalbum.setText(album.getTenAlbum());
    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    public class ViewHoldel extends RecyclerView.ViewHolder {
        ImageView imgallalbum;
        TextView txttenallalbum;

        public ViewHoldel(View itemView) {
            super(itemView);
            imgallalbum = itemView.findViewById(R.id.imageviewallalbum);
            txttenallalbum = itemView.findViewById(R.id.textviewtenallalbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("album", albumArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
