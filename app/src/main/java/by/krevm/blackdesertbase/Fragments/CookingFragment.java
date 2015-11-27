package by.krevm.blackdesertbase.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;

import by.krevm.blackdesertbase.Adapters.TabPagerFragmentAdapter;
import by.krevm.blackdesertbase.IngredientFromParse;
import by.krevm.blackdesertbase.R;


public class CookingFragment extends Fragment{
    public static ArrayList<IngredientFromParse> allIngredients = new ArrayList<>();
    ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cooking_fragment, container, false);
        allIngredients.clear();
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("Кулинария");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        final TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        TabPagerFragmentAdapter adapter = new TabPagerFragmentAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        ParseQuery<IngredientFromParse> query = ParseQuery.getQuery(IngredientFromParse.class);
        query.setLimit(1000);
        try {
            allIngredients.addAll(query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return view;
    }


}
