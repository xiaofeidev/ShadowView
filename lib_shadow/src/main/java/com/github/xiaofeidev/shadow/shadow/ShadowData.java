package com.github.xiaofeidev.shadow.shadow;

import androidx.annotation.ColorInt;

/**
 * 阴影的一些模糊半径等重要数据
 * @author 黎曼
 * @date 2020/7/18
 */
public class ShadowData {
    //模糊半径
    private int blurRadius;
    //x 轴偏移
    private int offsetX;
    //y 轴偏移
    private int offsetY;
    //阴影颜色
    private @ColorInt int color;

    public int getBlurRadius() {
        return blurRadius;
    }

    public void setBlurRadius(int blurRadius) {
        this.blurRadius = blurRadius;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }


    public static final class ShadowDataBuilder {
        //模糊半径
        private int blurRadius;
        //x 轴偏移
        private int offsetX;
        //y 轴偏移
        private int offsetY;
        //阴影颜色
        private int color;

        private ShadowDataBuilder() {
        }

        public static ShadowDataBuilder instance(){
            return new ShadowDataBuilder();
        }

        public ShadowDataBuilder setBlurRadius(int blurRadius) {
            this.blurRadius = blurRadius;
            return this;
        }

        public ShadowDataBuilder setOffsetX(int offsetX) {
            this.offsetX = offsetX;
            return this;
        }

        public ShadowDataBuilder setOffsetY(int offsetY) {
            this.offsetY = offsetY;
            return this;
        }

        public ShadowDataBuilder setColor(int color) {
            this.color = color;
            return this;
        }

        public ShadowData build() {
            ShadowData shadowData = new ShadowData();
            shadowData.setBlurRadius(blurRadius);
            shadowData.setOffsetX(offsetX);
            shadowData.setOffsetY(offsetY);
            shadowData.setColor(color);
            return shadowData;
        }
    }
}
