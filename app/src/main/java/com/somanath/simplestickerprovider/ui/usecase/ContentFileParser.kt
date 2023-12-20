package com.somanath.simplestickerprovider.ui.usecase

import android.text.TextUtils
import android.util.JsonReader
import com.somanath.simplestickerprovider.ui.contentprovider.model.Sticker
import com.somanath.simplestickerprovider.ui.contentprovider.model.StickerPack
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class ContentFileParser {

    companion object {
        const val LIMIT_EMOJI_COUNT = 2
    }

    @Throws(IOException::class, IllegalStateException::class)
    fun parseStickerPacks(contentsInputStream: InputStream): List<StickerPack> {
        JsonReader(InputStreamReader(contentsInputStream)).use { reader -> return readStickerPackList(reader) }
    }

    @Throws(IOException::class, IllegalStateException::class)
    private fun readStickerPackList(reader: JsonReader): List<StickerPack> {
        val stickerPackList = ArrayList<StickerPack>()
        var androidPlayStoreLink: String? = null
        var iosAppStoreLink: String? = null
        reader.beginObject()
        while (reader.hasNext()) {
            when (val key = reader.nextName()) {
                "android_play_store_link" -> androidPlayStoreLink = reader.nextString()
                "ios_app_store_link" -> iosAppStoreLink = reader.nextString()
                "sticker_packs" -> {
                    reader.beginArray()
                    while (reader.hasNext()) {
                        val stickerPack = readStickerPack(reader)
                        stickerPackList.add(stickerPack)
                    }
                    reader.endArray()
                }
                else -> throw IllegalStateException("unknown field in json: $key")
            }
        }
        reader.endObject()
        if (stickerPackList.size == 0) {
            throw IllegalStateException("sticker pack list cannot be empty")
        }
        for (stickerPack in stickerPackList) {
            stickerPack.androidPlayStoreLink = androidPlayStoreLink
            stickerPack.iosAppStoreLink = iosAppStoreLink
        }
        return stickerPackList
    }

    @Throws(IOException::class, IllegalStateException::class)
    private fun readStickerPack(reader: JsonReader): StickerPack {
        reader.beginObject()
        var identifier: String? = null
        var name: String? = null
        var publisher: String? = null
        var trayImageFile: String? = null
        var publisherEmail: String? = null
        var publisherWebsite: String? = null
        var privacyPolicyWebsite: String? = null
        var licenseAgreementWebsite: String? = null
        var stickerList: List<Sticker>? = null
        while (reader.hasNext()) {
            when (reader.nextName()) {
                "identifier" -> identifier = reader.nextString()
                "name" -> name = reader.nextString()
                "publisher" -> publisher = reader.nextString()
                "tray_image_file" -> trayImageFile = reader.nextString()
                "publisher_email" -> publisherEmail = reader.nextString()
                "publisher_website" -> publisherWebsite = reader.nextString()
                "privacy_policy_website" -> privacyPolicyWebsite = reader.nextString()
                "license_agreement_website" -> licenseAgreementWebsite = reader.nextString()
                "stickers" -> stickerList = readStickers(reader)
                else -> reader.skipValue()
            }
        }

        when (identifier.isNullOrBlank()) {
            true -> throw IllegalStateException("identifier cannot be empty")
            false -> {
                if (identifier.contains("..") || identifier.contains("/")) {
                    throw IllegalStateException("identifier should not contain .. or / to prevent directory traversal")
                }
            }
        }

        if (name.isNullOrBlank()) {
            throw IllegalStateException("name cannot be empty")
        }
        if (publisher.isNullOrBlank()) {
            throw IllegalStateException("publisher cannot be empty")
        }
        if (trayImageFile.isNullOrBlank()) {
            throw IllegalStateException("tray_image_file cannot be empty")
        }
        if (stickerList.isNullOrEmpty()) {
            throw IllegalStateException("sticker list is empty")
        }
        reader.endObject()

        return StickerPack(
            identifier = identifier,
            name = name,
            publisher = publisher,
            trayImageFile = trayImageFile,
            publisherEmail = publisherEmail,
            publisherWebsite = publisherWebsite,
            privacyPolicyWebsite = privacyPolicyWebsite,
            licenseAgreementWebsite = licenseAgreementWebsite,
            stickers = stickerList
        )
    }

    @Throws(IOException::class, IllegalStateException::class)
    private fun readStickers(reader: JsonReader): List<Sticker> {
        reader.beginArray()
        val stickerList = java.util.ArrayList<Sticker>()

        while (reader.hasNext()) {
            reader.beginObject()
            var imageFile: String? = null
            val emojis = ArrayList<String>(LIMIT_EMOJI_COUNT)
            while (reader.hasNext()) {
                when (val key = reader.nextName()) {
                    "image_file" -> imageFile = reader.nextString()
                    "emojis" -> {
                        reader.beginArray()
                        while (reader.hasNext()) {
                            val emoji = reader.nextString()
                            emojis.add(emoji)
                        }
                        reader.endArray()
                    }
                    else -> throw IllegalStateException("unknown field in json: $key")
                }
            }
            reader.endObject()
            if (TextUtils.isEmpty(imageFile)) {
                throw IllegalStateException("sticker image_file cannot be empty")
            }
            if (!imageFile!!.endsWith(".webp")) {
                throw IllegalStateException("image file for stickers should be webp files, image file is: $imageFile")
            }
            if (imageFile.contains("..") || imageFile.contains("/")) {
                throw IllegalStateException("the file name should not contain .. or / to prevent directory traversal, image file is:$imageFile")
            }
            stickerList.add(Sticker(imageFile, emojis))
        }
        reader.endArray()
        return stickerList
    }
}