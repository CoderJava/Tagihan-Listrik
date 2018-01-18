package com.tagihanlistrik.ysn.views.ui.history

import com.tagihanlistrik.ysn.views.base.mvp.BaseView
import com.tagihanlistrik.ysn.views.ui.history.adapter.AdapterBillHistory

/**
 * Created by yudisetiawan on 1/17/18.
 */

interface HistoryView: BaseView {

    fun loadData(adapterBillHistory: AdapterBillHistory)

    fun loadDataFailed(message: String)

}
