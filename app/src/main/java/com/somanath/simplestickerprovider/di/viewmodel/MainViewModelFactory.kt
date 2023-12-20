package com.somanath.simplestickerprovider.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.somanath.simplestickerprovider.ui.usecase.ActionResolverUseCase
import com.somanath.simplestickerprovider.ui.usecase.IntentResolverUseCase
import com.somanath.simplestickerprovider.ui.usecase.StickerPackLoaderUseCase

class MainViewModelFactory(
    private val stickerPackLoaderUseCase: StickerPackLoaderUseCase,
    private val intentResolverUseCase: IntentResolverUseCase,
    private val actionResolverUseCase: ActionResolverUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")

        return MainViewModel(stickerPackLoaderUseCase, intentResolverUseCase, actionResolverUseCase) as T
    }
}