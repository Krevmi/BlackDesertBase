package by.krevm.bdbase;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Recipe")
public class Recipe extends ParseObject {
   IngredientFromParse ing1, ing2, ing3, ing4, ing5, res1, res2;
    int amount1, amount2, amount3, amount4, amount5;
    ParseObject obj1,obj2,obj3,obj4,obj5,r1,r2;

    public IngredientFromParse getIng1() {

        return(IngredientFromParse) getParseObject("ingredient1");
    }

    public IngredientFromParse getIng2() {
        return(IngredientFromParse) getParseObject("ingredient2");
    }

    public IngredientFromParse getIng3() {
        return(IngredientFromParse) getParseObject("ingredient3");
    }

    public IngredientFromParse getIng4() {
        return(IngredientFromParse) getParseObject("ingredient4");
    }

    public IngredientFromParse getIng5() {
        return(IngredientFromParse) getParseObject("ingredient5");
    }

    public IngredientFromParse getRes1() {
        return(IngredientFromParse) getParseObject("result1");
    }

    public IngredientFromParse getRes2() {
        return(IngredientFromParse) getParseObject("result2");
    }

    public int getAmount1() {
        return getInt("amount1");
    }

    public int getAmount2() {
        return getInt("amount2");
    }

    public int getAmount3() {
        return getInt("amount3");
    }

    public int getAmount4() {
        return getInt("amount4");
    }

    public int getAmount5() {
        return getInt("amount5");
    }

    public boolean hasIngredient(String objectId){
        String id1,id2,id3,id4,id5;
        if(getIng1().getObjectId().equals(objectId))return true;
        if(getIng1().getObjectId().equals(objectId))return true;
        if(getIng2().getObjectId().equals(objectId))return true;
        if(getIng3().getObjectId().equals(objectId))return true;
        if(getIng4().getObjectId().equals(objectId))return true;
        return getIng5().getObjectId().equals(objectId);

    }
}
