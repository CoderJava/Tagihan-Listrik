package com.tagihanlistrik.ysn.views.main

import com.tagihanlistrik.ysn.views.base.MvpView

/**
 * Created by root on 06/09/17.
 */
interface MainView : MvpView {

    fun loadData()

    fun checkTheBillFailed(message: String?)

    fun checkTheBill()

}