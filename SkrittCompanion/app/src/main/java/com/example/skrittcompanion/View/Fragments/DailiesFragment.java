package com.example.skrittcompanion.View.Fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.skrittcompanion.Model.DailyInfo;
import com.example.skrittcompanion.View.RecyclerAdapters.DailyAdapter;
import com.example.skrittcompanion.ViewModel.DailiesViewModel;
import com.example.skrittcompanion.R;

import java.util.List;

public class DailiesFragment extends Fragment implements DailyAdapter.OnListItemClickListener {

    private DailiesViewModel mViewModel;

    public static DailiesFragment newInstance() {
        return new DailiesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dailies_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DailiesViewModel.class);

        RecyclerView recyclerFrame = (getView()).findViewById(R.id.fractalFrame);
        recyclerFrame.hasFixedSize();
        recyclerFrame.setLayoutManager(new LinearLayoutManager(getActivity()));
        final DailyAdapter adapter = new DailyAdapter(this);
        recyclerFrame.setAdapter(adapter);
        try {
            mViewModel.getFractalDailies().observe(this, new Observer<List<DailyInfo>>() {
                @Override
                public void onChanged(@Nullable final List<DailyInfo> words) {
                    // Update the cached copy of the words in the adapter.
                    adapter.setDailies(words);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        RecyclerView pveRecyclerFrame = (getView()).findViewById(R.id.pveFrame);
        pveRecyclerFrame.hasFixedSize();
        pveRecyclerFrame.setLayoutManager(new LinearLayoutManager(getActivity()));
        final DailyAdapter pveAdapter = new DailyAdapter(this);
        pveRecyclerFrame.setAdapter(pveAdapter);
        try {
            mViewModel.getPveDailies().observe(this, new Observer<List<DailyInfo>>() {
                @Override
                public void onChanged(@Nullable final List<DailyInfo> words) {
                    // Update the cached copy of the words in the adapter.
                    pveAdapter.setDailies(words);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        RecyclerView pvpRecyclerFrame = (getView()).findViewById(R.id.pvpFrame);
        pvpRecyclerFrame.hasFixedSize();
        pvpRecyclerFrame.setLayoutManager(new LinearLayoutManager(getActivity()));
        final DailyAdapter pvpAdapter = new DailyAdapter(this);
        pvpRecyclerFrame.setAdapter(pvpAdapter);
        try {
            mViewModel.getPvpDailies().observe(this, new Observer<List<DailyInfo>>() {
                @Override
                public void onChanged(@Nullable final List<DailyInfo> words) {
                    // Update the cached copy of the words in the adapter.
                    pvpAdapter.setDailies(words);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        RecyclerView wvwRecyclerFrame = (getView()).findViewById(R.id.wvwFrame);
        wvwRecyclerFrame.hasFixedSize();
        wvwRecyclerFrame.setLayoutManager(new LinearLayoutManager(getActivity()));
        final DailyAdapter wvwAdapter = new DailyAdapter(this);
        wvwRecyclerFrame.setAdapter(wvwAdapter);
        try {
            mViewModel.getWvwDailies().observe(this, new Observer<List<DailyInfo>>() {
                @Override
                public void onChanged(@Nullable final List<DailyInfo> words) {
                    // Update the cached copy of the words in the adapter.
                    wvwAdapter.setDailies(words);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

    }
}
