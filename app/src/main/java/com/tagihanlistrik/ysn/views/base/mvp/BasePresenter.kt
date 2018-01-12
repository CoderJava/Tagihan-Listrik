package com.tagihanlistrik.ysn.views.base.mvp

import java.lang.ref.WeakReference

/**
 * Created by root on 06/09/17.
 */
open class BasePresenter<V : BaseView> : Presenter<V> {

    private var weakReference: WeakReference<V>? = null

    override fun attachView(view: V) {
        if (!isViewAttached) {
            weakReference = WeakReference(view)
            view.setPresenter(this)
        }
    }

    override fun detachView() {
        weakReference?.clear()
        weakReference = null
    }

    val view: V?
        get() = weakReference?.get()

    private val isViewAttached: Boolean
        get() = weakReference != null && weakReference!!.get() != null
}