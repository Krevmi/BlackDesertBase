package by.krevm.blackdesertbase;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
           toolbar = (Toolbar) findViewById(R.id.toolbar);
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


        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
       ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void parseInitialize() {
        new ParseAppInitialization();
    }
}
