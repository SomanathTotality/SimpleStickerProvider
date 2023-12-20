package com.somanath.simplestickerprovider.di.component

import android.app.Application
import com.somanath.simplestickerprovider.StickerApplication
import com.somanath.simplestickerprovider.di.module.ActivityBuilderModule
import com.somanath.simplestickerprovider.di.module.AppModule
import com.somanath.simplestickerprovider.di.module.UseCaseModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
        AppModule::class,
        UseCaseModule::class
    ]
)
interface AppComponent : AndroidInjector<StickerApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
