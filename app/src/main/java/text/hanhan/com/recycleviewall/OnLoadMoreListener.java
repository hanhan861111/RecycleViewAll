package text.hanhan.com.recycleviewall;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by 韩永光
 * on 2017/1/10 19:04.
 */
public abstract class OnLoadMoreListener extends RecyclerView.OnScrollListener {

    private final LinearLayoutManager linearLayoutManager;
    private int visibleItemCount;
    //是否正在加载
    private boolean isloading;
    //条目总个数
    private   int preToutleIten;

    public OnLoadMoreListener(LinearLayoutManager linearLayoutManager) {
        super();
        this.linearLayoutManager =linearLayoutManager;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if(recyclerView.SCROLL_STATE_IDLE==newState){
            //获取当前可见条目的数量
            visibleItemCount = recyclerView.getChildCount();
            //获得总条目个数
            int itemCount = linearLayoutManager.getItemCount();
            //获取当前页面最后一个可见的条目
            int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
            if(itemCount!=preToutleIten){
                isloading=false;
                //上一次的总个数等于当前个数
                preToutleIten=itemCount;
            }
            if(!isloading){
                if(itemCount-1==lastVisibleItemPosition){
                    isloading=!isloading;
                    //加载更多
                    onloadMore();

                }
//                isloading=false;

            }

        }
    }



    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
    }
    //抽象方法
    public abstract void onloadMore();
}
