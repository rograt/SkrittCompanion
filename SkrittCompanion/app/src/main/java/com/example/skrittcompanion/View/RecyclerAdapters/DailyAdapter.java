package com.example.skrittcompanion.View.RecyclerAdapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.skrittcompanion.Model.DailyInfo;
import com.example.skrittcompanion.R;
import com.example.skrittcompanion.View.Activities.MainActivity;
import com.squareup.picasso.Picasso;


import java.util.List;

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.ViewHolder> {

    private List<DailyInfo> dailies;
    final private OnListItemClickListener mOnListItemClickListener;
    private Context context;

    public DailyAdapter(OnListItemClickListener mOnListItemClickListener, Context context) {
        this.mOnListItemClickListener = mOnListItemClickListener;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.daily_item_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.dailyName.setText(dailies.get(i).getName());
        if(!context.getSharedPreferences(MainActivity.PREF_NAME, Context.MODE_PRIVATE).getBoolean("dataSaver",false)) {
            Picasso.with(viewHolder.dailyIcon.getContext()).load(dailies.get(i).getIcon()).into(viewHolder.dailyIcon);
        }
        viewHolder.onClick(viewHolder.itemView);
    }


    @Override
    public int getItemCount() {
        if(dailies !=null){
            return dailies.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView dailyName;
        ImageView dailyIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dailyName = itemView.findViewById(R.id.description);
            dailyIcon =itemView.findViewById(R.id.dailyIcon);
            itemView.setOnClickListener(this);
        }

        @Override public void onClick(View v) {
            mOnListItemClickListener.onListItemClick(getAdapterPosition());
        }
    }

    public void setDailies(List<DailyInfo> dailies){
        this.dailies = dailies;
        notifyDataSetChanged();
    }

    public interface OnListItemClickListener {void onListItemClick(int clickedItemIndex);}


}
