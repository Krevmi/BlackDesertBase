package by.krevm.blackdesertbase.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentManager;
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

public class IngredientsListRVAdapter extends RecyclerView.Adapter<IngredientsListRVAdapter.ViewHolder> {
    ArrayList<IngredientFromParse> ingredients;
    Context context;
    public ItemClickListener clickListener;
    public IngredientsListRVAdapter(ArrayList<IngredientFromParse> ingredients,Context context) {
        System.out.println("Adapter create");
        this.ingredients = new ArrayList<>(ingredients);
        System.out.println(this.ingredients.size());
        this.context=context;

    }
    public interface ItemClickListener {
        void onClick(View view, IngredientFromParse ing);
    }
    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView ingredientNameTextView;
        private ImageView img;
        public ViewHolder(View v) {
            super(v);
            ingredientNameTextView = (TextView) v.findViewById(R.id.nameIngredientTextView);
            img = (ImageView) v.findViewById(R.id.image_ingredient_item);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, ingredients.get(getPosition()));
        }
    }

    @Override
    public IngredientsListRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredient_list_item_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final IngredientsListRVAdapter.ViewHolder holder, int position) {
        holder.ingredientNameTextView.setText(ingredients.get(position).getName());
        System.out.println("onBindViewHolder" + ingredients.get(position).getName());

        if(ingredients.get(position).getImg()!=null) {
            ingredients.get(position).getImg().getDataInBackground(new GetDataCallback() {
                @Override
                public void done(byte[] bytes, ParseException e) {
                    Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    holder.img.setImageBitmap(bmp);
                }
            });

        }
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
