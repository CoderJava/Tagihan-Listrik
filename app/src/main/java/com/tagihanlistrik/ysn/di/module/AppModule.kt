package com.tagihanlistrik.ysn.di.module

import android.app.Application
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by yudisetiawan on 1/12/18.
 */

@Module
class AppModule(val application: Application) {

    @Provides
    @Singleton
    fun providesGson() = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideApplication() = application

    @Provides
    @Singleton
    fun providesResource() = application.resources

}