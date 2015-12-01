package by.krevm.blackdesertbase;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by Krevm on 28.11.2015.
 */
public class ParseAppInitialization extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(IngredientFromParse.class);
        ParseObject.registerSubclass(Recipe.class);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "sW9ekJfTgdbont3HdayaBcrQcVZ61gjUsnDTdw4n", "hkp8YuciFSR8SrdiQEf0mG4sZ2T0ej8NjHWF6Gqx");
    }

}
