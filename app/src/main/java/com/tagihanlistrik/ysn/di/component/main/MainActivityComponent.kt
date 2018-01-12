package com.tagihanlistrik.ysn.di.component.main

import com.tagihanlistrik.ysn.di.ActivityScope
import com.tagihanlistrik.ysn.di.component.AppComponent
import com.tagihanlistrik.ysn.di.module.main.MainActivityModule
import com.tagihanlistrik.ysn.views.main.MainActivity
import dagger.Component

/**
 * Created by yudisetiawan on 1/12/18.
 */

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(MainActivityModule::class))
interface MainActivityComponent {

    fun inject(mainActivity: MainActivity)

}