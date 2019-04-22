package com.example.skrittcompanion.View.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.skrittcompanion.Model.DailyInfo;
import com.example.skrittcompanion.Model.Repositories.TradingPostRepository;
import com.example.skrittcompanion.Model.Transaction;
import com.example.skrittcompanion.R;
import com.example.skrittcompanion.View.RecyclerAdapters.DailyAdapter;
import com.example.skrittcompanion.View.RecyclerAdapters.TradingPostItemAdapter;
import com.example.skrittcompanion.ViewModel.DailiesViewModel;
import com.example.skrittcompanion.ViewModel.TradingPostViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TradingPostFragment extends Fragment implements TradingPostItemAdapter.OnListItemClickListener  {

    private TradingPostViewModel mViewModel;
    private Boolean isSale;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        isSale=getArguments().getBoolean("saleStat");
        return inflater.inflate(R.layout.trading_post_recycler_frame, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ProgressBar bar =(getView()).findViewById(R.id.loadingStatus);
        bar.setVisibility(View.VISIBLE);
        mViewModel = ViewModelProviders.of(this).get(TradingPostViewModel.class);
        RecyclerView recyclerFrame = (getView()).findViewById(R.id.tradingPostRecyclerFrame);
        recyclerFrame.hasFixedSize();
        recyclerFrame.setLayoutManager(new LinearLayoutManager(getActivity()));
        final TradingPostItemAdapter adapter = new TradingPostItemAdapter(this,this.getContext());
        recyclerFrame.setAdapter(adapter);
        try {
            if(isSale){
                mViewModel.getItemsOnSale().observe(this, new Observer<List<Transaction>>() {
                    @Override
                    public void onChanged(@Nullable final List<Transaction> transactions) {
                        // Update the cached copy of the words in the adapter.
                        bar.setVisibility(View.GONE);
                        adapter.setTransactions(transactions);
                    }
                });
            } else{
                mViewModel.getItemsOnBuy().observe(this, new Observer<List<Transaction>>() {
                    @Override
                    public void onChanged(@Nullable final List<Transaction> transactions) {
                        // Update the cached copy of the words in the adapter.
                        ProgressBar bar =(getView()).findViewById(R.id.loadingStatus);
                        bar.setVisibility(View.GONE);
                        adapter.setTransactions(transactions);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

    }
}
