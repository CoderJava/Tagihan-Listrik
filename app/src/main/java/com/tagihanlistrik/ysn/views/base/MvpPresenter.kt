package com.tagihanlistrik.ysn.views.base

/**
 * Created by root on 06/09/17.
 */
interface MvpPresenter<in T: MvpView> {

    fun onAttachView(mvpView: T)

    fun onDetachView()

}