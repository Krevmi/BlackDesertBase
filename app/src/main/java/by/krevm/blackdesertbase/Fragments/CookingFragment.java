package by.krevm.blackdesertbase.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;

import by.krevm.blackdesertbase.Adapters.TabPagerFragmentAdapter;
import by.krevm.blackdesertbase.IngredientFromParse;
import by.krevm.blackdesertbase.ParseAppInitialization;
import by.krevm.blackdesertbase.R;


public class CookingFragment extends Fragment {
    public static ArrayList<IngredientFromParse> allIngredients = new ArrayList<>();
    ViewPager viewPager;
    static int titleId;
    Tracker mTracker;
    TabPagerFragmentAdapter adapter;

    public static CookingFragment newInstance(int titleId) {
        Bundle args = new Bundle();
        args.putInt("titleID", titleId);
        CookingFragment fragment = new CookingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cooking_fragment, container, false);
        ParseAppInitialization application = (ParseAppInitialization) getActivity().getApplication();
        mTracker = application.getDefaultTracker();
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (getArguments() != null && getArguments().containsKey("titleID")) {
            titleId = getArguments().getInt("titleID");

            actionBar.setTitle(titleId);
            mTracker.setScreenName("Fragment " + getString(titleId));
            mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        }

        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        final TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.tab_layout);
        tabLayout.setVisibility(View.VISIBLE);

        if (titleId == R.string.cookery) {
            String[] tabs = new String[]{"Блюда", "Ингредиенты"};
            adapter = new TabPagerFragmentAdapter(getChildFragmentManager(), tabs);
        } else if (titleId == R.string.alchemy) {
            String[] tabs = new String[]{"Зелья", "Камни", "Реагенты", "Ингредиенты"};
            adapter = new TabPagerFragmentAdapter(getChildFragmentManager(), tabs);
        }

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        if (allIngredients.isEmpty()) {
            ParseQuery<IngredientFromParse> query = ParseQuery.getQuery(IngredientFromParse.class);
            query.setLimit(1000);
            try {
                allIngredients.addAll(query.find());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return view;
    }

    public static ArrayList<IngredientFromParse> getAllIngredients() {
        ArrayList<IngredientFromParse> ingredients = new ArrayList<>();
        if (titleId == R.string.cookery) {
            for (IngredientFromParse ing : allIngredients) {
                if (ing.getUseIn().contains("Кулинария")) {
                    ingredients.add(ing);
                }
            }
        } else if (titleId == R.string.alchemy) {
            for (IngredientFromParse ing : allIngredients) {
                if (ing.getUseIn().contains("Алхимия")) {
                    ingredients.add(ing);
                }
            }
        }
        return ingredients;

    }

}
