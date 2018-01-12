package com.tagihanlistrik.ysn.views.base

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.tagihanlistrik.ysn.di.component.AppComponent
import com.tagihanlistrik.ysn.di.component.DaggerAppComponent
import com.tagihanlistrik.ysn.di.module.AppModule

/**
 * Created by yudisetiawan on 1/12/18.
 */
class App: Application() {

    companion object {
        @JvmStatic
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
        Fresco.initialize(this)
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}