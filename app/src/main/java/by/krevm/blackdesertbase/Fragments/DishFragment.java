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
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import by.krevm.blackdesertbase.Adapters.IngredientsInDishRVAdapter;
import by.krevm.blackdesertbase.IngredientFromParse;
import by.krevm.blackdesertbase.R;
import by.krevm.blackdesertbase.Recipe;


public class DishFragment extends Fragment {
    IngredientFromParse dish;
    HashMap<String,Integer>amount = new HashMap<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    public static DishFragment newInstance(IngredientFromParse ing) {
        Bundle args = new Bundle();
        args.putParcelable("key", ing);
        DishFragment fragment = new DishFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dish_fragment, container, false);
        if (getArguments() != null && getArguments().containsKey("key")) {
            dish = getArguments().getParcelable("key");
        }
        ImageView dishImage = (ImageView) view.findViewById(R.id.imageViewDishFragment);
        TextView dishName = (TextView) view.findViewById(R.id.dishNameTextView);
        dishImage.setImageBitmap(dish.getBmp());
        dishName.setText(dish.getName());
        mRecyclerView = (RecyclerView) view.findViewById(R.id.ingredients_list_ii);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        final ArrayList<String> ingredientsId = new ArrayList<String>();
        ParseQuery<Recipe> queryRecipe = ParseQuery.getQuery(Recipe.class);
        queryRecipe.whereEqualTo("result1", dish);
        queryRecipe.findInBackground(new FindCallback<Recipe>() {
            @Override
            public void done(List<Recipe> list, ParseException e) {
                for (Recipe recipe : list) {
                    if (!recipe.getIng1().getObjectId().equals("0")) {
                        ingredientsId.add(recipe.getIng1().getObjectId());
                    }
                    if (!recipe.getIng2().getObjectId().equals("0")) {
                        ingredientsId.add(recipe.getIng2().getObjectId());
                    }
                    if (!recipe.getIng3().getObjectId().equals("0")) {
                        ingredientsId.add(recipe.getIng3().getObjectId());
                    }
                    if (!recipe.getIng4().getObjectId().equals("0")) {
                        ingredientsId.add(recipe.getIng4().getObjectId());
                    }
                    if (!recipe.getIng5().getObjectId().equals("0")) {
                        ingredientsId.add(recipe.getIng5().getObjectId());
                    }
                    if (recipe.getAmount1() != 0)
                        amount.put(recipe.getIng1().getObjectId(), recipe.getAmount1());
                    if (recipe.getAmount2() != 0)
                        amount.put(recipe.getIng2().getObjectId(), recipe.getAmount2());
                    if (recipe.getAmount3() != 0)
                        amount.put(recipe.getIng3().getObjectId(), recipe.getAmount3());
                    if (recipe.getAmount4() != 0)
                        amount.put(recipe.getIng4().getObjectId(), recipe.getAmount4());
                    if (recipe.getAmount5() != 0)
                        amount.put(recipe.getIng5().getObjectId(),recipe.getAmount5());
                }
                ParseQuery<IngredientFromParse> query = ParseQuery.getQuery(IngredientFromParse.class);
                query.whereContainedIn("objectId", ingredientsId);
                query.findInBackground(new FindCallback<IngredientFromParse>() {
                    @Override
                    public void done(List<IngredientFromParse> list, ParseException e) {
                        ArrayList<IngredientFromParse> resList = new ArrayList<>(list);
                        IngredientsInDishRVAdapter adapter = new IngredientsInDishRVAdapter(resList, amount);
                        mRecyclerView.setAdapter(adapter);
                    }
                });
            }
        });
        return view;
    }
}
