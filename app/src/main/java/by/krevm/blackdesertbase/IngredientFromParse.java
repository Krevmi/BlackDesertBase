package by.krevm.blackdesertbase;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

@ParseClassName("ingredient")
public class IngredientFromParse extends ParseObject {
    String name,parseId;

    public String getName() {
        return getString("Name");
    }

    public String getParseId() {
        return getObjectId();
    }
    public ParseFile getImg(){
        return getParseFile("Img");
    }
}
