package by.krevm.blackdesertbase.Adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;

import java.util.ArrayList;

import by.krevm.blackdesertbase.IngredientFromParse;
import by.krevm.blackdesertbase.R;

/**
 * Created by KrEvM on 09.11.2015.
 */
public class DishesListRVAdapter extends RecyclerView.Adapter<DishesListRVAdapter.ViewHolder> {
    ArrayList<IngredientFromParse> ingredients;

    public DishesListRVAdapter(ArrayList<IngredientFromParse> ingredients) {
        System.out.println("Adapter create");
        this.ingredients = new ArrayList<>(ingredients);
        System.out.println(this.ingredients.size());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView ingredientNameTextView;
        public ImageView img;

        public ViewHolder(View v) {
            super(v);
            ingredientNameTextView = (TextView) v.findViewById(R.id.nameIngredientTextView);

            img = (ImageView) v.findViewById(R.id.image_ingredient_item);
        }
    }

    @Override
    public DishesListRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredient_list_item_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final DishesListRVAdapter.ViewHolder holder, int position) {
        holder.ingredientNameTextView.setText(ingredients.get(position).getName());
        System.out.println("onBindViewHolder" +ingredients.get(position).getName());
        ingredients.get(position).getImg().getDataInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] bytes, ParseException e) {
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                holder.img.setImageBitmap(bmp);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }
}