package by.krevm.blackdesertbase.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import by.krevm.blackdesertbase.Adapters.IngredientsListRVAdapter;
import by.krevm.blackdesertbase.DividerItemDecoration;
import by.krevm.blackdesertbase.IngredientFromParse;
import by.krevm.blackdesertbase.R;


public class IngredientsListFragment extends Fragment implements IngredientsListRVAdapter.ItemClickListener,SearchView.OnQueryTextListener {
    ArrayList<IngredientFromParse> ingredients;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private IngredientsListRVAdapter mAdapter;

    public static IngredientsListFragment newInstance() {
        IngredientsListFragment fragment = new IngredientsListFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ingredients_list_fragment, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.ingredients_list);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        ingredients=new ArrayList<>();
        for (IngredientFromParse ing : CookingFragment.allIngredients) {
            if (!ing.isResult()) {
                System.out.println(ing.getGroupId() + " " + ing.getParseId());
                ingredients.add(ing);
            }
        }
        setAdapter();
        return view;
    }

    private void setAdapter() {
        mAdapter = new IngredientsListRVAdapter(ingredients, getActivity());
        mAdapter.setClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view, IngredientFromParse ing) {
        System.out.println("Click " + ing.getName());
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, IngredientFragment.newInstance(ing)).addToBackStack("stek").commit();
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
        ArrayList<IngredientFromParse>ingFilteredList = new ArrayList<>();
        for (IngredientFromParse ing:ingredients){
            String name = ing.getName().toLowerCase();
            if(name.contains(queryQ)){
                ingFilteredList.add(ing);
            }
        }
        mAdapter.setFilteredList(ingFilteredList);
        return false;
    }
}
