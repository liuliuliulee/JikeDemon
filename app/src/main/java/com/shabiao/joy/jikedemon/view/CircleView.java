package com.shabiao.joy.jikedemon.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.shabiao.joy.jikedemon.R;

/**
 * Created by joy on 2017/2/8.
 */

public class CircleView extends View {

    private int mColor = Color.RED;
    private String mText = "";
    private int mTextSize = 30;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//此flag消除走样、滤波

    private int mDefaultWidth = 100, mDefaultHeight = 100;

    public CircleView(Context context) {
        this(context,null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.e("aaa","aaaa");
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        int count = typedArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            Log.e("aaaa",i+"");
            int attr = typedArray.getIndex(i);
            switch (attr){
                case R.styleable.CircleView_circleColor:
                    mColor = typedArray.getColor(attr, Color.RED);
                    break;
                case R.styleable.CircleView_circleText:
                    mText = typedArray.getString(attr);
                    Log.e("aaa",mText);
                    break;
                case R.styleable.CircleView_circleTextSize:
                    //参数2：默认大小，把16px数值转为16sp
                    mTextSize = typedArray.getDimensionPixelSize(attr,(int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 24, getResources().getDisplayMetrics()));
                    break;
            }
        }
        typedArray.recycle();
        init();
    }

    public void init() {
        mPaint.setColor(mColor);
        mPaint.setTextSize(mTextSize);
        Log.e("aaaa","init");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //需要考虑如果warp_content时，要给默认值，不然就等同于使用match_parent
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mDefaultWidth, mDefaultHeight);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mDefaultWidth, heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, mDefaultHeight);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //需要考虑到padding值
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;
        int radius = Math.min(width, height) / 2;//半径
        //参数1：x坐标，参数2：y坐标，参数3：圆的半径，参数4：颜料/绘画
        canvas.drawCircle(paddingLeft + width / 2, paddingTop + height / 2, radius, mPaint);
        Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
        int baseline = (getMeasuredHeight() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
        // 下面这行是实现水平居中，drawText对应改为传入targetRect.centerX()
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(mTextSize);
        Log.e("===>","ondraw");
        canvas.drawText(mText,width/2,baseline,mPaint);

    }
}
