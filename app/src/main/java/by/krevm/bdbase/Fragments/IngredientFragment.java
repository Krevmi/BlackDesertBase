package by.krevm.bdbase.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import by.krevm.bdbase.Adapters.DishesListRVAdapter;
import by.krevm.bdbase.IngredientFromParse;
import by.krevm.bdbase.R;

public class IngredientFragment extends Fragment implements DishesListRVAdapter.ItemClickListener {
    TextView descriptionTextView, acquisitionTextView;
    ImageView imageView;
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    String groupId;
    String ingredientId;
    String objectId;
    ArrayList<IngredientFromParse> resList = new ArrayList<>();

    public static IngredientFragment newInstance(IngredientFromParse ing) {
        Bundle args = new Bundle();
        args.putParcelable("key", ing);
        IngredientFragment fragment = new IngredientFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ingredient_fragment, container, false);
        TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.tab_layout);
        tabLayout.setVisibility(View.GONE);
        setHasOptionsMenu(true);
        resList.clear();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        descriptionTextView = (TextView) view.findViewById(R.id.ing_description_text);
        acquisitionTextView = (TextView) view.findViewById(R.id.acquisition_text);
        imageView = (ImageView) view.findViewById(R.id.ingredient_imageView);
        recyclerView = (RecyclerView) view.findViewById(R.id.dishes_list_ii);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        if (getArguments() != null && getArguments().containsKey("key")) {
            IngredientFromParse ing = getArguments().getParcelable("key");
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(ing.getName());

            if (ing.getTupe() != null && ing.getTupe().equals("k")) {
                String[]effects=ing.getEffects();
                descriptionTextView.setText("Используется для инкрустации "+ing.getDescription()+
                " "+effects[0]);
                acquisitionTextView.setText("Вероятность разрушения: " + ing.getAcquisition());
            } else {
                descriptionTextView.setText(ing.getDescription());
                acquisitionTextView.setText("Способ получения: " + ing.getAcquisition());
            }
            imageView.setImageBitmap(ing.getBmp());
            groupId = ing.getGroupId();
            objectId = ing.getObjectId();
        }

        if (groupId != null) {
            ingredientId = groupId;
        } else ingredientId = objectId;
        if (ingredientId.equals("g")) {//если инг. это группа
            TextView listHeder = (TextView) view.findViewById(R.id.textView2);
            listHeder.setText("Любое из:");
            acquisitionTextView.setVisibility(View.GONE);
            for (IngredientFromParse ing : CookingFragment.allIngredients) {
                if (ing.getGroupId() != null)
                    if (ing.getGroupId().equals(objectId)) {
                        resList.add(ing);
                    }
            }
        } else {

            for (IngredientFromParse ing : CookingFragment.allIngredients) {
                if (ing.hasIngredient(ingredientId)) {
                    resList.add(ing);
                }
            }
        }
        setAdapter(resList);
        return view;
    }

    private void setAdapter(ArrayList<IngredientFromParse> dishesList) {
        DishesListRVAdapter mAdapter = new DishesListRVAdapter(dishesList);
        mAdapter.setClickListener(this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view, IngredientFromParse ing) {
        if (ing.isResult()) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, DishFragment.newInstance(ing)).addToBackStack("stek").commit();
        } else {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, IngredientFragment.newInstance(ing)).addToBackStack("stek").commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        System.out.println("onOptionsItemSelected");
        switch (item.getItemId()) {
            case android.R.id.home:
                System.out.println("Click back");
                getActivity().getSupportFragmentManager().popBackStack();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
