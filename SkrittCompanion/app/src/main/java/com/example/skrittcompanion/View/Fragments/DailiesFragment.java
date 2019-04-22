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
    public static final String BUNDLE_KEY = "DAILY_KEY";
    public static final String FRACTAL_CODE="DAILIES_FRACTALS";
    public static final String WVW_CODE="DAILIES_WVW";
    public static final String PVP_CODE="DAILIES_PVP";
    public static final String PVE_CODE="DAILIES_PVE";
    public static final String BUNDLE_EMPTY_CODE="DAILIES_EMPTY";

    private Bundle bundle;

    public static DailiesFragment newInstance() {
        return new DailiesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        bundle=getArguments();
        return inflater.inflate(R.layout.dailies_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DailiesViewModel.class);
        RecyclerView recyclerFrame = (getView()).findViewById(R.id.daily_frame);
        recyclerFrame.hasFixedSize();
        recyclerFrame.setLayoutManager(new LinearLayoutManager(getActivity()));
        final DailyAdapter adapter = new DailyAdapter(this,this.getContext());
        recyclerFrame.setAdapter(adapter);
        String value=bundle.getString(BUNDLE_KEY,"DAILIES_EMPTY");
        bundle.remove(BUNDLE_KEY);
        switch (value){
            case FRACTAL_CODE:{
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
                return;
            }
            case PVE_CODE:{
                try {
                    mViewModel.getPveDailies().observe(this, new Observer<List<DailyInfo>>() {
                        @Override
                        public void onChanged(@Nullable final List<DailyInfo> words) {
                            // Update the cached copy of the words in the adapter.
                            adapter.setDailies(words);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            }
            case WVW_CODE:{
                try {
                    mViewModel.getWvwDailies().observe(this, new Observer<List<DailyInfo>>() {
                        @Override
                        public void onChanged(@Nullable final List<DailyInfo> words) {
                            // Update the cached copy of the words in the adapter.
                            adapter.setDailies(words);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            }
            case PVP_CODE:{
                try {
                    mViewModel.getPvpDailies().observe(this, new Observer<List<DailyInfo>>() {
                        @Override
                        public void onChanged(@Nullable final List<DailyInfo> words) {
                            // Update the cached copy of the words in the adapter.
                            adapter.setDailies(words);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

    }
}
