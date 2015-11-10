package by.krevm.blackdesertbase;

import android.hardware.fingerprint.FingerprintManager;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.krevm.blackdesertbase.Adapters.TabPagerFragmentAdapter;
import by.krevm.blackdesertbase.Fragments.CookingFragment;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
   ViewPager viewPager;
FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        parseInitialize();
        initNavigationView();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.container,new CookingFragment()).commit();
      /*  initTab();
        //test super test
        ParseQuery<IngredientFromParse> query = ParseQuery.getQuery(IngredientFromParse.class);
        query.findInBackground(new FindCallback<IngredientFromParse>() {
            @Override
            public void done(List<IngredientFromParse> list, ParseException e) {
                for (IngredientFromParse ing : list) {
                    System.out.println(ing.getName() + " " + ing.getParseId());
                }
            }
        });

        ParseQuery<ParseObject> queryRecipe = ParseQuery.getQuery("Recipe");
        queryRecipe.include("ingredient");
        queryRecipe.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                for (int i = 0; i < list.size(); i++) {

                    System.out.println(list.get(i).getParseObject("ingredient1").getString("Name"));
                }
            }
        });

    }

    private void initTab() {
        viewPager = (ViewPager)findViewById(R.id.view_pager);
       TabLayout tabLayout= (TabLayout)findViewById(R.id.tab_layout);
        TabPagerFragmentAdapter adapter = new TabPagerFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }*/
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_meny_cooking) {
            // Handle the camera action
        } else if (id == R.id.nav_meny_cooking) {

        } else if (id == R.id.nav_meny_cooking) {

        } else if (id == R.id.nav_meny_cooking) {

        } else if (id == R.id.nav_meny_cooking) {

        } else if (id == R.id.nav_meny_cooking) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void initNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    private void parseInitialize() {
        ParseObject.registerSubclass(IngredientFromParse.class);
        ParseObject.registerSubclass(Recipe.class);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "sW9ekJfTgdbont3HdayaBcrQcVZ61gjUsnDTdw4n", "hkp8YuciFSR8SrdiQEf0mG4sZ2T0ej8NjHWF6Gqx");
    }
}
