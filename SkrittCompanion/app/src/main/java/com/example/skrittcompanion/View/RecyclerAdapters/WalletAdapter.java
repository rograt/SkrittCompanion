package com.example.skrittcompanion.View.RecyclerAdapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.skrittcompanion.Model.Currency;
import com.example.skrittcompanion.Model.CurrencyInfo;
import com.example.skrittcompanion.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.ViewHolder> {

    private Currency[] wallet;
    final private OnListItemClickListener mOnListItemClickListener;

    public WalletAdapter( OnListItemClickListener mOnListItemClickListener) {
        this.mOnListItemClickListener = mOnListItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.wallet_recycler_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if(wallet[i].getDescription()!=null){
            viewHolder.currencyName.setText(wallet[i].getDescription().getName());
            viewHolder.currencyAmount.setText(wallet[i].getValue()+"");
            Picasso.with(viewHolder.currencyLogo.getContext()).load(wallet[i].getDescription().getIcon()).into(viewHolder.currencyLogo);
            viewHolder.onClick(viewHolder.itemView);
        }
      }


    @Override
    public int getItemCount() {
        if(wallet!=null){
            return wallet.length;
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView currencyName;
        TextView currencyAmount;
        ImageView currencyLogo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            currencyName = itemView.findViewById(R.id.currencyNameView);
            currencyAmount = itemView.findViewById(R.id.currencyAmountView);
            currencyLogo=itemView.findViewById(R.id.currencyLogoView);
            itemView.setOnClickListener(this);
        }

        @Override public void onClick(View v) {
            mOnListItemClickListener.onListItemClick(getAdapterPosition());
        }
    }

    public void setWallet(Currency[] wallet){
        this.wallet=wallet;
        notifyDataSetChanged();
    }

    public interface OnListItemClickListener {void onListItemClick(int clickedItemIndex);}


}
