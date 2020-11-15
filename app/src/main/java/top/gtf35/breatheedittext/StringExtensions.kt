package top.gtf35.breatheedittext

import android.text.Editable

fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)