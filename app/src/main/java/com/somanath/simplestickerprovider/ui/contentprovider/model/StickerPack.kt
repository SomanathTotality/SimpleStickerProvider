package com.somanath.simplestickerprovider.ui.contentprovider.model

import com.somanath.simplestickerprovider.ui.contentprovider.model.Sticker

data class StickerPack(
    val identifier: String? = null,
    val name: String? = null,
    val publisher: String? = null,
    val trayImageFile: String? = null,
    val publisherEmail: String?,
    val publisherWebsite: String?,
    val privacyPolicyWebsite: String?,
    val licenseAgreementWebsite: String?,
    var iosAppStoreLink: String? = null,
    var stickers: List<Sticker>? = null,
    var androidPlayStoreLink: String? = null,
    var isWhitelisted: Boolean = false
)