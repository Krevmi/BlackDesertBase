package by.krevm.bdbase.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;

import java.util.ArrayList;

import by.krevm.bdbase.IngredientFromParse;
import by.krevm.bdbase.R;

public class DishesListRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<IngredientFromParse> ingredients;
    public ArrayList<String> itemToDeleteFromFavorites = new ArrayList<>();
    public ItemClickListener clickListener;
    private static final int SINGLELINE = 1;
    private static final int TWOLINE = 2;
    private Context context;

    public DishesListRVAdapter(ArrayList<IngredientFromParse> ingredients, Context context) {
        this.ingredients = new ArrayList<>(ingredients);
        this.context = context;
    }

    public interface ItemClickListener {
        void onClick(View view, IngredientFromParse ing);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == SINGLELINE) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.dish_list_item_layout, parent, false);
            ViewHolderSingle vh = new ViewHolderSingle(v);
            return vh;
        } else if (viewType == TWOLINE) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.two_line_item_list, parent, false);
            ViewHolderTwo vh = new ViewHolderTwo(v);
            return vh;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderSingle) {
            final ViewHolderSingle holderSingle = (ViewHolderSingle) holder;
            holderSingle.dishNameTextView.setText(ingredients.get(position).getName());
            holderSingle.dishNameTextView.setTextColor(ingredients.get(position).getGrade());
            ingredients.get(position).getImg().getDataInBackground(new GetDataCallback() {
                @Override
                public void done(byte[] bytes, ParseException e) {
                    Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    holderSingle.img.setImageBitmap(bmp);
                }
            });
        } else if (holder instanceof ViewHolderTwo) {
            final ViewHolderTwo holderTwo = (ViewHolderTwo) holder;
            holderTwo.dishNameTextView.setText(ingredients.get(position).getName());
            holderTwo.dishNameTextView.setTextColor(ingredients.get(position).getGrade());
            ingredients.get(position).getImg().getDataInBackground(new GetDataCallback() {
                @Override
                public void done(byte[] bytes, ParseException e) {
                    Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    holderTwo.img.setImageBitmap(bmp);
                }
            });
            String[] effects = ingredients.get(position).getEffects();
            holderTwo.effectTextView.setText(effects[0]);
        }
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public void showSnackBar(View view, final int position) {
        final IngredientFromParse ing = ingredients.get(position);
        Snackbar snackbar = Snackbar.make(view, "Удалено из избранного", Snackbar.LENGTH_LONG);
        snackbar.setAction("Отмена", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingredients.add(position, ing);
                itemToDeleteFromFavorites.remove(ing.getParseId());
                notifyItemInserted(position);
            }
        });
        snackbar.show();
        itemToDeleteFromFavorites.add(ing.getParseId());
        ingredients.remove(position);
        notifyItemRemoved(position);

    }

    @Override
    public int getItemViewType(int position) {
        if (ingredients.get(position).getTupe() != null) {
            if (ingredients.get(position).getTupe().equals("k") || ingredients.get(position).getTupe().equals("z")) {
                return TWOLINE;
            }
        }
        return SINGLELINE;
    }

    public void setFilteredList(ArrayList<IngredientFromParse> ingredients) {
        this.ingredients = new ArrayList<>(ingredients);
        notifyDataSetChanged();
    }

    public class ViewHolderSingle extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView dishNameTextView;
        public ImageView img;

        public ViewHolderSingle(View v) {
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

    public class ViewHolderTwo extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView dishNameTextView, effectTextView;
        public ImageView img;

        public ViewHolderTwo(View v) {
            super(v);
            dishNameTextView = (TextView) v.findViewById(R.id.nameIngredientInDishTextView);
            img = (ImageView) v.findViewById(R.id.image_dish_item);
            effectTextView = (TextView) v.findViewById(R.id.effectTextView);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, ingredients.get(getPosition()));
        }
    }
}
