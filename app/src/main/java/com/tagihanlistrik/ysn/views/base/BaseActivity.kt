package com.tagihanlistrik.ysn.views.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tagihanlistrik.ysn.di.component.AppComponent
import com.tagihanlistrik.ysn.views.base.mvp.BasePresenter
import com.tagihanlistrik.ysn.views.base.mvp.BaseView

/**
 * Created by yudisetiawan on 1/12/18.
 */
abstract class BaseActivity : AppCompatActivity(), BaseView {

    private var presenter: BasePresenter<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onActivityInject()
    }

    protected abstract fun onActivityInject()

    fun getAppComponent(): AppComponent = App.appComponent

    override fun setPresenter(presenter: BasePresenter<*>) {
        this.presenter = presenter
    }

    override fun onStart() {
        super.onStart()
        // TODO: do something in here if needed
    }

    override fun onStop() {
        super.onStop()
        // TODO: do something in here if needed
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
        presenter = null
    }

}