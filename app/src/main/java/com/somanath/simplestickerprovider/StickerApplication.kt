package com.somanath.simplestickerprovider


import com.somanath.simplestickerprovider.di.component.AppComponent
import com.somanath.simplestickerprovider.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerApplication

class StickerApplication : DaggerApplication(), HasAndroidInjector{

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
//        initLogger()
        enableVectorSupport()
//        initToaster()
        initFresco()
//        logDebug { "Application created!"
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        component = DaggerAppComponent.builder().application(this).build()

        return component
    }
}
