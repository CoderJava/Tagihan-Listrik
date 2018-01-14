package com.tagihanlistrik.ysn.di.component

import android.app.Application
import com.google.gson.Gson
import com.tagihanlistrik.ysn.api.bill.Endpoints
import com.tagihanlistrik.ysn.db.dao.BillLocalDao
import com.tagihanlistrik.ysn.di.module.*
import dagger.Component
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by yudisetiawan on 1/12/18.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class, RetrofitModule::class, ApiModule::class, OkHttpModule::class, RoomModule::class))
interface AppComponent {

    fun application(): Application

    fun gson(): Gson

    fun retrofit(): Retrofit

    fun endpoints(): Endpoints

    fun cache(): Cache

    fun client(): OkHttpClient

    fun loggingInterceptor(): HttpLoggingInterceptor

    fun billLocalDao(): BillLocalDao

}