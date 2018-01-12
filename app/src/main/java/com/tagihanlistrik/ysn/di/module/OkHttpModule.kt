package com.tagihanlistrik.ysn.di.module

import android.app.Application
import dagger.Module
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.tagihanlistrik.ysn.BuildConfig
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by yudisetiawan on 1/12/18.
 */

@Module
class OkHttpModule {

    private fun getBaseBuilder(cache: Cache): OkHttpClient.Builder {
        return OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .cache(cache)
                .retryOnConnectionFailure(true)
                .connectTimeout(1000 * 180, TimeUnit.MILLISECONDS)
                .readTimeout(1000 * 180, TimeUnit.MILLISECONDS)
                .writeTimeout(1000 * 180, TimeUnit.MILLISECONDS)
    }

    private class CachingControlInterceptor: Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            val requestBuilder = chain.request().newBuilder()
            val cacheControl = CacheControl.Builder()
                    .maxStale(1, TimeUnit.MINUTES)
                    .maxAge(1, TimeUnit.MINUTES)
                    .build();

            requestBuilder.cacheControl(cacheControl)
            requestBuilder.header("X-Authorization", BuildConfig.API_KEY)
            requestBuilder.header("Content-Type", "application/json")
            val request = requestBuilder.build()

            val originalResponse = chain.proceed(request)
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, only-if-cached, max-stale=604800")
                    .build()
        }
    }

    @Provides
    @Singleton
    fun providesOkHttpCache(pApplication: Application): Cache =
            Cache(pApplication.cacheDir, 10 * 1024 * 1024)

    @Provides
    @Singleton
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Singleton
    fun providesOkHttp(cache: Cache, loggingInterceptor: HttpLoggingInterceptor) = getBaseBuilder(cache)
            .addNetworkInterceptor(CachingControlInterceptor())
            .addInterceptor(loggingInterceptor)
            .build()
}