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
    private IngredientFromParse dish;
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
        HashMap<String, Integer> amount = new HashMap<>();
        ArrayList<IngredientFromParse> resList = new ArrayList<>();
        TextView effect1TextView = (TextView) view.findViewById(R.id.effect1);
        TextView effect2TextView = (TextView) view.findViewById(R.id.effect2);
        TextView effect3TextView = (TextView) view.findViewById(R.id.effect3);
        TextView durationTextView = (TextView) view.findViewById(R.id.duration);
        durationTextView.setText(dish.getDuration()+" мин.");
        String[] effects = dish.getEffects();
        if(effects!=null) {
            switch (effects.length) {
                case 1: {
                    effect1TextView.setText(effects[0]);
                    effect2TextView.setVisibility(View.GONE);
                    effect3TextView.setVisibility(View.GONE);
                    break;
                }
                case 2: {
                    effect1TextView.setText(effects[0]);
                    effect2TextView.setText(effects[1]);
                    effect3TextView.setVisibility(View.GONE);
                    break;
                }
                case 3: {
                    effect1TextView.setText(effects[0]);
                    effect2TextView.setText(effects[1]);
                    effect3TextView.setText(effects[2]);
                    break;
                }

            }
        }
        amount.clear();
        resList.clear();
        ImageView dishImage = (ImageView) view.findViewById(R.id.imageViewDishFragment);
        TextView dishName = (TextView) view.findViewById(R.id.dishNameTextView);
        dishImage.setImageBitmap(dish.getBmp());
        dishName.setText(dish.getName());
        mRecyclerView = (RecyclerView) view.findViewById(R.id.ingredients_list_ii);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        System.out.println("Dish ing1 id " + dish.getIng1Id());
        System.out.println("In list " + resList.size());
        for (IngredientFromParse ing : CookingFragment.allIngredients) {

            String ing1;
            ing1 = dish.getIng1Id();
            System.out.println(ing1);
            if (ing.getParseId().equals(ing1)) {
                resList.add(ing);
                amount.put(ing.getParseId(), dish.getAmount1());
            }
            if (ing.getParseId().equals(dish.getIng2Id())) {
                resList.add(ing);
                amount.put(ing.getParseId(), dish.getAmount2());
            }
            if (ing.getParseId().equals(dish.getIng3Id())) {
                resList.add(ing);
                amount.put(ing.getParseId(), dish.getAmount3());
            }
            if (ing.getParseId().equals(dish.getIng4Id())) {
                resList.add(ing);
                amount.put(ing.getParseId(), dish.getAmount4());
            }
            if (ing.getParseId().equals(dish.getIng5Id())) {
                resList.add(ing);
                amount.put(ing.getParseId(), dish.getAmount5());
            }
        }
        System.out.println("After for " + amount.size());
        IngredientsInDishRVAdapter adapter = new IngredientsInDishRVAdapter(resList, amount);
        mRecyclerView.setAdapter(adapter);
        return view;
    }
}
