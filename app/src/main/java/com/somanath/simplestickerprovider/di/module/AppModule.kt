package com.somanath.simplestickerprovider.di.module

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.content.UriMatcher
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.content.res.Resources
import com.somanath.simplestickerprovider.R
import com.somanath.simplestickerprovider.ui.contentprovider.StickerProviderHelper
import com.somanath.simplestickerprovider.ui.coroutine.DefaultDispatcherProvider
import com.somanath.simplestickerprovider.ui.coroutine.DispatcherProvider
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    companion object {
        const val CONTENT_PROVIDER_AUTHORITY = "key.content.provider.authority"
        const val PACKAGE_NAME = "key.package.name"
    }

    @Provides
    @Singleton
    fun providesContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun providesResources(application: Application): Resources {
        return application.resources
    }

    @Provides
    @Singleton
    fun providesAssetManager(application: Application): AssetManager {
        return application.assets
    }

    @Provides
    @Singleton
    fun providesPackageManager(application: Application): PackageManager {
        return application.packageManager
    }

    @Provides
    @Named(CONTENT_PROVIDER_AUTHORITY)
    fun providesContentProviderAuthority(resources: Resources): String {
        return resources.getString(R.string.content_provider_authority)
    }

    @Provides
    @Named(PACKAGE_NAME)
    fun providesPackageName(application: Application): String {
        return application.packageName
    }

    @Provides
    fun providesApplicationContentResolver(application: Application): ContentResolver {
        return application.contentResolver
    }

    @Provides
    fun providesUriMatcher(): UriMatcher {
        return UriMatcher(UriMatcher.NO_MATCH)
    }

    @Provides
    fun providesStickerProviderHelper(
        @Named(CONTENT_PROVIDER_AUTHORITY) providerAuthority: String,
        @Named(PACKAGE_NAME) packageName: String,
        contentResolver: ContentResolver
    ): StickerProviderHelper {
        if (!providerAuthority.startsWith(packageName)) {
            throw IllegalStateException("Your authority $providerAuthority or the content provider should start with your package name: $packageName")
        }
        return StickerProviderHelper(providerAuthority, contentResolver)
    }

//    @Provides
//    @Singleton
//    fun providesBus(): Bus = Bus()
//
//    @Provides
//    fun providesDebugFlag(): Boolean {
//        return BuildConfig.DEBUG
//    }
//
//    @Provides
//    fun providesGson(): Gson {
//        return GsonBuilder().setPrettyPrinting().create()
//    }

    @Singleton
    @Provides
    internal fun providesDispatcherProvider(): DispatcherProvider {
        return DefaultDispatcherProvider()
    }
}
