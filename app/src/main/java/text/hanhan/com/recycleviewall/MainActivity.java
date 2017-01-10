package text.hanhan.com.recycleviewall;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

    private RecyclerView recycleView;
    private ArrayList<String> list = new ArrayList<>();
    ;
    private SwipeRefreshLayout swiperefresh;
    private int index = 0;
    private int MAX_COUNT = 25;
    private RecycleViewAdapter adapter;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                 swiperefresh.setRefreshing(false);
            }

            if (adapter == null) {
                adapter = new RecycleViewAdapter(MainActivity.this, list);
                recycleView.setAdapter(adapter);
            } else {
                adapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycleView = (RecyclerView) findViewById(R.id.recycleView);
        swiperefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        //设置进度的颜色
        swiperefresh.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        //设置是否刷新---直接一进来就刷新
        swiperefresh.post(new Runnable() {
            @Override
            public void run() {
                //一上来先去做刷新的逻辑操作
                swiperefresh.setRefreshing(true);
                //请求数据
                refreshData();
            }


        });
        initData();
//        //设置滑动监听
//        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//              swiperefresh.setRefreshing(true);
//            }
//        });
        //创建一个条目触摸回调
        ItemTouchCallBack itemTouchCallBack = new ItemTouchCallBack(adapter);
        //设置给条目触摸的帮助类
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchCallBack);
        //设置关联recycleview
        itemTouchHelper.attachToRecyclerView(recycleView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(linearLayoutManager);
        //对recycleView添加一个滑动的监听
        recycleView.addOnScrollListener(new OnLoadMoreListener(linearLayoutManager) {
            @Override
            public void onloadMore() {
                onloadMoreData();
            }
        });
    }


    public void onloadMoreData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(3000);
                    swiperefresh.setRefreshing(false);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                index = index + MAX_COUNT;
                initData();
                //把数据发送给主线程
                handler.sendEmptyMessage(1);
            }
        }.start();
    }

    public void refreshData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(3000);
                    swiperefresh.setRefreshing(false);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                index = 0;
                list.clear();
                initData();
                //把数据发送给主线程
                handler.sendEmptyMessage(0);
            }
        }.start();
    }

    private void initData() {
        for (int i = index; i < index + MAX_COUNT; i++) {
            list.add("我是第" + i + "条数据");
        }
    }


}
