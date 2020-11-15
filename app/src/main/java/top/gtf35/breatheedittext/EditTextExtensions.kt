package top.gtf35.breatheedittext

import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText

fun AppCompatEditText.setCompatCursorDrawable(gradientDrawable: GradientDrawable) {
    gradientDrawable.setSize(8, textSize.toInt())
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        this.textCursorDrawable = gradientDrawable
        return
    }

    try {
        val editorField = try {
            TextView::class.java.getDeclaredField("mEditor").apply { isAccessible = true }
        } catch (t: Throwable) {
            null
        }
        val editor = editorField?.get(this) ?: this
        val editorClass: Class<*> = if (editorField == null) TextView::class.java else editor.javaClass

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            editorClass
                .getDeclaredField("mDrawableForCursor")
                .apply { isAccessible = true }
                .run { set(editor, gradientDrawable) }
        } else {
            editorClass
                .getDeclaredField("mCursorDrawable")
                .apply { isAccessible = true }
                .run { set(editor, arrayOf(gradientDrawable, gradientDrawable)) }
        }
    } catch (t: Throwable) {
        t.printStackTrace()
    }
}