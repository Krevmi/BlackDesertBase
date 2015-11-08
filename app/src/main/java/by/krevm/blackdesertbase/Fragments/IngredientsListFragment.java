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
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import by.krevm.blackdesertbase.Adapters.IngredientsListRVAdapter;
import by.krevm.blackdesertbase.DividerItemDecoration;
import by.krevm.blackdesertbase.IngredientFromParse;
import by.krevm.blackdesertbase.R;


public class IngredientsListFragment extends Fragment {
    ArrayList<IngredientFromParse> ingredients = new ArrayList<>();
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
        View view = inflater.inflate(R.layout.ingredients_list_fragment,container,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.ingredients_list);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        ParseQuery<IngredientFromParse> query = ParseQuery.getQuery(IngredientFromParse.class);
        query.findInBackground(new FindCallback<IngredientFromParse>() {
            @Override
            public void done(List<IngredientFromParse> list, ParseException e) {
                ingredients.addAll(list);
                mAdapter = new IngredientsListRVAdapter(ingredients);
                mRecyclerView.setAdapter(mAdapter);
                for (IngredientFromParse ing : list) {
                    System.out.println(ing.getName() + " " + ing.getParseId());
                }
            }
        });


        return view;
    }
}
