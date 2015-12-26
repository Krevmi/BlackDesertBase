package by.krevm.bdbase.Adapters;

import android.renderscript.Sampler;
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

import by.krevm.bdbase.IngredientFromParse;
import by.krevm.bdbase.R;


public class IngredientsInDishWithHeaderRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private ArrayList<IngredientFromParse> ingredients = new ArrayList<>();
    private HashMap<String, Integer> amount = new HashMap<>();
    private int dishAmount=1;
    public DishesListRVAdapter.ItemClickListener clickListener;

    public IngredientsInDishWithHeaderRVAdapter(ArrayList<IngredientFromParse> ingredients, HashMap<String, Integer> amount) {
        this.ingredients = ingredients;
        this.amount = amount;
    }
    public void setClickListener(DishesListRVAdapter.ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_HEADER){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_view_holder_in_dish_rv, parent, false);
            return new HeaderViewHolder(view);
        }else if (viewType==TYPE_ITEM){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_item_in_dish, parent, false);
            return new IngredientViewHolder(view);
        }
        return null;
    }
    private IngredientFromParse getItem (int position) {
        return ingredients.get (position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof HeaderViewHolder){
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
            headerHolder.amountDishEditText.setText(String.valueOf(dishAmount));
            headerHolder.amountDishEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                    if(actionId == EditorInfo.IME_ACTION_DONE){
                        System.out.println("IME_ACTION_DONE");
                        if(v.getText().length()>0) {
                          int dishAmount = Integer.parseInt(v.getText().toString());
                            setDishAmount(dishAmount);
                        }else {
                            setDishAmount(1);
                        }
                        return true;
                    }
                    return false;
                }
            });

        }else if(holder instanceof IngredientViewHolder){
            IngredientViewHolder ingredientViewHolder = (IngredientViewHolder)holder;
            ingredientViewHolder.imageView.setImageBitmap(ingredients.get(position-1).getBmp());
            ingredientViewHolder.nameTextView.setText(ingredients.get(position-1).getName());
            ingredientViewHolder.nameTextView.setTextColor(ingredients.get(position-1).getGrade());
            int amountIng = amount.get(ingredients.get(position-1).getParseId())*dishAmount;
            ingredientViewHolder.amountIngTextView.setText(String.valueOf(amountIng));
            ingredientViewHolder.amountIngTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if(actionId == EditorInfo.IME_ACTION_DONE){
                        if(v.getText().length()>0) {
                            System.out.println("IME_ACTION_DONE");
                            int ingAmount = Integer.parseInt(v.getText().toString());
                            int amountIngInRecipe = amount.get(ingredients.get(position - 1).getParseId());
                            int dishAmount = ingAmount / amountIngInRecipe;
                            setDishAmount(dishAmount);
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
    }
    private void setDishAmount(int dishAmount){
        if(dishAmount>0){
            this.dishAmount=dishAmount;
            notifyDataSetChanged();
            System.out.println("setDishAmount "+ dishAmount);
        }else {
            this.dishAmount=1;
            notifyDataSetChanged();
        }
    }
    @Override
    public int getItemViewType (int position) {
        if(isPositionHeader (position)) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    private boolean isPositionHeader (int position) {
        return position == 0;
    }

    @Override
    public int getItemCount() {
        return ingredients.size()+1;
    }

    class IngredientViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView nameTextView;
        EditText amountIngTextView;
        ImageView imageView;
        public IngredientViewHolder(View view) {
            super(view);
            nameTextView = (TextView) view.findViewById(R.id.nameIngredientInDishTextView);
            amountIngTextView = (EditText) view.findViewById(R.id.amountTextView);
            imageView = (ImageView) view.findViewById(R.id.image_dish_item);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, ingredients.get(getPosition()));
        }
    }
    class HeaderViewHolder extends RecyclerView.ViewHolder{
        EditText amountDishEditText;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            amountDishEditText = (EditText)itemView.findViewById(R.id.amountEdit);
        }
    }
}
