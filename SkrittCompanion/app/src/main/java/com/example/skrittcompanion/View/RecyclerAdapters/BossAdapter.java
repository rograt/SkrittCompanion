package com.example.skrittcompanion.View.RecyclerAdapters;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skrittcompanion.Model.WorldBoss;
import com.example.skrittcompanion.R;

import java.util.ArrayList;
import java.util.List;

public class BossAdapter extends RecyclerView.Adapter<BossAdapter.ViewHolder> {

    private List<WorldBoss> bosses;
    final private OnListItemClickListener mOnListItemClickListener;

    public BossAdapter(ArrayList<WorldBoss> bosses, OnListItemClickListener mOnListItemClickListener) {
        this.bosses = bosses;
        this.mOnListItemClickListener=mOnListItemClickListener;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.boss_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BossAdapter.ViewHolder viewHolder, int i) {
        viewHolder.name.setText(bosses.get(i).getBossName());
        viewHolder.waypoint.setText(bosses.get(i).getClosestWaypoint());
        String hour =bosses.get(i).getHour()%24+"";
        if(hour.equals("0")){
            hour+="0";
        }
        if( bosses.get(i).getMinute()==0){
            viewHolder.time.setText(hour+":"+bosses.get(i).getMinute()+"0");
        }
        else {
            viewHolder.time.setText(hour + ":" + bosses.get(i).getMinute());
        }
        viewHolder.area.setText(bosses.get(i).getArea());
    }

    @Override
    public int getItemCount() {
        return bosses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        TextView time;
        TextView waypoint;
        TextView area;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.bossNameView);
            time = itemView.findViewById(R.id.spawnTimeView);
            waypoint=itemView.findViewById(R.id.waypointView);
            area=itemView.findViewById(R.id.bossArea);
            itemView.setOnClickListener(this);
        }

        @Override public void onClick(View v) {
            mOnListItemClickListener.onListItemClick(getAdapterPosition());
            Intent browserIntent = new Intent(Intent.ACTION_VIEW);
            browserIntent.setData(Uri.parse(bosses.get(getAdapterPosition()).getWikiPage()));
            v.getContext().startActivity(browserIntent);
        }
    }
    public interface OnListItemClickListener {void onListItemClick(int clickedItemIndex);}


    public void setBosses(List<WorldBoss> bosses){
        this.bosses=bosses;
        notifyDataSetChanged();
    }
}
