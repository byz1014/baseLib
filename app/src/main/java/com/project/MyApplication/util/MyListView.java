package com.project.MyApplication.util;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MyListView extends ListView implements AbsListView.OnScrollListener {
    private static final String TAG = "LazyListView";
    private OnScrollListener onScrollListener;
    //以前可见的item项
    private int oldVisibleItemCount = 0;

    private OnLazyLoadListener onLazyLoadListener;

    //记录Item的懒加载情况
    //比如（1，true）表示为position为1的item已经懒加载过了
    //（2，false) 表示postion为2的item还没有被懒加载
    private SparseArray<Boolean> itemsNow;

    public MyListView(Context context) {
        super(context);
        init();
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //调用父类的设置滑动监听事件
        super.setOnScrollListener(this);

        itemsNow = new SparseArray<>();

    }

    /**
     * 覆盖setOnScrollListener方法截取回调
     *
     * @param
     */
    @Override
    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }


    /**
     * 当ListView出现滑动时会回调这个方法
     * 但ListView首次显示时并没有滑动，所以还需要通过onScroll方法判断一下ListView的首次展示
     * scrollState 表示滑动的位置
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //设置不需要加载的item设置为未加载
        updateShouldLoadPos();
        //判断是否有过滑动
        if (scrollState == 0) {
            //此方法用法判断已经加载了多少项。但没有滑动式显示默认加载。
            caculateWhichShouldLoad(this.getFirstVisiblePosition(), this.getLastVisiblePosition());
        }
        //将回调传递下去，这样不影响在外部调用监听方法
        if (this.onScrollListener != null) {
            this.onScrollListener.onScrollStateChanged(view, scrollState);

        }

    }

    /**
     * 当ListView首次加载时，这个方法会调用多次，同时这个方法第一次被
     * 调用时visibleItemCount的值为0，所以可以跟据这个特性判断listView是不是首次显示
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //visibleItemCount判断是不是第一次调用  oldVisibleItemCount  oldVisibleItemCount
        if (visibleItemCount != oldVisibleItemCount && oldVisibleItemCount != -1) {
            //因为第一次加载，所以回调此方法，保证不见的不加载
            updateShouldLoadPos();
            //设置应该加载的项为第一个item以及最后最后一个item为第一个加上最后一个-1
            caculateWhichShouldLoad(firstVisibleItem, firstVisibleItem + visibleItemCount - 1);
            //并且同时把以前可见的最后一个item（现在下拉为第一个可见的item）设置为-1，那么现在第一个firstVisibleItem有重新为0了。
            oldVisibleItemCount = -1;
        }
        if (oldVisibleItemCount == -1) {
            oldVisibleItemCount = visibleItemCount;
        }
        //如何他滑动了，着将回调传递下去，这样不影响在外部调用监听方法
        if (this.onScrollListener != null) {
            this.onScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }

    }

    //此方法用法判断已经加载了多少项。
    private void caculateWhichShouldLoad(int firstPos, int finalPos) {
        List<Integer> itemsPos = new ArrayList<>();
        for (int i = firstPos; i <= finalPos; i++) {
            if (!itemsNow.get(i, false)) {
                itemsPos.add(i);
                itemsNow.put(i, true);
            }
        }

        Log.d(TAG, "需要加载的position：" + itemsPos.toString());

        if (onLazyLoadListener != null) {
            onLazyLoadListener.shouldLoad(itemsPos);
        }

    }


    /**
     * 更新需要重新加载的项
     * 将不再显示范围内的项设为未加载
     */
    private void updateShouldLoadPos() {
        int fristpos = this.getFirstVisiblePosition();
        int finalPos = this.getLastVisiblePosition();
        for (int i = 0; i < fristpos; i++) {
            itemsNow.put(i, false);
        }
        for (int i = finalPos + 1; i < this.getCount(); i++) {
            itemsNow.put(i, false);
        }

    }

    /**
     * 不加载回调接口
     * 在ListView滑动停止后回掉
     */
    public interface OnLazyLoadListener {
        /**
         * 应该被加载细节的项
         *
         * @param itemsPos item的位置集合
         */
        void shouldLoad(List<Integer> itemsPos);
    }

    public void setOnLazyLoadListener(OnLazyLoadListener onLazyLoadListener) {
        this.onLazyLoadListener = onLazyLoadListener;
    }

}