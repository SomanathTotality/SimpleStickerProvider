package com.somanath.simplestickerprovider

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.facebook.drawee.backends.pipeline.Fresco


fun Application.enableVectorSupport() {
    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
}

fun Application.initToaster() {
//    Toasty.Config.getInstance().apply {
//        setToastTypeface(ResourcesCompat.getFont(this@initToaster, R.font.finger_paint)!!)
//        setErrorColor(ContextCompat.getColor(this@initToaster, R.color.error))
//        setInfoColor(ContextCompat.getColor(this@initToaster, R.color.primary_dark))
//        setSuccessColor(ContextCompat.getColor(this@initToaster, R.color.primary))
//        setWarningColor(ContextCompat.getColor(this@initToaster, R.color.accent))
//        setTextColor(ContextCompat.getColor(this@initToaster, R.color.white))
//        tintIcon(true)
//        apply()
//    }
}

fun Application.initLogger() {
//    if (BuildConfig.DEBUG) {
//        Timber.plant(Timber.DebugTree())
//        logInfo { "Timber is initialised" }
//    } else {
//        logError { "You should not be seeing this!" }
//    }
}

fun Application.initFresco() {
    Fresco.initialize(this)
}
