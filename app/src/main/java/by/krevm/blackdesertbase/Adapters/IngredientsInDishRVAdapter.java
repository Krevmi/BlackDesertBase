package by.krevm.blackdesertbase.Adapters;

import android.animation.ValueAnimator;
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

public class IngredientsInDishRVAdapter extends RecyclerView.Adapter<IngredientsInDishRVAdapter.ViewHolder>  {
    ArrayList<IngredientFromParse> ingredients= new ArrayList<>();
    HashMap <String,Integer> amount= new HashMap<>();

    public IngredientsInDishRVAdapter(ArrayList<IngredientFromParse> ingredients,HashMap<String,Integer> amount) {
       this.ingredients.clear();
        this.amount.clear();
        this.ingredients.addAll(ingredients);
        this.amount.putAll(amount);

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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView nameTextView;
        TextView amountTextView;
        ImageView imageView;
        ImageButton imageButton;
        private int mOriginalHeight = 0;
        private boolean mIsViewExpanded = false;

        public ViewHolder(View view) {
            super(view);
            nameTextView = (TextView) view.findViewById(R.id.nameIngredientInDishTextView);
            amountTextView = (TextView) view.findViewById(R.id.amountTextView);
            imageView = (ImageView) view.findViewById(R.id.image_dish_item);
            imageButton = (ImageButton)view.findViewById(R.id.imageButton);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(final View v) {
            if (mOriginalHeight == 0) {
                mOriginalHeight = v.getHeight();
            }
            ValueAnimator valueAnimator;
            if (!mIsViewExpanded) {
                mIsViewExpanded = true;
                valueAnimator = ValueAnimator.ofInt(mOriginalHeight, mOriginalHeight + (int) (mOriginalHeight * 1.5));
            } else {
                mIsViewExpanded = false;
                valueAnimator = ValueAnimator.ofInt(mOriginalHeight + (int) (mOriginalHeight * 1.5), mOriginalHeight);
            }
            valueAnimator.setDuration(300);
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator animation) {
                    Integer value = (Integer) animation.getAnimatedValue();
                    v.getLayoutParams().height = value.intValue();
                    v.requestLayout();
                }
            });
            valueAnimator.start();
        }
    }
}
