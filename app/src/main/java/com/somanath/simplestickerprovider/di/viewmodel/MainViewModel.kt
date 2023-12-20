package com.somanath.simplestickerprovider.di.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.somanath.simplestickerprovider.ui.contentprovider.model.StickerPack
import com.somanath.simplestickerprovider.ui.usecase.ActionResolverUseCase
import com.somanath.simplestickerprovider.ui.usecase.IntentResolverUseCase
import com.somanath.simplestickerprovider.ui.usecase.StickerPackLoaderUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val stickerPackLoaderUseCase: StickerPackLoaderUseCase,
    private val intentResolverUseCase: IntentResolverUseCase,
    private val actionResolverUseCase: ActionResolverUseCase
) : ViewModel() {

//    val toastSingleLiveEvent = SingleLiveEvent<ToastMessage>()
//    val launchIntentSingleLiveEvent = SingleLiveEvent<LaunchIntentCommand>()
    val stickerData = MutableLiveData<ArrayList<StickerPack>>()
    val detailsStickerPackData = MutableLiveData<StickerPack>()

    private var currentDetailsPosition = 0

    fun loadStickers() {
        viewModelScope.launch {
//            stickerPackLoaderUseCase.loadStickerPacks().fold(
//                ifSuccess = { stickerPacks ->
//                    stickerData.value = stickerPacks
//                    updateDetailsStickerPack(currentDetailsPosition)
//                },
//                ifError = {
//                    when (it) {
//                        is GenericError.UnknownError -> toastSingleLiveEvent.value =
//                            ToastMessage.Error(message = Message(it.message))
//                        else -> toastSingleLiveEvent.value = ToastMessage.Error(message = Message("Some error happened"))
//                    }
//                }
//            )
        }
    }

    fun tryToAddStickerPack(identifier: String, packName: String) {
        viewModelScope.launch {
//            try {
//                when (actionResolverUseCase.resolveActionAddStickerPack(identifier)) {
//                    APPS_NOT_FOUND -> {
//                        toastSingleLiveEvent.value =
//                            ToastMessage.Error(resource = Resource(R.string.add_pack_fail_prompt_update_whatsapp))
//                    }
//                    ASK_USER_WHICH_APP -> {
//                        val intent = intentResolverUseCase.createChooserIntentToAddStickerPack(identifier, packName)
//                        launchIntentSingleLiveEvent.value = LaunchIntentCommand.Chooser(intent)
//                    }
//                    ADD_TO_CONSUMER -> {
//                        val intent = intentResolverUseCase.createConsumerIntentToAddStickerPack(identifier, packName)
//                        launchIntentSingleLiveEvent.value = LaunchIntentCommand.Specific(intent)
//                    }
//                    ADD_TO_BUSINESS -> {
//                        val intent = intentResolverUseCase.createBusinessIntentToAddStickerPack(identifier, packName)
//                        launchIntentSingleLiveEvent.value = LaunchIntentCommand.Specific(intent)
//                    }
//                    PROMPT_UPDATE_CAUSE_FAILURE -> {
//                        toastSingleLiveEvent.value =
//                            ToastMessage.Error(resource = Resource(R.string.add_pack_fail_prompt_update_whatsapp))
//                    }
//                }
//            } catch (e: Exception) {
//                toastSingleLiveEvent.value =
//                    ToastMessage.Error(resource = Resource(R.string.add_pack_fail_prompt_update_whatsapp))
//            }
        }
    }

    fun updateDetailsStickerPack(position: Int) {
        currentDetailsPosition = position
        detailsStickerPackData.postValue(stickerData.value?.get(currentDetailsPosition))
    }

    override fun onCleared() {
//        logDebug { "MainViewModel cleared" }
        super.onCleared()
    }
}
