package com.tagihanlistrik.ysn.di.component.about

import com.tagihanlistrik.ysn.di.ActivityScope
import com.tagihanlistrik.ysn.di.component.AppComponent
import com.tagihanlistrik.ysn.di.module.about.AboutActivityModule
import com.tagihanlistrik.ysn.views.ui.about.AboutActivity
import dagger.Component

/**
 * Created by yudisetiawan on 1/15/18.
 */

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(AboutActivityModule::class))
interface AboutActivityComponent {

    fun inject(aboutActivity: AboutActivity)

}