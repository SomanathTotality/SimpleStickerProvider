package com.somanath.simplestickerprovider.ui.usecase

import android.content.ContentResolver
import android.net.Uri
import com.somanath.simplestickerprovider.ui.contentprovider.METADATA
import com.somanath.simplestickerprovider.ui.contentprovider.STICKERS
import com.somanath.simplestickerprovider.ui.contentprovider.STICKERS_ASSET

class UriResolverUseCase(private val providerAuthority: String) {

    fun getStickerListUri(identifier: String): Uri =
        Uri.Builder()
            .scheme(ContentResolver.SCHEME_CONTENT)
            .authority(providerAuthority)
            .appendPath(STICKERS)
            .appendPath(identifier)
            .build()

    fun getStickerAssetUri(identifier: String, stickerName: String): Uri =
        Uri.Builder()
            .scheme(ContentResolver.SCHEME_CONTENT)
            .authority(providerAuthority)
            .appendPath(STICKERS_ASSET)
            .appendPath(identifier)
            .appendPath(stickerName)
            .build()

    fun getAuthorityUri(): Uri =
        Uri.Builder()
            .scheme(ContentResolver.SCHEME_CONTENT)
            .authority(providerAuthority)
            .appendPath(METADATA)
            .build()
}