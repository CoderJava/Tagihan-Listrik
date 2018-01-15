package com.tagihanlistrik.ysn.di.module.about

import com.tagihanlistrik.ysn.di.ActivityScope
import com.tagihanlistrik.ysn.views.ui.about.AboutPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by yudisetiawan on 1/15/18.
 */

@Module
class AboutActivityModule {

    @Provides
    @ActivityScope
    internal fun provideAboutPresenter(): AboutPresenter {
        return AboutPresenter()
    }

}