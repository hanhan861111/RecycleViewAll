package text.hanhan.com.recycleviewall;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 韩永光
 * on 2017/1/10 20:50.
 */
public abstract class BaseHolder<T> extends RecyclerView.ViewHolder {
    public BaseHolder(View itemView) {
        super(itemView);
    }
        //定义一个抽象方法  获取数据使用
    public abstract void getHolder(Context context, T t);

}
