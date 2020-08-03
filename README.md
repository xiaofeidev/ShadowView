# ShadowView[ ![Download](https://api.bintray.com/packages/xiaofei00/xiaofei/ShadowView/images/download.svg) ](https://bintray.com/xiaofei00/xiaofei/ShadowView/_latestVersion)

实现简单的 Android 原生阴影控件。

`Gradle` 依赖：

```
implementation 'com.github.xiaofeidev:shadow:latest_version'
```
## 界面预览：

<img src="https://github.com/xiaofei-dev/ShadowView/blob/master/art/preview.jpg" width="40%" height="40%">


实现阴影效果的原理主要是在原 `View` 的底下绘制一个阴影(图层)。具体阴影的形状和效果需要客户端自己定义，且必须要由客户端自己定义出来。可定义属性包括阴影的形状，模糊半径，颜色，X 轴偏移量，Y 轴偏移量等。阴影的形状以一个圆角矩形或正圆形为主，注意阴影的形状不可太复杂，你只能将阴影定义成上图中那样简单规则的形状。

`ShadowImageView` 为本项目核心控件

## 自定义属性：

|              属性名               |              解释              |
| :-------------------------------: | :----------------------------: |
|       shadow_corner_radius        | 矩形阴影图层的圆角半径，尺寸值 |
|   shadow_corner_top_left_radius   |  阴影图层左上圆角半径，尺寸值  |
|  shadow_corner_top_right_radius   |  阴影图层右上圆角半径，尺寸值  |
| shadow_corner_bottom_left_radius  |  阴影图层左下圆角半径，尺寸值  |
| shadow_corner_bottom_right_radius |  阴影图层右下圆角半径，尺寸值  |
|        shadow_blur_radius         |     阴影的模糊半径，尺寸值     |
|          shadow_offset_x          |   阴影的 x 轴偏移量，尺寸值    |
|          shadow_offset_y          |   阴影的 y 轴偏移量，尺寸值    |
|           shadow_color            |        阴影颜色，颜色值        |

## 简单示例：

```xml
<com.github.xiaofeidev.shadow.ShadowImageView
    android:id="@+id/img2"
    android:layout_width="150dp"
    android:layout_height="150dp"
    app:layout_constraintStart_toEndOf="@id/img1"
    app:layout_constraintTop_toTopOf="parent"
    android:scaleType="fitXY"
    android:adjustViewBounds="true"
    app:srcCompat="@drawable/round_red"
    app:shadow_corner_radius="20dp"
    app:shadow_blur_radius="20dp"
    app:shadow_color="@android:color/black"/>
```

效果：

<img src="https://github.com/xiaofei-dev/ShadowView/blob/master/art/preview1.jpg" width="20%" height="20%">
