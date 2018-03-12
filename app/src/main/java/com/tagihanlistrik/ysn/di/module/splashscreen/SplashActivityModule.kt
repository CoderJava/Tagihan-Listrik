package com.tagihanlistrik.ysn.di.module.splashscreen

import com.tagihanlistrik.ysn.di.ActivityScope
import com.tagihanlistrik.ysn.views.ui.splashscreen.SplashPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by yudisetiawan on 3/12/18.
 */

@Module
class SplashActivityModule {

    @Provides
    @ActivityScope
    internal fun providesSplashPresenter(): SplashPresenter = SplashPresenter()

}