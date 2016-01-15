package by.krevm.bdbase.Data;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import by.krevm.bdbase.Fragments.CookingFragment;
import by.krevm.bdbase.IngredientFromParse;

public class LoadDataFromParse {
    public static ArrayList<IngredientFromParse> getFavorites(final Set<String> favoritesId) {

        final ArrayList<IngredientFromParse> favorites = new ArrayList<>();
       /* ParseQuery<IngredientFromParse> query = ParseQuery.getQuery(IngredientFromParse.class);
        query.fromLocalDatastore();
        query.findInBackground(new FindCallback<IngredientFromParse>() {
            @Override
            public void done(List<IngredientFromParse> list, ParseException e) {
                System.out.println("Load data from local data size  "+ list.size());
                for (IngredientFromParse item: list){
                    if(favoritesId.contains(item.getParseId())){
                        favorites.add(item);
                    }
                }
            }
        });*/
        for(IngredientFromParse item: CookingFragment.allIngredients){
            if(favoritesId.contains(item.getParseId())) {
                favorites.add(item);
            }
        }
        return favorites;
    }
}
