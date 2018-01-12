package com.tagihanlistrik.ysn.views.main

import android.os.Bundle
import com.tagihanlistrik.ysn.R
import com.tagihanlistrik.ysn.di.component.main.DaggerMainActivityComponent
import com.tagihanlistrik.ysn.di.module.main.MainActivityModule
import com.tagihanlistrik.ysn.views.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    val TAG = javaClass.simpleName

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onError() {
        // TODO: do something in here if needed
    }

    override fun onActivityInject() {
        DaggerMainActivityComponent.builder()
                .appComponent(getAppComponent())
                .mainActivityModule(MainActivityModule())
                .build()
                .inject(this)
        presenter.attachView(this)
    }

}
