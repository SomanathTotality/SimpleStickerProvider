package com.somanath.simplestickerprovider.ui.contentprovider.model

data class Sticker(
    val imageFileName: String? = null,
    val emojis: List<String>? = null,
    var size: Long = 0
)
