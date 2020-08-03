package com.github.xiaofeidev.shadow.shadow;

import android.graphics.Canvas;

/**
 * 阴影跟宿主 View 互动的契约
 * @author 黎曼
 * @date 2020/7/27
 */
public interface ShadowDraw {
    //跟宿主 View 互动
    void onSizeChanged(int w, int h, int oldw, int oldh);
    //跟宿主 View 互动
    void onMeasure();
    //跟宿主 View 互动
    void onDraw(Canvas canvas);
}
