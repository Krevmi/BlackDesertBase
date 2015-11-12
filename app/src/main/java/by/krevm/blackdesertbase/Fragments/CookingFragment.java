package by.krevm.blackdesertbase.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.krevm.blackdesertbase.Adapters.TabPagerFragmentAdapter;
import by.krevm.blackdesertbase.R;


public class CookingFragment extends Fragment {

    ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cooking_fragment, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        TabPagerFragmentAdapter adapter = new TabPagerFragmentAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }


}
