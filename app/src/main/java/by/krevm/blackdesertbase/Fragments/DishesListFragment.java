package by.krevm.blackdesertbase.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.krevm.blackdesertbase.R;

/**
 * Created by KrEvM on 06.11.2015.
 */
public class DishesListFragment extends Fragment {
    public static DishesListFragment newInstance(){
        DishesListFragment fragment = new DishesListFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dishes_list_fragment_layout,container,false);
        return view;
    }
}
