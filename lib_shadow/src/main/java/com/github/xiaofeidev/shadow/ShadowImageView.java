package com.github.xiaofeidev.shadow;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.github.xiaofeidev.shadow.round.RoundStatus;
import com.github.xiaofeidev.shadow.round.RoundStatusImpl;
import com.github.xiaofeidev.shadow.shadow.ShadowData;
import com.github.xiaofeidev.shadow.shadow.ShadowHelper;
import com.github.xiaofeidev.shadow.shadow.ShadowStatus;

import static com.github.xiaofeidev.shadow.round.RoundStatus.INVALID_VALUE;

public class ShadowImageView extends AppCompatImageView implements ShadowStatus {
    //处理阴影相关逻辑
    private ShadowHelper mShadowHelper;

    public ShadowImageView(Context context) {
        this(context, null);
    }

    public ShadowImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ShadowImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    @Override
    public int getBlurRadius() {
        return mShadowHelper.getBlurRadius();
    }

    @Override
    public void setBlurRadius(int radius) {
        mShadowHelper.setBlurRadius(radius);
    }

    @Override
    public int getShadowOffsetX() {
        return mShadowHelper.getShadowOffsetX();
    }

    @Override
    public void setShadowOffsetX(int offsetX) {
        mShadowHelper.setShadowOffsetX(offsetX);
    }

    @Override
    public int getShadowOffsetY() {
        return mShadowHelper.getShadowOffsetY();
    }

    @Override
    public void setShadowOffsetY(int offsetY) {
        mShadowHelper.setShadowOffsetY(offsetY);
    }

    @Override
    public int getShadowColor() {
        return mShadowHelper.getShadowColor();
    }

    @Override
    public void setShadowColor(int color) {
        mShadowHelper.setShadowColor(color);
    }

    @Override
    public RoundStatus getRoundStatus() {
        return mShadowHelper.getRoundStatus();
    }

    @Override
    public void setRadius(@NonNull RoundStatus roundStatus) {
        mShadowHelper.setRadius(roundStatus);
    }

    private void init(Context context, @Nullable AttributeSet attrs, int defStyleAttr){
        //关闭硬件加速
        if (Build.VERSION.SDK_INT < 28){
            setLayerType(LAYER_TYPE_SOFTWARE, null);
        }

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ShadowImageView,
                0, 0);

        try {
            float radius, topLeftRadius, topRightRadius, bottomLeftRadius, bottomRightRadius;

            radius = typedArray.getDimension(R.styleable.ShadowImageView_shadow_corner_radius, 0);;
            topLeftRadius = typedArray.getDimension(R.styleable.ShadowImageView_shadow_corner_top_left_radius, INVALID_VALUE);
            topRightRadius = typedArray.getDimension(R.styleable.ShadowImageView_shadow_corner_top_right_radius, INVALID_VALUE);
            bottomRightRadius = typedArray.getDimension(R.styleable.ShadowImageView_shadow_corner_bottom_right_radius, INVALID_VALUE);
            bottomLeftRadius = typedArray.getDimension(R.styleable.ShadowImageView_shadow_corner_bottom_left_radius, INVALID_VALUE);

            RoundStatus mRoundStatus = new RoundStatusImpl.RoundStatusBuilder()
                    .setMRadius(radius)
                    .setMTopLeftRadius(topLeftRadius)
                    .setMTopRightRadius(topRightRadius)
                    .setMBottomRightRadius(bottomRightRadius)
                    .setMBottomLeftRadius(bottomLeftRadius)
                    .build();

            int blurRadius = typedArray.getDimensionPixelSize(R.styleable.ShadowImageView_shadow_blur_radius, 0);
            int shadowOffsetX = typedArray.getDimensionPixelSize(R.styleable.ShadowImageView_shadow_offset_x, 0);
            int shadowOffsetY = typedArray.getDimensionPixelSize(R.styleable.ShadowImageView_shadow_offset_y, 0);
            int shadowColor = typedArray.getColor(R.styleable.ShadowImageView_shadow_color, Color.BLACK);
            //保证数值合法
            if (blurRadius < 0){
                blurRadius = 0;
            }
            //偏移量不可过头
            if (Math.abs(shadowOffsetX) > blurRadius){
                if (shadowOffsetX > 0){
                    shadowOffsetX = blurRadius;
                } else {
                    shadowOffsetX = -blurRadius;
                }
            }
            //偏移量不可过头
            if (Math.abs(shadowOffsetY) > blurRadius){
                if (shadowOffsetY > 0){
                    shadowOffsetY = blurRadius;
                } else {
                    shadowOffsetY = -blurRadius;
                }
            }

            ShadowData shadowData = ShadowData.ShadowDataBuilder.instance()
                    .setBlurRadius(blurRadius)
                    .setOffsetX(shadowOffsetX)
                    .setOffsetY(shadowOffsetY)
                    .setColor(shadowColor)
                    .build();

            mShadowHelper = new ShadowHelper(this, mRoundStatus, shadowData);
        } finally {
            //注意资源回收
            typedArray.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mShadowHelper.onMeasure();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mShadowHelper.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public void draw(Canvas canvas) {
        //先画阴影
        mShadowHelper.onDraw(canvas);
        //再画原 View
        super.draw(canvas);
    }
}
