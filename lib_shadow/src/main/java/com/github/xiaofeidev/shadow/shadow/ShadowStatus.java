package com.github.xiaofeidev.shadow.shadow;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

import com.github.xiaofeidev.shadow.round.RoundStatus;

/**
 * 记录一个 View 的阴影状态
 * @author 黎曼
 * @date 2020/7/18
 */
public interface ShadowStatus {
    //获取阴影模糊半径
    int getBlurRadius();
    //设置阴影模糊半径
    void setBlurRadius(int radius);
    //获取阴影 x 轴的偏移量
    int getShadowOffsetX();
    //设置阴影 x 轴的偏移量
    void setShadowOffsetX(int offsetX);
    //获取阴影 y 轴的偏移量
    int getShadowOffsetY();
    //设置阴影 y 轴的偏移量
    void setShadowOffsetY(int offsetY);
    //获取阴影颜色
    @ColorInt int getShadowColor();
    //设置阴影颜色
    void setShadowColor(@ColorInt int color);
    //获取圆角信息
    RoundStatus getRoundStatus();
    //设置圆角信息
    void setRadius(@NonNull RoundStatus roundStatus);
}
