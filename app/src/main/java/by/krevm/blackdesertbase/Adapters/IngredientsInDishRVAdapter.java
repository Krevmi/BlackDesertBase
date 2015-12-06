package by.krevm.blackdesertbase.Adapters;

import android.animation.ValueAnimator;
import android.renderscript.Sampler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

import by.krevm.blackdesertbase.IngredientFromParse;
import by.krevm.blackdesertbase.R;

public class IngredientsInDishRVAdapter extends RecyclerView.Adapter<IngredientsInDishRVAdapter.ViewHolder> {
    private ArrayList<IngredientFromParse> ingredients = new ArrayList<>();
    private HashMap<String, Integer> amount = new HashMap<>();
    public DishesListRVAdapter.ItemClickListener clickListener;
    int amountDish=1;

    public IngredientsInDishRVAdapter(ArrayList<IngredientFromParse> ingredients, HashMap<String, Integer> amount) {
        this.ingredients.clear();
        this.amount.clear();
        this.ingredients.addAll(ingredients);
        this.amount.putAll(amount);

    }
    public void setClickListener(DishesListRVAdapter.ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
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
        int amountIng = amount.get(ingredients.get(position).getParseId())*amountDish;
        holder.amountTextView.setText(String.valueOf(amountIng));
        holder.nameTextView.setText(ingredients.get(position).getName());
    }
    public void setAmountDish(int amountDish){
        this.amountDish=amountDish;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameTextView;
        TextView amountTextView;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            nameTextView = (TextView) view.findViewById(R.id.nameIngredientInDishTextView);
            amountTextView = (TextView) view.findViewById(R.id.amountTextView);
            imageView = (ImageView) view.findViewById(R.id.image_dish_item);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, ingredients.get(getPosition()));
        }
    }
}
