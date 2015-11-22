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

    protected IngredientFromParse(Parcel in) {
        parseId= in.readString();
        name = in.readString();
        parseId = in.readString();
        description = in.readString();
        acquisition = in.readString();
        groupId = in.readString();
        bmp = (Bitmap) in.readValue(Bitmap.class.getClassLoader());
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
