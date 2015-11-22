package by.krevm.blackdesertbase.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import by.krevm.blackdesertbase.IngredientFromParse;
import by.krevm.blackdesertbase.R;


public class DishFragment extends Fragment {
    IngredientFromParse dish;
    public DishFragment newInstance(IngredientFromParse ing){
        Bundle args = new Bundle();
        args.putParcelable("key",ing);
        DishFragment fragment = new DishFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ingredient_item_in_dish,container,false);
        if(getArguments()!=null&&getArguments().containsKey("key")){
            dish = getArguments().getParcelable("key");
        }
        ImageView dishImage = (ImageView)view.findViewById(R.id.imageView);
        TextView dishName = (TextView)view.findViewById(R.id.dishNameTextView);
        dishImage.setImageBitmap(dish.getBmp());
        dishName.setText(dish.getName());
        return view;
    }
}
