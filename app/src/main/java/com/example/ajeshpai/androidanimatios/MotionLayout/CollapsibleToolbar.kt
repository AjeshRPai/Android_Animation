package com.example.ajeshpai.androidanimatios.MotionLayout

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.motion.widget.MotionLayout
import com.google.android.material.appbar.AppBarLayout

class CollapsibleToolbar @JvmOverloads constructor(
        context: Context,attributeSet: AttributeSet?=null,defStyleAttr:Int =0
) :MotionLayout(context,attributeSet,defStyleAttr),AppBarLayout.OnOffsetChangedListener{

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
           progress = -verticalOffset/appBarLayout?.totalScrollRange?.toFloat()!!
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        (parent as? AppBarLayout)?.addOnOffsetChangedListener(this)
    }
}