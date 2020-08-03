package com.github.xiaofeidev.shadowview

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.github.xiaofeidev.shadow.ShadowImageView
import com.github.xiaofeidev.shadow.round.RoundStatus
import com.github.xiaofeidev.shadow.round.RoundStatusImpl.RoundStatusBuilder
import com.github.xiaofeidev.shadow.utils.SizeUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        val roundStatus: RoundStatus = RoundStatusBuilder()
            .setMRadius(SizeUtils.dp2px(100f))
            .build()

        img4.setRadius(roundStatus)
        img4.blurRadius = SizeUtils.dp2px(10f).toInt()
        img4.shadowOffsetY = SizeUtils.dp2px(5f).toInt()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            img4.shadowColor = resources.getColor(android.R.color.holo_red_dark, theme)
        } else {
            img4.shadowColor = resources.getColor(android.R.color.holo_red_dark)
        }
//        root.setOnClickListener {
//            val shadowImageView = ShadowImageView(this)
//            shadowImageView.setImageResource(R.drawable.ic_profile)
//            val lp = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//            root.addView(shadowImageView, lp)
//        }
    }
}