package by.krevm.bdbase.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import by.krevm.bdbase.Fragments.DishFragment;
import by.krevm.bdbase.IngredientFromParse;
import by.krevm.bdbase.R;

public class IngredientsInDishRVAdapter extends RecyclerView.Adapter<IngredientsInDishRVAdapter.ViewHolder> {
    private ArrayList<IngredientFromParse> ingredients = new ArrayList<>();
    private HashMap<String, Integer> amount = new HashMap<>();
    public DishesListRVAdapter.ItemClickListener clickListener;
    int amountDish = 1;
    int amountIngInRecipe = 1;
    DishFragment fragment;

    public IngredientsInDishRVAdapter(ArrayList<IngredientFromParse> ingredients, HashMap<String, Integer> amount, DishFragment fragment) {
        this.ingredients.clear();
        this.amount.clear();
        this.ingredients.addAll(ingredients);
        this.amount.putAll(amount);
        this.fragment = fragment;

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
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.imageView.setImageBitmap(ingredients.get(position).getBmp());
        int amountIng = amount.get(ingredients.get(position).getParseId()) * amountDish;
        amountIngInRecipe = amount.get(ingredients.get(position).getParseId());
        holder.amountTextView.setText(String.valueOf(amountIng));
        holder.amountTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                        actionId == EditorInfo.IME_ACTION_DONE ||
                        event.getAction() == KeyEvent.ACTION_DOWN &&
                                event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {

                    int amount = Integer.parseInt(v.getText().toString()) / amountIngInRecipe;

                    return true; // consume.

                }
                return false; // pass on to other listeners.
            }
        });
        holder.nameTextView.setText(ingredients.get(position).getName());
    }

    public void setAmountDish(int amountDish) {
        this.amountDish = amountDish;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameTextView;
        EditText amountTextView;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            nameTextView = (TextView) view.findViewById(R.id.nameIngredientInDishTextView);
            amountTextView = (EditText) view.findViewById(R.id.amountTextView);
            imageView = (ImageView) view.findViewById(R.id.image_dish_item);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, ingredients.get(getPosition()));
        }
    }
}
