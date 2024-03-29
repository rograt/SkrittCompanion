package com.example.skrittcompanion.View.RecyclerAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skrittcompanion.Model.Currency;
import com.example.skrittcompanion.R;
import com.example.skrittcompanion.View.Activities.MainActivity;
import com.squareup.picasso.Picasso;

public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.ViewHolder> {

    private Currency[] wallet;
    final private OnListItemClickListener mOnListItemClickListener;
    private Context context;

    public WalletAdapter(OnListItemClickListener mOnListItemClickListener, Context context) {
        this.mOnListItemClickListener = mOnListItemClickListener;
        this.context=context;
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
            if(!context.getSharedPreferences(MainActivity.PREF_NAME,Context.MODE_PRIVATE).getBoolean("dataSaver",false)){
                Picasso.with(viewHolder.currencyLogo.getContext()).load(wallet[i].getDescription().getIcon()).into(viewHolder.currencyLogo);
            }
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
        View itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView=itemView;
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
