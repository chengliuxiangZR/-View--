package com.example.asus.circleview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CircleView extends View {
    private int mMinWidth=256;
    private int mMinHeight=256;

    private int mColor= Color.RED;
    private Paint mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    public CircleView(Context context){
        super(context);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //自定义属性
        TypedArray a=context.obtainStyledAttributes(attrs,R.styleable.CircleView);
        mColor=a.getColor(R.styleable.CircleView_circle_color,Color.RED);
        a.recycle();
        init();
    }
    private void init(){
        mPaint.setColor(mColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        支持padding
        final int paddingLeft=getPaddingLeft();
        final int paddingRight=getPaddingRight();
        final int paddingTop=getPaddingTop();
        final int paddingBottom=getPaddingBottom();

//        int width=getWidth();
////        int height=getHeight();
        int width=getWidth()-paddingLeft-paddingRight;
        int height=getHeight()-paddingBottom-paddingTop;

        int radius=Math.min(width,height)/2;
//        canvas.drawCircle(width/2,height/2,radius,mPaint);
        canvas.drawCircle(paddingLeft+width/2,paddingTop+height/2,radius,mPaint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //支持wrap_content
        int widthSpecMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize=MeasureSpec.getSize(heightMeasureSpec);
        if(widthSpecMode==MeasureSpec.AT_MOST&&heightMeasureSpec==MeasureSpec.AT_MOST){
            setMeasuredDimension(mMinWidth,mMinHeight);
        }else if(widthSpecMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(mMinWidth,heightSpecSize);
        }else if(heightSpecMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSpecSize,mMinHeight);
        }
    }
}
