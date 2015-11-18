package by.krevm.blackdesertbase.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import by.krevm.blackdesertbase.IngredientFromParse;
import by.krevm.blackdesertbase.R;

public class IngredientFragment extends Fragment{
    TextView nameTextView,descriptionTextView;

    public static IngredientFragment newInstance(IngredientFromParse ing){
        System.out.println("newInstance IngredientFragment");
        Bundle args = new Bundle();
        args.putParcelable("key",ing);
       IngredientFragment fragment = new IngredientFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ingredient_fragment,container,false);
        nameTextView = (TextView)view.findViewById(R.id.ing_name_text);
        descriptionTextView= (TextView)view.findViewById(R.id.ing_description_text);
        if (getArguments() != null && getArguments().containsKey("key")) {
            IngredientFromParse ing = getArguments().getParcelable("key");
           nameTextView.setText(ing.getName());
            descriptionTextView.setText(ing.getDescription());
        }
        return view;
    }
}
