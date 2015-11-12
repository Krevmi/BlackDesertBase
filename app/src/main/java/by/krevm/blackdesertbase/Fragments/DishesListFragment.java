package by.krevm.blackdesertbase.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import by.krevm.blackdesertbase.IngredientFromParse;
import by.krevm.blackdesertbase.R;
import by.krevm.blackdesertbase.Recipe;


public class DishesListFragment extends Fragment {
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
        ParseQuery<Recipe> queryRecipe = ParseQuery.getQuery("Recipe");

        queryRecipe.findInBackground(new FindCallback<Recipe>() {
            @Override
            public void done(List<Recipe> list, ParseException e) {
                for (Recipe rec:list) {
                    System.out.println(rec.getIng1().getObjectId()+" "+
                            rec.getIng2().getObjectId()+" "+
                            rec.getIng3().getObjectId()+" "+
                            rec.getIng4().getObjectId()+" "+
                            rec.getIng5().getObjectId());

                }
            }
        });
        return view;
    }
}
