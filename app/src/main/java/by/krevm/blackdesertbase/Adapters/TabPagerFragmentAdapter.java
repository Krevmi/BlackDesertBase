package by.krevm.blackdesertbase.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.support.v4.app.FragmentStatePagerAdapter;

import by.krevm.blackdesertbase.Fragments.DishesListFragment;
import by.krevm.blackdesertbase.Fragments.IngredientsListFragment;

public class TabPagerFragmentAdapter extends FragmentStatePagerAdapter {
    String[] tabs;
    int tabLength;
    public TabPagerFragmentAdapter(FragmentManager fm,String[] tabs) {
        super(fm);
        this.tabs=tabs;
        tabLength =tabs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public Fragment getItem(int position) {
        if(tabLength>2)
        switch (position){
            case 0:return DishesListFragment.newInstance("z");
            case 1:return DishesListFragment.newInstance("k");
            case 2:return DishesListFragment.newInstance("r");
            case 3:return IngredientsListFragment.newInstance();
        }else {
            switch (position) {
                case 0:
                    return DishesListFragment.newInstance();
                case 1:
                    return IngredientsListFragment.newInstance();
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabLength;
    }
}
