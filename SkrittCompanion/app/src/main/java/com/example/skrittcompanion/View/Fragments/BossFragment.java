package com.example.skrittcompanion.View.Fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.skrittcompanion.View.RecyclerAdapters.BossAdapter;
import com.example.skrittcompanion.Model.WorldBoss;
import com.example.skrittcompanion.R;

import java.util.ArrayList;

public class BossFragment extends Fragment implements BossAdapter.OnListItemClickListener {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.boss_recycler_frame, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerFrame = (getView()).findViewById(R.id.recyclerFrame);
        recyclerFrame.hasFixedSize();
        recyclerFrame.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<WorldBoss> bosses = new ArrayList<>();
        BossAdapter adapter = new BossAdapter(bosses,this);
        recyclerFrame.setAdapter(adapter);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

    }
}
