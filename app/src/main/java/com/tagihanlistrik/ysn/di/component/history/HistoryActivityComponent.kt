package com.tagihanlistrik.ysn.di.component.history

import com.tagihanlistrik.ysn.di.ActivityScope
import com.tagihanlistrik.ysn.di.component.AppComponent
import com.tagihanlistrik.ysn.di.module.history.HistoryActivityModule
import com.tagihanlistrik.ysn.views.ui.history.HistoryActivity
import dagger.Component

/**
 * Created by yudisetiawan on 1/17/18.
 */

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(HistoryActivityModule::class))
interface HistoryActivityComponent {

    fun inject(historyActivity: HistoryActivity)

}