package text.hanhan.com.recycleviewall;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by 韩永光
 * on 2017/1/9 16:46.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<BaseHolder> implements MoveSwipeLitener {

    private final Context context;
    private final ArrayList<String> list;
    private final int TYPE_NORMAL = 0;
    private final int TYPE_LOADMORE = 1;

    public RecycleViewAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;
        BaseHolder holder = null;
        switch (viewType) {
            case TYPE_NORMAL:
                view = View.inflate(context, android.R.layout.simple_list_item_1, null);
                holder = new NormalHolder(view);
                break;
            case TYPE_LOADMORE:
                view = View.inflate(context, R.layout.seekbar, null);
                holder = new LoadMaoreHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        if(position<list.size()){

            NormalHolder normalHolder= (NormalHolder) holder;
            normalHolder.text1.setText(list.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return list.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == list.size()) {
            return TYPE_LOADMORE;
        }
        return TYPE_NORMAL;

    }

    //上下移动替换的方法
    public void onMove(int oldadapterPosition, int newadapterPosition) {
        //替换集合里的数据   调用Collections的方法
        Collections.swap(list, oldadapterPosition, newadapterPosition);
        //另一种替换的方法
//        String s1 = list.get(oldadapterPosition);
//        String s2 = list.get(newadapterPosition);
//        list.set(oldadapterPosition,s2);
//        list.set(newadapterPosition,s1);
        //刷新适配器
        this.notifyItemMoved(oldadapterPosition, newadapterPosition);
    }

    //左右移动删除的方法
    public void Swiped(int adapterPosition) {
        list.remove(adapterPosition);
        this.notifyItemRemoved(adapterPosition);
    }
}
