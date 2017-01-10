package text.hanhan.com.recycleviewall;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by 韩永光
 * on 2017/1/10 10:05.
 */
public class ItemTouchCallBack extends ItemTouchHelper.Callback {
    private final RecycleViewAdapter adapter;

    public ItemTouchCallBack(RecycleViewAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //设置上下移动的标记
        int dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        //设置左右移动的标记
        int swipTag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        //制作两种标记
        int i = ItemTouchHelper.Callback.makeMovementFlags(dragFlag, swipTag);
        return i;
    }

    /**
     * @param recyclerView
     * @param viewHolder   原来的viewholder
     * @param target       拖动后的viewholder
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //拖动时的position
        int oldadapterPosition = viewHolder.getAdapterPosition();
        //拖动后的position
        int newadapterPosition = target.getAdapterPosition();
        //交换位置
        adapter.onMove(oldadapterPosition, newadapterPosition);
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        //删除条目
        int adapterPosition = viewHolder.getAdapterPosition();
        adapter.Swiped(adapterPosition);
    }
}
