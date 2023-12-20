package com.somanath.simplestickerprovider.di.module

import com.somanath.simplestickerprovider.di.viewmodel.MainViewModelFactory
import com.somanath.simplestickerprovider.ui.usecase.ActionResolverUseCase
import com.somanath.simplestickerprovider.ui.usecase.IntentResolverUseCase
import com.somanath.simplestickerprovider.ui.usecase.StickerPackLoaderUseCase
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {
    @Provides
    internal fun providesMainViewModelFactory(
        stickerPackLoaderUseCase: StickerPackLoaderUseCase,
        intentResolverUseCase: IntentResolverUseCase,
        actionResolverUseCase: ActionResolverUseCase
    ) = MainViewModelFactory(stickerPackLoaderUseCase, intentResolverUseCase, actionResolverUseCase)
}