package com.tagihanlistrik.ysn.views.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import com.tagihanlistrik.ysn.R
import com.tagihanlistrik.ysn.di.component.splashscreen.DaggerSplashActivityComponent
import com.tagihanlistrik.ysn.di.module.splashscreen.SplashActivityModule
import com.tagihanlistrik.ysn.views.base.BaseActivity
import com.tagihanlistrik.ysn.views.ui.main.MainActivity
import javax.inject.Inject

class SplashScreenActivity : BaseActivity(), SplashView {

    private val TAG = javaClass.simpleName

    @Inject
    lateinit var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        doSetTimer()
    }

    private fun doSetTimer() {
        presenter.onSetTimer()
    }

    override fun onError() {
        // TODO: do something in here if needed
    }

    override fun onActivityInject() {
        DaggerSplashActivityComponent.builder()
                .appComponent(getAppComponent())
                .splashActivityModule(SplashActivityModule())
                .build()
                .inject(this)
        presenter.attachView(this)
    }

    override fun setTimer() {
        val intentMainActivity = Intent(this, MainActivity::class.java)
        intentMainActivity.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intentMainActivity)
    }
}
