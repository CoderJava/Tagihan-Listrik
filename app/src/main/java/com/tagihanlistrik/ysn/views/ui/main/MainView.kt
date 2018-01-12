package com.tagihanlistrik.ysn.views.ui.main

import com.tagihanlistrik.ysn.model.bill.Bill
import com.tagihanlistrik.ysn.views.base.mvp.BaseView

/**
 * Created by yudisetiawan on 1/12/18.
 */

interface MainView: BaseView {

    fun checkTheBill(bill: Bill) {}

    fun checkTheBillFailed(message: String?) {}

}