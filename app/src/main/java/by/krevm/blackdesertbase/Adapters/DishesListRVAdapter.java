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

public class DishesListRVAdapter extends RecyclerView.Adapter<DishesListRVAdapter.ViewHolder> {
    ArrayList<IngredientFromParse> ingredients;
    public DishesListRVAdapter.ItemClickListener clickListener;
    public DishesListRVAdapter(ArrayList<IngredientFromParse> ingredients) {
        System.out.println("Adapter create");
        this.ingredients = new ArrayList<>(ingredients);
        System.out.println(this.ingredients.size());
    }
    public interface ItemClickListener {
        void onClick(View view, IngredientFromParse ing);
    }
    public void setClickListener(DishesListRVAdapter.ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView dishNameTextView;
        public ImageView img;

        public ViewHolder(View v) {
            super(v);
            dishNameTextView = (TextView) v.findViewById(R.id.nameIngredientInDishTextView);

            img = (ImageView) v.findViewById(R.id.image_dish_item);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, ingredients.get(getPosition()));
        }
    }

    @Override
    public DishesListRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dish_list_item_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final DishesListRVAdapter.ViewHolder holder, int position) {
        holder.dishNameTextView.setText(ingredients.get(position).getName());
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
    public void setFilteredList(ArrayList<IngredientFromParse> ingredients){
        this.ingredients=new ArrayList<>(ingredients);
        notifyDataSetChanged();
    }
}