package com.tagihanlistrik.ysn.di.module.main

import com.tagihanlistrik.ysn.api.bill.Endpoints
import com.tagihanlistrik.ysn.di.ActivityScope
import com.tagihanlistrik.ysn.views.main.MainPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by yudisetiawan on 1/12/18.
 */

@Module
class MainActivityModule {

    @Provides
    @ActivityScope
    internal fun providesMainPresenter(api: Endpoints): MainPresenter {
        return MainPresenter(api)
    }

}