package by.krevm.blackdesertbase.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

import by.krevm.blackdesertbase.IngredientFromParse;
import by.krevm.blackdesertbase.R;

public class IngredientsInDishRVAdapter extends RecyclerView.Adapter<IngredientsInDishRVAdapter.ViewHolder> {
    ArrayList<IngredientFromParse> ingredients= new ArrayList<>();
    HashMap <String,Integer> amount= new HashMap<>();

    public IngredientsInDishRVAdapter(ArrayList<IngredientFromParse> ingredients,HashMap<String,Integer> amount) {
        System.out.println("Construktor called "+this.ingredients.size());
       this.ingredients.clear();
        this.amount.clear();
        this.ingredients.addAll(ingredients);
        this.amount.putAll(amount);
        System.out.println("Construktor called2 " + this.ingredients.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_item_in_dish, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.imageView.setImageBitmap(ingredients.get(position).getBmp());
        holder.amountTextView.setText(amount.get(ingredients.get(position).getParseId()).toString());
        holder.nameTextView.setText(ingredients.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView amountTextView;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            nameTextView = (TextView) view.findViewById(R.id.nameIngredientInDishTextView);
            amountTextView = (TextView) view.findViewById(R.id.amountTextView);
            imageView = (ImageView) view.findViewById(R.id.image_dish_item);
        }
    }
}
