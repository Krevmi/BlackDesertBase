package by.krevm.blackdesertbase;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.parse.Parse;
import com.parse.ParseObject;

import by.krevm.blackdesertbase.Fragments.CookingFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
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
        fragmentManager.beginTransaction().add(R.id.container, new CookingFragment()).commit();
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
