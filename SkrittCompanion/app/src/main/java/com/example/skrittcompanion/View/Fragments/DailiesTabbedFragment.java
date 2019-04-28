package com.example.skrittcompanion.View.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.skrittcompanion.R;
import com.google.android.material.tabs.TabLayout;


public class DailiesTabbedFragment extends Fragment {

    @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater,
                                 @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_dailies_tabbed, container, false);
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            DailiesTabbedFragmentAdapter dailiesTabbedFragmentAdapter = new DailiesTabbedFragmentAdapter(getChildFragmentManager());
            ViewPager dailiesPager = view.findViewById(R.id.pager);
            dailiesPager.setAdapter(dailiesTabbedFragmentAdapter);
            TabLayout tabLayout = view.findViewById(R.id.dailies_tab_layout);
            tabLayout.setupWithViewPager(dailiesPager);
        }
    }

    // Since this is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.
    class DailiesTabbedFragmentAdapter extends FragmentStatePagerAdapter {

        DailiesTabbedFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int i) {
            Fragment fragment = new DailiesFragment();
            Bundle args = new Bundle();
            if(i==0){
                args.putString(DailiesFragment.BUNDLE_KEY,DailiesFragment.FRACTAL_CODE);
            } else if(i==1){
                args.putString(DailiesFragment.BUNDLE_KEY,DailiesFragment.PVE_CODE);
            } else if(i==2){
                args.putString(DailiesFragment.BUNDLE_KEY,DailiesFragment.WVW_CODE);
            } else if (i==3) args.putString(DailiesFragment.BUNDLE_KEY,DailiesFragment.PVP_CODE);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                // Apparently I can't just leave them as is, if I don't override this, the tab names will poof
                // dunno why, dun care either maybe i'm just stupid.
                case 0: return "FRACTALS";
                case 1: return "PvE";
                case 2: return "WvW";
                case 3: return "PvP";
            }
            return "ERROR";
        }
    }