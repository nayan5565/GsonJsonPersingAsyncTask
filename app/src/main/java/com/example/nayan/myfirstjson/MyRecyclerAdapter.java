package com.example.nayan.myfirstjson;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by JEWEL on 8/9/2016.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>{
    ArrayList<MVideo> videos;
    Context context;
    LayoutInflater inflater;
    MVideo mVideo;
    public MyRecyclerAdapter(Context context){
        this.context=context;
        mVideo=new MVideo();
        videos=new ArrayList<>();
        inflater=LayoutInflater.from(context);

    }
    public void setData(ArrayList<MVideo> videos){
        this.videos=videos;
        notifyDataSetChanged();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.row_video_item,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        mVideo=videos.get(position);
        holder.txtMenuId.setText(mVideo.getMenuId()+"");
        holder.txtMenuTitle.setText(mVideo.getMenuTitle());
        holder.txtMenuVideo.setText(mVideo.getMenuVideo());
        holder.txtMenuFileName.setText(mVideo.getMenuFile_name());

    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtMenuId,txtMenuTitle,txtMenuVideo,txtMenuFileName;
        public MyViewHolder(View itemView) {
            super(itemView);
            txtMenuId=(TextView)itemView.findViewById(R.id.txtMenuId);
            txtMenuTitle=(TextView)itemView.findViewById(R.id.txtMenuTitle);
            txtMenuVideo=(TextView)itemView.findViewById(R.id.txtMenuVideo);
            txtMenuFileName=(TextView) itemView.findViewById(R.id.txtMenuFileName);
        }
    }
}
