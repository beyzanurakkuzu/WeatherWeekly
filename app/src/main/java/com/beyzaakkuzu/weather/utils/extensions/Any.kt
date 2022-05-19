package com.beyzaakkuzu.weather.utils.extensions

import android.text.Spannable
import android.text.SpannableString
import android.text.TextUtils
import java.lang.Exception

fun tryCatch(
    tryBlock:() ->Unit,
    catchBlock:((t:Throwable)-> Unit)? = null,
    finalBlock:(()->Unit)? = null
){
    try {
        tryBlock()
    }
    catch (
        e:Exception
    ){
        catchBlock?.invoke(e)
    }finally {
        finalBlock?.invoke()
    }
}

fun spannable(func: ()-> SpannableString)= func()

private fun span(s: CharSequence, o: Any) = (
        if (s is String) SpannableString(s) else s as? SpannableString
            ?: SpannableString("")
        ).apply { setSpan(o, 0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE) }

operator fun SpannableString.plus(s: SpannableString) = SpannableString(TextUtils.concat(this, s))

operator fun SpannableString.plus(s: String) = SpannableString(TextUtils.concat(this, s))
