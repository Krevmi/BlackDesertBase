package by.krevm.blackdesertbase.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import by.krevm.blackdesertbase.Adapters.DishesListRVAdapter;
import by.krevm.blackdesertbase.Adapters.IngredientsListRVAdapter;
import by.krevm.blackdesertbase.DividerItemDecoration;
import by.krevm.blackdesertbase.IngredientFromParse;
import by.krevm.blackdesertbase.R;
import by.krevm.blackdesertbase.Recipe;


public class DishesListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    public static DishesListFragment newInstance(){
        DishesListFragment fragment = new DishesListFragment();
        System.out.println("Dishes fragment create");
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("Dishes fragment create");
        View view = inflater.inflate(R.layout.dishes_list_fragment_layout,container,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.dishes_list);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        final ArrayList<String> results=new ArrayList<>();
        ParseQuery<Recipe> queryRecipe = ParseQuery.getQuery("Recipe");
        queryRecipe.findInBackground(new FindCallback<Recipe>() {
            @Override
            public void done(List<Recipe> list, ParseException e) {
                for (Recipe rec : list) {
                    results.add(rec.getRes1().getObjectId());
                }
                System.out.println("results list size = " + results.size());
                ParseQuery<IngredientFromParse> query = ParseQuery.getQuery(IngredientFromParse.class);
                query.whereContainedIn("objectId", results);
                query.findInBackground(new FindCallback<IngredientFromParse>() {
                    @Override
                    public void done(List<IngredientFromParse> list, ParseException e) {
                        ArrayList <IngredientFromParse> resList = new ArrayList<>(list);
                      DishesListRVAdapter  mAdapter = new DishesListRVAdapter(resList);
                        mRecyclerView.setAdapter(mAdapter);
                        for (IngredientFromParse ing : list) {
                            System.out.println(ing.getName());
                        }
                    }
                });
            }
        });


        return view;
    }
}
