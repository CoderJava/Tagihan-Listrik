package com.tagihanlistrik.ysn.di.module

import com.tagihanlistrik.ysn.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by yudisetiawan on 1/12/18.
 */

@Module
class RetrofitModule {

    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_API_BISA_TOP_UP)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
}