package com.github.xiaofeidev.shadow.shadow;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

import com.github.xiaofeidev.shadow.round.RoundStatus;

/**
 * @author 黎曼
 * @date 2020/7/18
 */
public class ShadowHelper implements ShadowStatus, ShadowDraw {
    //宿主 View
    private View mHostView;
    //圆角信息都在这个对象里面
    private RoundStatus mRoundStatus;
    //阴影信息的数据
    private ShadowData mShadowData;
    //////////////////////////////////////阴影相关属性
    //圆角矩形的矩形
    private RectF mRectF;
    //圆角矩形存在这个路径里
    private Path mPath;
    //阴影画笔
    private Paint mPaintShadow;

    //宿主 View 的初始 measure 宽度
    private int mHostOriginWidth = 0;
    //宿主 View 的初始 measure 高度
    private int mHostOriginHeight = 0;

    //View 是否已测量过
    private boolean isMeasure = false;

    public ShadowHelper(View mHostView, RoundStatus roundStatus, ShadowData shadowData) {
        this.mHostView = mHostView;
        this.mRoundStatus = roundStatus;
        this.mShadowData = shadowData;
        init();
    }

    private void init(){
        //阴影的画笔
        mPaintShadow = new Paint(Paint.ANTI_ALIAS_FLAG);
        //抗锯齿
        mPaintShadow.setAntiAlias(true);
        mPaintShadow.setStyle(Paint.Style.FILL);
        mPaintShadow.setStrokeCap(Paint.Cap.ROUND);
        //必须是实色
        mPaintShadow.setColor(Color.WHITE);

        mRectF = new RectF();
        mPath = new Path();
        mRoundStatus.fillRadius();
        initShadowLayer();
    }

    private void initShadowLayer(){
        //除以根号2是必须的，这样可确保阴影边缘不会被裁切
        mPaintShadow.setShadowLayer(mShadowData.getBlurRadius() /(float) Math.sqrt(2),
                mShadowData.getOffsetX(),
                mShadowData.getOffsetY(),
                mShadowData.getColor());
    }
///////////////////////////////////////////////////////////////////////////阴影相关
    @Override
    public int getBlurRadius() {
        return mShadowData.getBlurRadius();
    }

    @Override
    public void setBlurRadius(int radius) {
        mShadowData.setBlurRadius(radius);
        initShadowLayer();
        reMeasure();
    }

    @Override
    public int getShadowOffsetX() {
        return mShadowData.getOffsetX();
    }

    @Override
    public void setShadowOffsetX(int offsetX) {
        mShadowData.setOffsetX(offsetX);
        initShadowLayer();
        reMeasure();
    }

    @Override
    public int getShadowOffsetY() {
        return mShadowData.getOffsetY();
    }

    @Override
    public void setShadowOffsetY(int offsetY) {
        mShadowData.setOffsetY(offsetY);
        initShadowLayer();
        reMeasure();
    }

    @Override
    public @ColorInt int getShadowColor() {
        return mShadowData.getColor();
    }

    @Override
    public void setShadowColor(@ColorInt int color) {
        mShadowData.setColor(color);
        initShadowLayer();
        mHostView.invalidate();
    }

    @Override
    public RoundStatus getRoundStatus() {
        return mRoundStatus;
    }

    @Override
    public void setRadius(@NonNull RoundStatus roundStatus) {
        this.mRoundStatus = roundStatus;
        mRoundStatus.fillRadius();
        mPath.reset();
        mPath.addRoundRect(mRectF, mRoundStatus.getRadiusList(), Path.Direction.CW);
        mHostView.invalidate();
    }

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w > 0){
            int left = mShadowData.getBlurRadius() + Math.abs(mShadowData.getOffsetX()) + 1;//多留一个像素
            int top = mShadowData.getBlurRadius() + Math.abs(mShadowData.getOffsetY()) + 1;//多留一个像素
            mRectF.set(left, top, w - left, h - top);

            mPath.reset();
            mPath.addRoundRect(mRectF, mRoundStatus.getRadiusList(), Path.Direction.CW);
        }
    }

    //需要重新测量 View 时调用
    private void reMeasure(){
        isMeasure = false;
        onMeasure();
    }

    @Override
    public void onMeasure() {
        int measureWidth = mHostView.getMeasuredWidth();
        int measureHeight = mHostView.getMeasuredHeight();
        if (measureWidth > 0 && measureHeight > 0 && !isMeasure){
            isMeasure = true;
            if (mHostOriginWidth == 0 && mHostOriginHeight == 0){
                mHostOriginWidth = measureWidth;
                mHostOriginHeight = measureHeight;
            }
            ViewGroup.LayoutParams lp = mHostView.getLayoutParams();
            lp.width = mHostOriginWidth + (mShadowData.getBlurRadius() << 1) + (Math.abs(mShadowData.getOffsetX()) << 1);
            lp.height = mHostOriginHeight + (mShadowData.getBlurRadius() << 1) + (Math.abs(mShadowData.getOffsetY()) << 1);
            mHostView.setLayoutParams(lp);
            setPadding();
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawPath(mPath, mPaintShadow);
    }

    //给宿主 View 设置内边距，应在 measure 之后
    private void setPadding(){
        //暂时不用缩放画布给阴影留出空间的方式了，而是给 View 留出内边距给阴影足够的显示空间
        int left = mShadowData.getBlurRadius() + Math.abs(mShadowData.getOffsetX());// right 同
        int top = mShadowData.getBlurRadius() + Math.abs(mShadowData.getOffsetY());// bottom 同
        mHostView.setPadding(left, top, left, top);
    }
}
