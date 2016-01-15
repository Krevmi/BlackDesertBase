package by.krevm.bdbase;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.parse.Parse;
import com.parse.ParseObject;


public class ParseAppInitialization extends android.app.Application {
    private Tracker mTracker;

    /**
     * Gets the default {@link Tracker} for this {@link Application}.
     * @return tracker
     */
    synchronized public Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
            mTracker = analytics.newTracker(R.xml.global_tracker);
        }
        return mTracker;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(IngredientFromParse.class);
        ParseObject.registerSubclass(Recipe.class);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "sW9ekJfTgdbont3HdayaBcrQcVZ61gjUsnDTdw4n", "hkp8YuciFSR8SrdiQEf0mG4sZ2T0ej8NjHWF6Gqx");
    }

}
