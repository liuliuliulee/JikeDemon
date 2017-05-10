package com.shabiao.joy.jikedemon;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView recyclerView;
    private ContentAdapter contentAdapter;
    private SwipeRefreshLayout refreshLayout;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //此两句相当于在主题中设置 <!--<item name="android:windowTranslucentStatus">true</item>--> <!--<item name="android:windowTranslucentNavigation">true</item>-->


//        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//
//        } else {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        setContentView(R.layout.activity_main_four);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.inflateMenu(R.menu.toolbar);
        //设置点击监听
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.setting:
                        Log.e("==>", "setting");
                        break;
                    case R.id.about:
                        Log.e("==>", "about");
                        break;
                    case R.id.search:
                        Log.e("==>", "search");
                        break;
                    case R.id.notification:
                        Log.e("==>", "notification");
                        break;
                }
                return false;
            }
        });

//        mToolbar.setNavigationIcon(R.mipmap.ic_drawer_home);//设置导航栏图标
//        mToolbar.setLogo(R.mipmap.ic_launcher);//设置app logo

//        mToolbar.setTitle("Title");//设置主标题
//        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));//设置主标题颜色
//        mToolbar.setTitleTextAppearance(this, R.style.Theme_ToolBar_Base_Title);//修改主标题的外观，包括文字颜色，文字大小等

//        mToolbar.setSubtitle("Subtitle");//设置子标题
//        mToolbar.setSubtitleTextColor(getResources().getColor(android.R.color.darker_gray));//设置子标题颜色
//        mToolbar.setSubtitleTextAppearance(this, R.style.Theme_ToolBar_Base_Subtitle);//设置子标题的外观，包括文字颜色，文字大小等


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().setStatusBarColor(0x000000);

//            getWindow()
//                    .setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        } else {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            CollapsingToolbarLayout.LayoutParams layoutParam = new CollapsingToolbarLayout.LayoutParams(mToolbar.getLayoutParams());
//            layoutParam.setMargins(0, getStatusBarHeight(this), 0, 0);
//            mToolbar.setLayoutParams(layoutParam);
//        }
//        StatusBarUtil.setColorForDrawerLayout(this,(DrawerLayout)findViewById(R.id.drawer_layout), Color.TRANSPARENT);
//        StatusBarUtil.setTranslucentForDrawerLayout(this,(DrawerLayout)findViewById(R.id.drawer_layout),0);
//        StatusBarView statusBarView = createStatusBarView(this, Color.TRANSPARENT, 120);
//        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);
//        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
//        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
//        collapsingToolbarLayout.addView(statusBarView,0);


//        TypedValue typedValue = new TypedValue();
//        this.getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true);
//        WindowManager wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
//        DisplayMetrics outMetrics = new DisplayMetrics();
//        wm.getDefaultDisplay().getMetrics(outMetrics);
//        float dimension = typedValue.getDimension(outMetrics);


//
////        mToolbar.setPadding(0,100,0,0);
//        layoutParam.setMargins(0,100,0,0);
//        mToolbar.setLayoutParams(layoutParam);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
//        setSupportActionBar(mToolbar);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        contentAdapter = new ContentAdapter(this, new ArrayList<String>());
        recyclerView.setAdapter(contentAdapter);
        recyclerView.addOnScrollListener(new RecyclerViewScrollerListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                Log.e("aaa", "load more");
                ArrayList<String> list = contentAdapter.getAdapterList();
                for (int i = 0; i < 10; i++) {
                    list.add("");
                }
                contentAdapter.notifyDataSetChanged();

            }
        });
        ArrayList<String> list = contentAdapter.getAdapterList();
        for (int i = 0; i < 5; i++) {
            list.add("");
        }
        contentAdapter.notifyDataSetChanged();
        refreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary), 0x55654321, 0x000fff);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {


                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                refreshLayout.setRefreshing(false);
                            }
                        });
                    }
                }).start();
            }
        });


        //初始化抽屉
        final NavigationView navigationView = (NavigationView) findViewById(R.id.right);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.about:
                            Log.e("==>", "about");
                            break;
                        case R.id.setting:
                            Log.e("==>", "setting");
                            break;
                        case R.id.store:
                            Log.e("==>", "store");
                            break;
                    }
                    return false;
                }
            });
            View headerLayout = navigationView.inflateHeaderView(R.layout.drawer_head);
            LinearLayout headerBackground = (LinearLayout) headerLayout.findViewById(R.id.drawer_head_background);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, mToolbar, R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close) {
                @Override
                public void onDrawerSlide(View drawerView, float slideOffset) {
                    super.onDrawerSlide(drawerView, slideOffset);
//                    navigationView.setAlpha(slideOffset);
                    Log.e("===>", slideOffset + "");
                }

                @Override
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                    Log.e("===>", "drawer open");
                }

                @Override
                public void onDrawerClosed(View drawerView) {
                    super.onDrawerClosed(drawerView);
                    Log.e("===>", "drawer close");
                }
            };
            drawer.addDrawerListener(toggle);
            toggle.syncState();
        }

//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);//设置toolbar后退键

    }


    //初始化actionbar的layout
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }


    //监听actionbar按钮被点击
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    /**
     * 获取状态栏高度
     *
     * @param context context
     * @return 状态栏高度
     */
    private static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }


}
