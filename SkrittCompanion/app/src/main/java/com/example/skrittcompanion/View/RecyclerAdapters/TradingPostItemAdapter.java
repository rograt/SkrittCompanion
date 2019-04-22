package com.example.skrittcompanion.View.RecyclerAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.skrittcompanion.Model.DailyInfo;
import com.example.skrittcompanion.Model.Transaction;
import com.example.skrittcompanion.R;
import com.example.skrittcompanion.View.Activities.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TradingPostItemAdapter extends RecyclerView.Adapter<TradingPostItemAdapter.ViewHolder> {

private List<Transaction> transactions;
final private OnListItemClickListener mOnListItemClickListener;
private Context context;

    public TradingPostItemAdapter(TradingPostItemAdapter.OnListItemClickListener mOnListItemClickListener,Context context) {
        this.mOnListItemClickListener = mOnListItemClickListener;
        this.context=context;
    }

    @NonNull
    @Override
    public TradingPostItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.trading_post_recycler_item, viewGroup, false);
        return new TradingPostItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TradingPostItemAdapter.ViewHolder viewHolder, int i) {
        if(transactions.get(i).getItem()!=null){
            viewHolder.tpItemNameView.setText(transactions.get(i).getItem().getName()+"");
            viewHolder.quantity.setText(transactions.get(i).getQuantity()+"");
            viewHolder.price.setText(transactions.get(i).getPrice()/10000+"g "+transactions.get(i).getPrice()%10000/100+"s "+transactions.get(i).getPrice()%10000%100+"b ");
            if(!context.getSharedPreferences(MainActivity.PREF_NAME,Context.MODE_PRIVATE).getBoolean("dataSaver",false)) {
                Picasso.with(viewHolder.tpItemIconView.getContext()).load(transactions.get(i).getItem().getIcon()).into(viewHolder.tpItemIconView);
            }
            viewHolder.onClick(viewHolder.itemView);
        }

    }


    @Override
    public int getItemCount() {
        if(transactions !=null){
            return transactions.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tpItemNameView;
        TextView quantity;
        TextView price;
        ImageView tpItemIconView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tpItemNameView = itemView.findViewById(R.id.tpItemName);
            quantity = itemView.findViewById(R.id.quantity);
            price=itemView.findViewById(R.id.price);
            tpItemIconView =itemView.findViewById(R.id.itemIcon);
            itemView.setOnClickListener(this);
        }

        @Override public void onClick(View v) {
            mOnListItemClickListener.onListItemClick(getAdapterPosition());
        }
    }

    public void setTransactions(List<Transaction> transactions){
        this.transactions = transactions;
        notifyDataSetChanged();
    }

    public interface OnListItemClickListener {void onListItemClick(int clickedItemIndex);}


}
