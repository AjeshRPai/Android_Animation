package com.example.ajeshpai.androidanimatios.PathAnimations

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.animation.ValueAnimator.INFINITE
import android.animation.ValueAnimator.RESTART
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.FloatProperty
import android.view.animation.LinearInterpolator
import android.view.animation.PathInterpolator
import androidx.annotation.RequiresApi
import com.example.ajeshpai.androidanimatios.R
import kotlinx.android.synthetic.main.activity_polygon_path.*

class PolygonPathAnimation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_polygon_path)
        val drawable = PolygonLapsDrawable();


        ObjectAnimator.ofFloat(drawable, PROGRESS, 0f, 1f).apply {
            duration = 4000L
            interpolator = LinearInterpolator()
            repeatCount = INFINITE
            repeatMode = RESTART
        }.start()

        polygon_path.setImageDrawable(drawable)
    }


    @RequiresApi(Build.VERSION_CODES.N)
    object PROGRESS : FloatProperty<PolygonLapsDrawable>("progress") {
        override fun setValue(drawable: PolygonLapsDrawable, progress: Float) {
            drawable.progress = progress
        }

        override fun get(drawable: PolygonLapsDrawable) = drawable.progress
    }

    @RequiresApi(Build.VERSION_CODES.N)
    object DOT_PROGRESS : FloatProperty<PolygonLapsDrawable>("dotProgress") {
        override fun setValue(drawable: PolygonLapsDrawable, dotProgress: Float) {
            drawable.dotProgress = dotProgress
        }

        override fun get(drawable: PolygonLapsDrawable) = drawable.dotProgress
    }
}
