package by.krevm.bdbase.Adapters;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;


public class MovieTouchHelper extends ItemTouchHelper.SimpleCallback {
    private DishesListRVAdapter adapter;
    private RecyclerView rv;

    public MovieTouchHelper(DishesListRVAdapter adapter, RecyclerView rv) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.adapter = adapter;
        this.rv = rv;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        adapter.showSnackBar(rv, viewHolder.getAdapterPosition());
    }
}
