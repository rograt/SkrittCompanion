package com.example.skrittcompanion.View.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.skrittcompanion.Model.Account;
import com.example.skrittcompanion.Model.AccountSingleton;
import com.example.skrittcompanion.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class OverviewFragment extends Fragment {

    private TextView accountName;
    private TextView ageView;
    private TextView dailyAp;
    private TextView monthlyAp;
    private TextView fractalRank;
    private TextView wvwRank;
    public OverviewFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_overview, container, false);
        accountName = view.findViewById(R.id.embeddedNameValue);
        ageView = view.findViewById(R.id.embeddedAgeViewValue);
        dailyAp = view.findViewById(R.id.dailyAPValueView);
        monthlyAp = view.findViewById(R.id.monthlyAPValueView);
        fractalRank = view.findViewById(R.id.fractalRankValueView);
        wvwRank = view.findViewById(R.id.wvwRankValueView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Account currentAccount=AccountSingleton.getInstance().getValue();
        accountName.setText(currentAccount.getName()+"");
        ageView.setText(currentAccount.getAge()/3600+" Hours "+currentAccount.getAge()%3600/60+" Minutes");
        dailyAp.setText(currentAccount.getDaily_ap()+"");
        monthlyAp.setText(currentAccount.getMonthly_ap()+"");
        fractalRank.setText(currentAccount.getFractal_level()+"");
        wvwRank.setText(currentAccount.getWvw_rank()+"");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }




}
