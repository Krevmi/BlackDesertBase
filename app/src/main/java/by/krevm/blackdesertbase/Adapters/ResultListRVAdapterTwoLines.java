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
 * Created by Krevm on 19.12.2015.
 */
public class ResultListRVAdapterTwoLines extends RecyclerView.Adapter<ResultListRVAdapterTwoLines.ViewHolder> {
    ArrayList<IngredientFromParse> ingredients;
    public ItemClickListener clickListener;

    public ResultListRVAdapterTwoLines(ArrayList<IngredientFromParse> ingredients) {
        this.ingredients = new ArrayList<>(ingredients);
    }
    public interface ItemClickListener {
        void onClick(View view, IngredientFromParse ing);
    }
    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView dishNameTextView,effectTextView;
        public ImageView img;

        public ViewHolder(View v) {
            super(v);
            dishNameTextView = (TextView) v.findViewById(R.id.nameIngredientInDishTextView);
            effectTextView = (TextView)v.findViewById(R.id.effectTextView);
            img = (ImageView) v.findViewById(R.id.image_dish_item);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, ingredients.get(getPosition()));
        }
    }

    @Override
    public ResultListRVAdapterTwoLines.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.two_line_item_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ResultListRVAdapterTwoLines.ViewHolder holder, int position) {
        holder.dishNameTextView.setText(ingredients.get(position).getName());
        String[] effect =ingredients.get(position).getEffects();
        holder.effectTextView.setText(effect[0]);
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
