package com.somanath.simplestickerprovider.di.module

import android.content.ContentResolver
import android.content.pm.PackageManager
import com.somanath.simplestickerprovider.ui.contentprovider.StickerProviderHelper
import com.somanath.simplestickerprovider.ui.coroutine.DispatcherProvider
import com.somanath.simplestickerprovider.ui.usecase.ActionResolverUseCase
import com.somanath.simplestickerprovider.ui.usecase.ContentFileParser
import com.somanath.simplestickerprovider.ui.usecase.FetchStickerAssetUseCase
import com.somanath.simplestickerprovider.ui.usecase.IntentResolverUseCase
import com.somanath.simplestickerprovider.ui.usecase.StickerPackLoaderUseCase
import com.somanath.simplestickerprovider.ui.usecase.StickerPackValidator
import com.somanath.simplestickerprovider.ui.usecase.UriResolverUseCase
import com.somanath.simplestickerprovider.ui.usecase.WhiteListCheckUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class UseCaseModule {

    @Provides
    internal fun providesContentFileParser(): ContentFileParser =
        ContentFileParser()

    @Provides
    internal fun providesStickerPackValidator(fetchStickerAssetUseCase: FetchStickerAssetUseCase): StickerPackValidator =
        StickerPackValidator(fetchStickerAssetUseCase)

    @Provides
    internal fun providesStickerPackLoaderUseCase(
        dispatchers: DispatcherProvider,
        stickerProviderHelper: StickerProviderHelper,
        fetchStickerAssetUseCase: FetchStickerAssetUseCase,
        uriResolverUseCase: UriResolverUseCase,
        stickerPackValidator: StickerPackValidator,
        whiteListCheckUseCase: WhiteListCheckUseCase
    ): StickerPackLoaderUseCase = StickerPackLoaderUseCase(
        dispatchers,
        stickerProviderHelper,
        fetchStickerAssetUseCase,
        uriResolverUseCase,
        stickerPackValidator,
        whiteListCheckUseCase
    )

    @Provides
    internal fun providesUriResolverUseCase(@Named(AppModule.CONTENT_PROVIDER_AUTHORITY) providerAuthority: String): UriResolverUseCase =
        UriResolverUseCase(providerAuthority)

    @Provides
    internal fun providesFetchStickerAssetUseCase(
        contentResolver: ContentResolver,
        uriResolverUseCase: UriResolverUseCase
    ): FetchStickerAssetUseCase = FetchStickerAssetUseCase(contentResolver, uriResolverUseCase)

    @Provides
    internal fun providesWhiteListCheckUseCase(
        stickerProviderHelper: StickerProviderHelper,
        packageManager: PackageManager
    ): WhiteListCheckUseCase = WhiteListCheckUseCase(stickerProviderHelper, packageManager)

    @Provides
    internal fun providesIntentResolverUseCase(
        @Named(AppModule.CONTENT_PROVIDER_AUTHORITY) providerAuthority: String
    ): IntentResolverUseCase = IntentResolverUseCase(providerAuthority)

    @Provides
    internal fun providesActionResolverUseCase(
        dispatchers: DispatcherProvider,
        whiteListCheckUseCase: WhiteListCheckUseCase
    ): ActionResolverUseCase = ActionResolverUseCase(dispatchers, whiteListCheckUseCase)
}
