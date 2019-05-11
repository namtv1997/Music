package vn.com.namhenry.box_box_music.Activity.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import vn.com.namhenry.box_box_music.Activity.Model.Baihat;
import vn.com.namhenry.box_box_music.R;

public class PlaynhacAdapter extends RecyclerView.Adapter<PlaynhacAdapter.ViewHolder>{
    Context context;
    ArrayList<Baihat> mangbaihat;

    public PlaynhacAdapter(Context context, ArrayList<Baihat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_play_baihat,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Baihat baihat=mangbaihat.get(position);
        holder.txtcasi.setText(baihat.getCasi());
        holder.txtindex.setText(position+1+"");
        holder.txttenbaihat.setText(baihat.getTenBaiHat());
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
TextView txtindex,txttenbaihat,txtcasi;
    public ViewHolder(View itemView) {
        super(itemView);
        txtcasi=itemView.findViewById(R.id.textviewplaynhactencasi);
        txtindex=itemView.findViewById(R.id.textviewplaynhacindex);
        txttenbaihat=itemView.findViewById(R.id.textviewplaynhactenbaihat);
    }
}
}
