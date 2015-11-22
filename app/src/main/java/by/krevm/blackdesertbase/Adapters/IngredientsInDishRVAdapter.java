package by.krevm.blackdesertbase.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ListIterator;

import by.krevm.blackdesertbase.R;

public class IngredientsInDishRVAdapter extends RecyclerView.Adapter<IngredientsInDishRVAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_item_in_dish,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameTextView;
        EditText amountTextView;
        ImageView imageView;
        public ViewHolder(View view) {
            super(view);
            nameTextView=(TextView)view.findViewById(R.id.nameIngredientInDishTextView);
            amountTextView=(EditText)view.findViewById(R.id.amountEditText);
            imageView=(ImageView)view.findViewById(R.id.image_dish_item);

        }
    }
}
