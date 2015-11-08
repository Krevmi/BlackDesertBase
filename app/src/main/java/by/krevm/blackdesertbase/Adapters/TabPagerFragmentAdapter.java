package by.krevm.blackdesertbase.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import by.krevm.blackdesertbase.Fragments.DishesListFragment;
import by.krevm.blackdesertbase.Fragments.IngredientsListFragment;

/**
 * Created by KrEvM on 06.11.2015.
 */
public class TabPagerFragmentAdapter extends FragmentPagerAdapter {
    String[] tabs;
    public TabPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
        tabs = new String[]{"Ингредиенты","Блюда"};
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:return IngredientsListFragment.newInstance();
            case 1:return DishesListFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
