package com.wilmer.fooddataandlist.services

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.core.content.getSystemService

fun vibratePhone(context: Context, duration: Long = 500) {
    val vibrator = context.getSystemService<Vibrator>()
    val vibrationEffect = VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE)
    vibrator?.vibrate(vibrationEffect)
}
