package com.tagihanlistrik.ysn.di.component.splashscreen

import com.tagihanlistrik.ysn.di.ActivityScope
import com.tagihanlistrik.ysn.di.component.AppComponent
import com.tagihanlistrik.ysn.di.module.splashscreen.SplashActivityModule
import com.tagihanlistrik.ysn.views.ui.splashscreen.SplashScreenActivity
import dagger.Component

/**
 * Created by yudisetiawan on 3/12/18.
 */

@ActivityScope
@Component(dependencies = [(AppComponent::class)], modules = [(SplashActivityModule::class)])
interface SplashActivityComponent {

    fun inject(splashScreenActivity: SplashScreenActivity)

}