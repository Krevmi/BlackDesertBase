package by.krevm.blackdesertbase.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;

import by.krevm.blackdesertbase.Adapters.DishesListRVAdapter;
import by.krevm.blackdesertbase.DividerItemDecoration;
import by.krevm.blackdesertbase.IngredientFromParse;
import by.krevm.blackdesertbase.R;
import by.krevm.blackdesertbase.Recipe;


public class DishesListFragment extends Fragment implements DishesListRVAdapter.ItemClickListener, SearchView.OnQueryTextListener {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    DishesListRVAdapter mAdapter;
    ArrayList<IngredientFromParse> resList;

    public static DishesListFragment newInstance() {
        DishesListFragment fragment = new DishesListFragment();
        System.out.println("Dishes fragment create");
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("Dishes fragment create");
        View view = inflater.inflate(R.layout.dishes_list_fragment_layout, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.dishes_list);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        resList = new ArrayList<>();
        for (IngredientFromParse ing : CookingFragment.allIngredients) {
            if (ing.isResult()) {
                resList.add(ing);
            }
        }
        setAdapter(resList);
        return view;
    }

    private void setAdapter(ArrayList<IngredientFromParse> list) {
        mAdapter = new DishesListRVAdapter(list);
        mAdapter.setClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view, IngredientFromParse ing) {
        System.out.println("Dish List onClick");
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, DishFragment.newInstance(ing)).addToBackStack("stek").commit();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.coocing_menu, menu);
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        String queryQ = query.toLowerCase();
        ArrayList<IngredientFromParse> ingFilteredList = new ArrayList<>();
        for (IngredientFromParse ing : resList) {
            String name = ing.getName().toLowerCase();
            if (name.contains(queryQ)) {
                ingFilteredList.add(ing);
            }
        }
        mAdapter.setFilteredList(ingFilteredList);
        return false;
    }
}
