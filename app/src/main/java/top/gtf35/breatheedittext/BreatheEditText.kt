package top.gtf35.breatheedittext

import android.animation.ObjectAnimator
import android.animation.ValueAnimator.INFINITE
import android.animation.ValueAnimator.REVERSE
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.GradientDrawable
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText

class BreatheEditText(context: Context) : AppCompatEditText(context) {

    private var cursorAlpha = 0
        set(value) {
            field = value
            invalidate()
        }
    private val paint = Paint(Color.BLACK)

    /*自定义透明光标，因为计算光标位置的代码基于原版 blink 的代码，需要依赖他来计算绘制位置，所以不能直接不让他显示*/
    private val cursorDrawable by lazy {
        GradientDrawable().apply {
            val round = dp(10)
            cornerRadii = floatArrayOf(
                round, round,
                round, round,
                round, round,
                round, round
            )
            setColor(Color.TRANSPARENT)
        }
    }

    private val breatheAnimator = ObjectAnimator.ofInt(this, "cursorAlpha", 0, 255).apply {
        duration = BREATHE_DURATION
        repeatMode = REVERSE
        repeatCount = INFINITE
    }

    @SuppressLint("DiscouragedPrivateApi")
    private val getVerticalOffsetMethod = try {
        TextView::class.java.getDeclaredMethod("getVerticalOffset", Boolean::class.javaPrimitiveType)
            .apply { isAccessible = true }
    } catch (t: Throwable) {
        t.printStackTrace()
        null
    }

    @Suppress("SameParameterValue")
    private fun getVerticalOffsetByHook(forceNormal: Boolean): Int {
        if (getVerticalOffsetMethod == null) return 0
        return getVerticalOffsetMethod.invoke(this, forceNormal) as Int
    }

    init {
        setCompatCursorDrawable(cursorDrawable)
        breatheAnimator.start()
    }

    override fun onVisibilityAggregated(isVisible: Boolean) {
        super.onVisibilityAggregated(isVisible)
        if (!isVisible) breatheAnimator.pause() else breatheAnimator.resume()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        breatheAnimator.pause()
    }

    override fun onDraw(canvas: Canvas?) {
        // 光标绘制区域来自：https://github.com/aosp-mirror/platform_frameworks_base/blob/master/core/java/android/widget/TextView.java#L7441
        val horizontalPadding = compoundPaddingLeft
        val verticalPadding = extendedPaddingTop + getVerticalOffsetByHook(true)
        val bounds = cursorDrawable.bounds
        paint.alpha = cursorAlpha
        canvas?.drawRect(
            bounds.left + horizontalPadding.toFloat(),
            bounds.top + verticalPadding.toFloat(),
            bounds.right + horizontalPadding.toFloat(),
            bounds.bottom + verticalPadding.toFloat(),
            paint
        )
        super.onDraw(canvas)
    }

    companion object {
        private const val BREATHE_DURATION = 1000L
    }
}