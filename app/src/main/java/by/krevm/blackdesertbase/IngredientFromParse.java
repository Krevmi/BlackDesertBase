package by.krevm.blackdesertbase;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

import com.parse.GetDataCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;

@ParseClassName("ingredient")
public class IngredientFromParse extends ParseObject implements Parcelable {
    private String name;
    private String parseId;
    private String description;
    private Bitmap bmp;
    private String acquisition;
    private String groupId;
    private String ing1Id, ing2Id, ing3Id, ing4Id, ing5Id;
    private int amount1, amount2, amount3, amount4, amount5;
    private String[] effects;
    private String duration;
    private String useIn;
    private String tupe;

    public String getTupe() {
        tupe=getString("tupe");
        return tupe;
    }

    public String getUseIn() {
        useIn=getString("useIn");
        return useIn;
    }

    public String getDuration() {
        duration= getString("duration");
        return duration;
    }

    public String[] getEffects() {
        if(getString("effect")!=null) {
            effects = getString("effect").split("/");
            return effects;
        }return null;
    }

    public String getIng1Id() {
        if (getString("ingredient1") != null && !getString("ingredient1").equals("0/0")) {
            ing1Id = getString("ingredient1").substring(0, 10);
            return ing1Id;
        }
        return "0";
    }

    public String getIng2Id() {
        if (getString("ingredient2") != null && !getString("ingredient2").equals("0/0")) {
            ing2Id = getString("ingredient2").substring(0, 10);
            return ing2Id;
        }
        return "0";
    }

    public String getIng3Id() {
        if (getString("ingredient3") != null && !getString("ingredient3").equals("0/0")) {
            ing3Id = getString("ingredient3").substring(0, 10);
            return ing3Id;
        }
        return "0";
    }

    public String getIng4Id() {
        if (getString("ingredient4") != null && !getString("ingredient4").equals("0/0")) {
            ing4Id = getString("ingredient4").substring(0, 10);
            return ing4Id;
        }
        return "0";
    }

    public String getIng5Id() {
        if (getString("ingredient5") != null && !getString("ingredient5").equals("0/0")) {
            ing5Id = getString("ingredient5").substring(0, 10);
            return ing5Id;
        }
        return "0";
    }

    public int getAmount1() {
        if (getString("ingredient1") != null && !getString("ingredient1").equals("0/0")) {
            amount1 = Integer.parseInt(getString("ingredient1").substring(11));
            return amount1;
        }
        return 0;
    }

    public int getAmount2() {
        if (getString("ingredient2") != null && !getString("ingredient2").equals("0/0")) {
            amount2 = Integer.parseInt(getString("ingredient2").substring(11));
            return amount2;
        }
        return 0;
    }

    public int getAmount3() {
        if (getString("ingredient3") != null && !getString("ingredient3").equals("0/0")) {
            amount3 = Integer.parseInt(getString("ingredient3").substring(11));
            return amount3;
        }
        return 0;
    }

    public int getAmount4() {
        if (getString("ingredient4") != null && !getString("ingredient4").equals("0/0")) {
            amount4 = Integer.parseInt(getString("ingredient4").substring(11));
            return amount4;
        }
        return 0;
    }

    public int getAmount5() {
        if (getString("ingredient5") != null && !getString("ingredient5").equals("0/0")) {
            amount5 = Integer.parseInt(getString("ingredient5").substring(11));
            return amount5;
        }
        return 0;
    }

    public String getGroupId() {
        groupId = getString("groupId");
        return groupId;
    }

    public String getAcquisition() {
        acquisition = getString("acquisition");
        return acquisition;
    }

    public IngredientFromParse() {

    }

    public Bitmap getBmp() {
        try {
            byte[] bytes = getParseFile("Img").getData();
            bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return bmp;
    }

    public String getDescription() {
        description = getString("description");
        return description;
    }

    public String getName() {
        name = getString("Name");
        return name;
    }

    public String getParseId() {
        parseId = getObjectId();
        return parseId;
    }

    public ParseFile getImg() {
        return getParseFile("Img");
    }

    public boolean isResult() {
        return getBoolean("result");
    }

    public boolean hasIngredient(String ingredientId) {
        if (getIng1Id().equals(ingredientId)) return true;
        if (getIng2Id().equals(ingredientId)) return true;
        if (getIng3Id().equals(ingredientId)) return true;
        if (getIng4Id().equals(ingredientId)) return true;
        if (getIng5Id().equals(ingredientId)) return true;
        return false;
    }

    public boolean hasEffect(String effect){
        if(getString("effect")!=null){
        if(getString("effect").contains(effect)) {
            return true;
        }
        }return false;
    }

    protected IngredientFromParse(Parcel in) {
        parseId = in.readString();
        name = in.readString();
        parseId = in.readString();
        description = in.readString();
        acquisition = in.readString();
        groupId = in.readString();
        bmp = (Bitmap) in.readValue(Bitmap.class.getClassLoader());
        ing1Id = in.readString();
        ing2Id = in.readString();
        ing3Id = in.readString();
        ing4Id = in.readString();
        ing5Id = in.readString();
        amount1 = in.readInt();
        amount2 = in.readInt();
        amount3 = in.readInt();
        amount4 = in.readInt();
        amount5 = in.readInt();
        effects = in.createStringArray();
        tupe = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(parseId);
        dest.writeString(name);
        dest.writeString(parseId);
        dest.writeString(description);
        dest.writeString(acquisition);
        dest.writeString(groupId);
        dest.writeValue(bmp);
        dest.writeString(ing1Id);
        dest.writeString(ing2Id);
        dest.writeString(ing3Id);
        dest.writeString(ing4Id);
        dest.writeString(ing5Id);
        dest.writeInt(amount1);
        dest.writeInt(amount2);
        dest.writeInt(amount3);
        dest.writeInt(amount4);
        dest.writeInt(amount5);
        dest.writeStringArray(effects);
        dest.writeString(tupe);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<IngredientFromParse> CREATOR = new Parcelable.Creator<IngredientFromParse>() {
        @Override
        public IngredientFromParse createFromParcel(Parcel in) {
            return new IngredientFromParse(in);
        }

        @Override
        public IngredientFromParse[] newArray(int size) {
            return new IngredientFromParse[size];
        }
    };
}
