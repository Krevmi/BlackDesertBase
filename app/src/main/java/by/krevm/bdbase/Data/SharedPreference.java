package by.krevm.bdbase.Data;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Krevm on 11.01.2016.
 */
public class SharedPreference {

    public static final String PREFS_NAME = "PRODUCT_APP";
    public static final String FAVORITES = "Item_Favorite";
    private Context context;
    public SharedPreference(Context context) {
        super();
        this.context = context;
    }

    public void saveFavorites(Set<String> favorites) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();
        System.out.println("save favor size " + favorites.size());
        editor.putStringSet(FAVORITES, favorites);
        editor.commit();
    }

    public void addFavorite(String ingredientFromParseId) {
        Set<String> favorites = getFavorites();
        if (favorites == null) {
            System.out.println("favorites == null");

            System.out.println("favorites.size() == 0");
            favorites = new HashSet<>();
        }
        System.out.println("favorit size " + favorites.size());
        favorites.add(ingredientFromParseId);
        saveFavorites( favorites);
    }

    public void removeFavorite(String ingredientFromParseId) {
        Set<String> favorites = getFavorites();
        if (favorites != null) {
            favorites.remove(ingredientFromParseId);
            saveFavorites(favorites);
        }
    }

    public HashSet<String> getFavorites() {
        SharedPreferences settings;
        Set<String> favorites;
        HashSet<String> def = new HashSet<>();
        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            favorites = new HashSet<>(settings.getStringSet(FAVORITES, def));
        } else
            return null;
        System.out.println("get favor size " + favorites.size());
        return (HashSet<String>) favorites;
    }
    public boolean isFavorite(String parseId){
        if(getFavorites()!=null) {
            Set<String> favorites = getFavorites();
            return (favorites.contains(parseId));
        }else return false;
    }
    public void clickFavorite(String parseId){
        if(isFavorite(parseId)){
            removeFavorite(parseId);
        }else {
            addFavorite(parseId);
        }
    }
}
