package by.krevm.blackdesertbase.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // nameTextView = (TextView) view.findViewById(R.id.ing_name_text);
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

          //  nameTextView.setText(ing.getName());
            descriptionTextView.setText(ing.getDescription());
            acquisitionTextView.setText("Способ получения: "+ing.getAcquisition());
            imageView.setImageBitmap(ing.getBmp());
            groupId = ing.getGroupId();
            objectId = ing.getObjectId();
        }

        if (groupId != null) {
            ingredientId = groupId;
        } else ingredientId = objectId;

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        System.out.println("onOptionsItemSelected");
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                System.out.println("Click back");
                getActivity().getSupportFragmentManager().popBackStack();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
