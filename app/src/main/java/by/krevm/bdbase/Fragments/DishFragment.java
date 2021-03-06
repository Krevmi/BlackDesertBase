package by.krevm.bdbase.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import by.krevm.bdbase.Adapters.DishesListRVAdapter;
import by.krevm.bdbase.Adapters.IngredientsInDishWithHeaderRVAdapter;
import by.krevm.bdbase.Data.SharedPreference;
import by.krevm.bdbase.IngredientFromParse;
import by.krevm.bdbase.R;


public class DishFragment extends Fragment implements DishesListRVAdapter.ItemClickListener {
    private IngredientFromParse dish;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    IngredientsInDishWithHeaderRVAdapter adapter = null;
    private SharedPreference sharedPreference;

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
        sharedPreference = new SharedPreference(getActivity());
      //  System.out.println("favorite "+sharedPreference.getFavorites(getActivity()).size());
        setHasOptionsMenu(true);
        TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.tab_layout);
        tabLayout.setVisibility(View.GONE);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (getArguments() != null && getArguments().containsKey("key")) {
            dish = getArguments().getParcelable("key");
        }

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(dish.getName());
        final FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        if(sharedPreference.isFavorite(dish.getParseId())) {
            fab.setImageResource(R.mipmap.ic_star);
        }else {
            fab.setImageResource(R.mipmap.ic_star_outline);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreference.clickFavorite(dish.getParseId());
                if(sharedPreference.isFavorite(dish.getParseId())) {
                    fab.setImageResource(R.mipmap.ic_star);
                    Snackbar snackbar = Snackbar.make(view,"Добавленно в избранное",Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }else {
                    fab.setImageResource(R.mipmap.ic_star_outline);
                    Snackbar snackbar = Snackbar.make(view,"Удалено из избранного",Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }
        });
        HashMap<String, Integer> amount = new HashMap<>();
        ArrayList<IngredientFromParse> resList = new ArrayList<>();
        TextView effect1TextView = (TextView) view.findViewById(R.id.effect1);
        TextView effect2TextView = (TextView) view.findViewById(R.id.effect2);
        TextView effect3TextView = (TextView) view.findViewById(R.id.effect3);
        TextView durationTextView = (TextView) view.findViewById(R.id.duration);
        TextView reloadingTextView = (TextView) view.findViewById(R.id.reloadingTextView);
        if (dish.getReloading() != null) {
            reloadingTextView.setText("Перезарядка: " + dish.getReloading());
        } else {
            reloadingTextView.setVisibility(View.GONE);
        }

        if (dish.getDuration() != null) {
            durationTextView.setText("Время действия: " + dish.getDuration() + " мин.");
        } else {
            durationTextView.setVisibility(View.GONE);
        }
        String[] effects = dish.getEffects();
        if (effects != null) {
            switch (effects.length) {
                case 1: {
                    System.out.println("case 1 " + dish.getTupe());
                    effect1TextView.setText(effects[0]);

                    if (dish.getTupe() != null && dish.getTupe().equals("k")) {
                        System.out.println("dish.getTupe().equals(\"k\")");
                        effect2TextView.setText("Вероятность разрушения: " + dish.getAcquisition());
                        effect3TextView.setVisibility(View.GONE);
                    } else {
                        System.out.println("dish.getTupe().equals(\"k\") else");
                        effect2TextView.setVisibility(View.GONE);
                        effect3TextView.setVisibility(View.GONE);
                    }
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
        } else {
            effect1TextView.setVisibility(View.GONE);
            effect2TextView.setVisibility(View.GONE);
            effect3TextView.setVisibility(View.GONE);
            durationTextView.setVisibility(View.GONE);
        }
        amount.clear();
        resList.clear();
        ImageView dishImage = (ImageView) view.findViewById(R.id.imageViewDishFragment);
        TextView dishDiscription = (TextView) view.findViewById(R.id.discription_dish);
        dishImage.setImageBitmap(dish.getBmp());
        if (dish.getTupe() != null && dish.getTupe().equals("k")) {
            dishDiscription.setText("Используется для инкрустации " + dish.getDescription());
        } else {
            dishDiscription.setText(dish.getDescription());
        }
        mRecyclerView = (RecyclerView) view.findViewById(R.id.ingredients_list_ii);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        for (IngredientFromParse ing : CookingFragment.allIngredients) {

            String ing1;
            ing1 = dish.getIng1Id();

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

        adapter = new IngredientsInDishWithHeaderRVAdapter(resList, amount);
        adapter.setClickListener(this);
        mRecyclerView.setAdapter(adapter);
        return view;
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
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().getSupportFragmentManager().popBackStack();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
