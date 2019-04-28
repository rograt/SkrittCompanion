package com.example.skrittcompanion.View.Fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.skrittcompanion.Model.Account;
import com.example.skrittcompanion.Model.AccountSingleton;
import com.example.skrittcompanion.Model.Repositories.BossRepository;
import com.example.skrittcompanion.View.Activities.MainActivity;
import com.example.skrittcompanion.View.Activities.SignUpActivity;
import com.example.skrittcompanion.View.RecyclerAdapters.BossAdapter;
import com.example.skrittcompanion.Model.WorldBoss;
import com.example.skrittcompanion.R;
import com.example.skrittcompanion.ViewModel.BossViewModel;
import com.example.skrittcompanion.ViewModel.BossViewModelFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BossFragment extends Fragment implements BossAdapter.OnListItemClickListener {

    private BossViewModel vm;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.boss_recycler_frame, container, false);
        vm=ViewModelProviders.of(this, new BossViewModelFactory(Objects.requireNonNull(this.getActivity()).getApplication())).get(BossViewModel.class);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerFrame = (getView()).findViewById(R.id.recyclerFrame);
        recyclerFrame.hasFixedSize();
        recyclerFrame.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<WorldBoss> bosses=new ArrayList<>();
        final BossAdapter adapter=new BossAdapter(bosses,this);
        recyclerFrame.setAdapter(adapter);
        vm.getAllBosses().observe(getActivity(), new Observer<List<WorldBoss>>() {
            @Override
            public void onChanged(@Nullable final List<WorldBoss> bosses) {
                if(bosses!=null){
                    adapter.setBosses(vm.getTimedBosses(bosses));
                }
            }
        });

    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

    }
}
