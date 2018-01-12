package com.tagihanlistrik.ysn.views.base.mvp

/**
 * Created by root on 06/09/17.
 */
interface BaseView {

    fun onError()

    fun setPresenter(presenter: BasePresenter<*>)

}