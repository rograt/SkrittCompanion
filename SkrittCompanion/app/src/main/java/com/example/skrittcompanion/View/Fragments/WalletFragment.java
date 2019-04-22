package com.example.skrittcompanion.View.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.skrittcompanion.Model.CurrencyInfo;
import com.example.skrittcompanion.Model.Wallet;
import com.example.skrittcompanion.View.RecyclerAdapters.WalletAdapter;
import com.example.skrittcompanion.R;
import com.example.skrittcompanion.ViewModel.WalletViewModel;

import java.util.List;

public class WalletFragment extends Fragment implements WalletAdapter.OnListItemClickListener  {

    private WalletViewModel walletViewModel;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.wallet_recycler_frame, container, false);
        walletViewModel = ViewModelProviders.of(this).get(WalletViewModel.class);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ProgressBar bar =(getView()).findViewById(R.id.loadingStatus);
        bar.setVisibility(View.VISIBLE);
        RecyclerView recyclerFrame = (getView()).findViewById(R.id.walletFrame);
        recyclerFrame.hasFixedSize();
        recyclerFrame.setLayoutManager(new LinearLayoutManager(getActivity()));
        final WalletAdapter adapter = new WalletAdapter(this,this.getContext());
        recyclerFrame.setAdapter(adapter);
        walletViewModel.getWallet().observe(this, new Observer<Wallet>() {
            @Override
            public void onChanged(@Nullable final Wallet words) {
                // Update the cached copy of the words in the adapter.
                bar.setVisibility(View.GONE);
                adapter.setWallet(words.getCurrencies());
            }
        });
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

    }
}
