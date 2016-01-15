package by.krevm.bdbase.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import by.krevm.bdbase.Adapters.DishesListRVAdapter;
import by.krevm.bdbase.Adapters.MovieTouchHelper;
import by.krevm.bdbase.Data.LoadDataFromParse;
import by.krevm.bdbase.Data.SharedPreference;
import by.krevm.bdbase.IngredientFromParse;
import by.krevm.bdbase.R;


public class FavoritesFragment extends Fragment implements DishesListRVAdapter.ItemClickListener {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private DishesListRVAdapter mAdapter;
    private ArrayList<IngredientFromParse> favoritesList;

    public FavoritesFragment() {
    }

    public static FavoritesFragment newInstance(int titleId) {
        Bundle args = new Bundle();
        args.putInt("title", titleId);
        FavoritesFragment fragment = new FavoritesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorite_fragment, container, false);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (getArguments() != null && getArguments().containsKey("title")) {
            actionBar.setTitle(getArguments().getInt("title", 0));
        }
        TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.tab_layout);
        tabLayout.setVisibility(View.GONE);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.favorites_list);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        SharedPreference sp = new SharedPreference(getActivity());
        favoritesList = new ArrayList<>(LoadDataFromParse.getFavorites(sp.getFavorites()));
        mAdapter = new DishesListRVAdapter(favoritesList, getActivity());
        mAdapter.setClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        ItemTouchHelper.Callback callback = new MovieTouchHelper(mAdapter, mRecyclerView);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRecyclerView);
        return view;
    }

    @Override
    public void onClick(View view, IngredientFromParse ing) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, DishFragment.newInstance(ing)).addToBackStack("stek").commit();
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreference sp = new SharedPreference(getActivity());
        for (String itemToDeleteId : mAdapter.itemToDeleteFromFavorites) {
            sp.removeFavorite(itemToDeleteId);
        }
    }
}
