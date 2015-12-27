package by.krevm.bdbase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import by.krevm.bdbase.Fragments.CookingFragment;
import by.krevm.bdbase.Fragments.MainActivityNoInternet;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    FragmentManager fragmentManager;
    Toolbar toolbar;
    private Tracker mTracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()) {
        } else {
            Intent intent = new Intent(this, MainActivityNoInternet.class);
            startActivity(intent);
            finish();
        }
        setContentView(R.layout.activity_main);

        ParseAppInitialization application = (ParseAppInitialization) getApplication();
        mTracker = application.getDefaultTracker();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        initNavigationView();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.container, CookingFragment.newInstance(R.string.cookery)).commit();

    /*    AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("meizu-m1_note-0123456789ABCDEF").build();
        mAdView.loadAd(adRequest);*/
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        fragmentManager.popBackStack("stek",FragmentManager.POP_BACK_STACK_INCLUSIVE);
        int id = item.getItemId();
        if (id == R.id.nav_meny_cooking) {
            mTracker.send(new HitBuilders.EventBuilder()
                    .setCategory("Action")
                    .setAction("Кулинария")
                    .build());
            fragmentManager.beginTransaction().replace(R.id.container, CookingFragment.newInstance(R.string.cookery)).commit();
        } else if (id == R.id.nav_meny_alchemy) {
            mTracker.send(new HitBuilders.EventBuilder()
                    .setCategory("Action")
                    .setAction("Алхимия")
                    .build());
            fragmentManager.beginTransaction().replace(R.id.container, CookingFragment.newInstance(R.string.alchemy)).commit();
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
        //  ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        //    drawerLayout.setDrawerListener(toggle);
        //  toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        SharedPreferences sp = getSharedPreferences("firstLaunch",0);
        boolean isFirstStart = sp.getBoolean("key", true);
        if(isFirstStart) {
            drawerLayout.openDrawer(Gravity.LEFT);
            SharedPreferences.Editor e = sp.edit();
            // we save the value "false", indicating that it is no longer the first appstart
            e.putBoolean("key", false);
            e.commit();
        }
    }

    @Override
    public void onBackPressed() {
        System.out.println(" onBackPressed");
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else if (getFragmentManager().getBackStackEntryCount() > 0)
            getFragmentManager().popBackStack();
        else
            super.onBackPressed();
    }
}
