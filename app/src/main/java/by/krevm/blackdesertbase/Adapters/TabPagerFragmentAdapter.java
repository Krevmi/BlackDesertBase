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
