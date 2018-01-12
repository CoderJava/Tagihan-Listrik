package com.tagihanlistrik.ysn.di.module

import com.tagihanlistrik.ysn.api.bill.Endpoints
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by yudisetiawan on 1/12/18.
 */

@Module
class ApiModule {

    @Provides
    @Singleton
    fun providesEndpoints(retrofit: Retrofit): Endpoints =
            retrofit.create(Endpoints::class.java)

}