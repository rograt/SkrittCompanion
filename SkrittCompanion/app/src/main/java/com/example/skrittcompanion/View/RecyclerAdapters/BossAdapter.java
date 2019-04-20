package com.example.skrittcompanion.View.RecyclerAdapters;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.skrittcompanion.Model.WorldBoss;
import com.example.skrittcompanion.R;

import java.util.ArrayList;

public class BossAdapter extends RecyclerView.Adapter<BossAdapter.ViewHolder> {

    private ArrayList<WorldBoss> bosses;
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
        viewHolder.time.setText(bosses.get(i).getClosestWaypoint());
        viewHolder.layout.setBackgroundResource(bosses.get(i).getBackgroundId());
    }

    @Override
    public int getItemCount() {
        return bosses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        TextView time;
        TextView waypoint;
        ConstraintLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.bossNameView);
            time = itemView.findViewById(R.id.spawnTimeView);
            waypoint=itemView.findViewById(R.id.waypointView);
            layout=itemView.findViewById(R.id.backgroundLayout);
            itemView.setOnClickListener(this);
        }

        @Override public void onClick(View v) {mOnListItemClickListener.onListItemClick(getAdapterPosition());
        }
    }
    public interface OnListItemClickListener {void onListItemClick(int clickedItemIndex);}
}
