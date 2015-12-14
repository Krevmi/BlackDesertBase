package by.krevm.blackdesertbase.Fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import by.krevm.blackdesertbase.Adapters.DishesListRVAdapter;
import by.krevm.blackdesertbase.DividerItemDecoration;
import by.krevm.blackdesertbase.IngredientFromParse;
import by.krevm.blackdesertbase.R;


public class DishesListFragment extends Fragment implements DishesListRVAdapter.ItemClickListener, SearchView.OnQueryTextListener {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    DishesListRVAdapter mAdapter;
    ArrayList<IngredientFromParse> resList;
    int filterPosition = 0;

    public DishesListFragment() {
        setArguments(new Bundle());
    }

    public static DishesListFragment newInstance() {
        DishesListFragment fragment = new DishesListFragment();
        return fragment;
    }
    public static DishesListFragment newInstance(String tupe) {
        Bundle args = new Bundle();
        args.putString("tupe",tupe);
        DishesListFragment fragment = new DishesListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dishes_list_fragment_layout, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.dishes_list);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        resList = new ArrayList<>();
        if(getArguments()!=null&&getArguments().containsKey("tupe")){
            String tupe=getArguments().getString("tupe");
            for (IngredientFromParse ing : CookingFragment.getAllIngredients()) {
                if(ing.isResult()&&ing.getTupe().equals(tupe)){
                    resList.add(ing);
                }
            }
        }else {
            for (IngredientFromParse ing : CookingFragment.getAllIngredients()) {
                if (ing.isResult()) {
                    resList.add(ing);
                }
            }
        }
        setAdapter(resList);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        if (getArguments() != null && getArguments().containsKey("filter")) {
            onFilterList(getArguments().getInt("filter"));
        }
        return view;
    }

    private void setAdapter(ArrayList<IngredientFromParse> list) {
        mAdapter = new DishesListRVAdapter(list);
        mAdapter.setClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view, IngredientFromParse ing) {
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

    private void showDialog() {
        new FireMissilesDialogFragment().show(getActivity().getSupportFragmentManager(), "");
    }

    @Override
    public void onPause() {
        super.onPause();
        getArguments().putInt("filter", filterPosition);
    }

    public void onFilterList(int position) {
        ArrayList<IngredientFromParse> ingFilteredList = new ArrayList<>();
        if (position == 0) {
            mAdapter.setFilteredList(resList);
        }
        if (position != 0) {
            String[] allEffects = getResources().getStringArray(R.array.all_effects);
            for (IngredientFromParse ing : resList) {
                if (ing.hasEffect(allEffects[position])) {
                    ingFilteredList.add(ing);
                }
            }
            mAdapter.setFilteredList(ingFilteredList);
        }
    }

    @SuppressLint("ValidFragment")
    public class FireMissilesDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Выберите эффект")
                    .setItems(R.array.all_effects, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            filterPosition = which;
                            onFilterList(which);
                        }
                    });
            return builder.create();
        }
    }
}
