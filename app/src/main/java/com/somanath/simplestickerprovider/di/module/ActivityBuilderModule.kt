package com.somanath.simplestickerprovider.di.module


import com.somanath.simplestickerprovider.MainActivity
import com.somanath.simplestickerprovider.ui.contentprovider.StickerContentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class, FragmentBuilderModule::class])
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun stickerStickerContentProvider(): StickerContentProvider
}
