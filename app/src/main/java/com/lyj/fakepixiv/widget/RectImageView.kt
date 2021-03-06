package com.lyj.fakepixiv.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

/**
 * @author greensun
 *
 * @date 2019/3/23
 *
 * @desc
 */
class RectImageView : ImageView {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        val mode = MeasureSpec.getMode(heightMeasureSpec)
        val res: Int
        res = if (width > 0 && height > 0) {
            if (height > width) width else height
        } else {
            if (height > width) height else width
        }
        val size = MeasureSpec.makeMeasureSpec(res, mode)
        setMeasuredDimension(size, size)
    }
}