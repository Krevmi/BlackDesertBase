package by.krevm.blackdesertbase.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import by.krevm.blackdesertbase.Adapters.DishesListRVAdapter;
import by.krevm.blackdesertbase.IngredientFromParse;
import by.krevm.blackdesertbase.R;
import by.krevm.blackdesertbase.Recipe;

public class IngredientFragment extends Fragment implements DishesListRVAdapter.ItemClickListener {
    TextView nameTextView, descriptionTextView, acquisitionTextView;
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
        nameTextView = (TextView) view.findViewById(R.id.ing_name_text);
        descriptionTextView = (TextView) view.findViewById(R.id.ing_description_text);
        acquisitionTextView = (TextView) view.findViewById(R.id.acquisition_text);
        imageView = (ImageView) view.findViewById(R.id.ingredient_imageView);
        recyclerView = (RecyclerView) view.findViewById(R.id.dishes_list_ii);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        if (getArguments() != null && getArguments().containsKey("key")) {
            IngredientFromParse ing = getArguments().getParcelable("key");
            nameTextView.setText(ing.getName());
            descriptionTextView.setText(ing.getDescription());
            acquisitionTextView.setText(ing.getAcquisition());
            imageView.setImageBitmap(ing.getBmp());
            groupId = ing.getGroupId();
            objectId = ing.getObjectId();
        }

        if (groupId != null) {
            ingredientId = groupId;
        } else ingredientId = objectId;
        final ArrayList<String> results = new ArrayList<>();
     /*  ParseQuery<Recipe> queryRecipe = ParseQuery.getQuery(Recipe.class);
        queryRecipe.findInBackground(new FindCallback<Recipe>() {
            @Override
            public void done(List<Recipe> list, ParseException e) {
                for (Recipe recipe : list) {
                    System.out.println(recipe.hasIngredient(ingredientId));
                    if (recipe.hasIngredient(ingredientId)) {
                        results.add(recipe.getRes1().getObjectId());
                    }
                }
                ParseQuery<IngredientFromParse> query = ParseQuery.getQuery(IngredientFromParse.class);
                query.whereContainedIn("objectId", results);
                query.findInBackground(new FindCallback<IngredientFromParse>() {
                    @Override
                    public void done(List<IngredientFromParse> list, ParseException e) {
                        ArrayList<IngredientFromParse> resList = new ArrayList<>(list);
                        setAdapter(resList);
                    }
                });
            }
        });
*/
        for (IngredientFromParse ing:CookingFragment.allIngredients){
            if(ing.hasIngredient(ingredientId)){
              resList.add(ing);
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
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, DishFragment.newInstance(ing)).addToBackStack("stek").commit();
    }
}
