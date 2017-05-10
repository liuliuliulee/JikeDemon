package com.shabiao.joy.jikedemon;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


/**
 * Created by joy on 2017/2/3.
 */

public abstract class RecyclerViewScrollerListener extends RecyclerView.OnScrollListener {
    LinearLayoutManager linearLayoutManager;

    //当前页，从0开始
    private int currentPage = 1;

    //已经加载出来的Item的数量
    private int totalItemCount;

    //主要用来存储上一个totalItemCount
    private int previousTotal = 0;

    int lastVisibleItemPosition;

//    //在屏幕上可见的item数量
    private int visibleItemCount;
//
//    //在屏幕可见的Item中的第一个
    private int firstVisibleItem;

    //是否正在上拉数据
    private boolean loading = true;

    public RecyclerViewScrollerListener(LinearLayoutManager layoutManager) {
        this.linearLayoutManager = layoutManager;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
//        Log.e("aaa", "newState" + newState);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();

//        Log.e("on srcool", "dx:" + dx + ",dy:" + dy);

        //可见item数量
        visibleItemCount = recyclerView.getChildCount();
        //已经加载出来的item数量
        totalItemCount = linearLayoutManager.getItemCount();
        //在屏幕可见的Item中的第一个
        firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

        Log.e("wnwn", "firstVisibleItem: " + firstVisibleItem);
        Log.e("wnwn", "lastVisibleItem: " + lastVisibleItemPosition);
        Log.e("wnwn", "totalPageCount:" + totalItemCount);
        Log.e("wnwn", "visibleItemCount:" + visibleItemCount);

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                //此时已经loading完毕
                previousTotal = totalItemCount;
            }
        }

//        if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem) {
//            currentPage++;
//            onLoadMore(currentPage);
//            loading = true;
//        }
        if (!loading && totalItemCount - 1 <= lastVisibleItemPosition) {
            currentPage++;
            onLoadMore(currentPage);
            loading = true;
        }
    }

    /**
     * 提供一个抽象方法
     */
    public abstract void onLoadMore(int currentPage);
}
