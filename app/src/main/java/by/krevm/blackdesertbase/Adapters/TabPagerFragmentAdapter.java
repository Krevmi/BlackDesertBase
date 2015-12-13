package by.krevm.blackdesertbase.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.support.v4.app.FragmentStatePagerAdapter;

import by.krevm.blackdesertbase.Fragments.DishesListFragment;
import by.krevm.blackdesertbase.Fragments.IngredientsListFragment;

public class TabPagerFragmentAdapter extends FragmentStatePagerAdapter {
    String[] tabs;
    public TabPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
        tabs = new String[]{"Результат","Ингредиенты"};
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:return DishesListFragment.newInstance();
            case 1:return IngredientsListFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
