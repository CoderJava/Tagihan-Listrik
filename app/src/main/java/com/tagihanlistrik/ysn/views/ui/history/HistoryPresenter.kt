package com.tagihanlistrik.ysn.views.ui.history

import com.tagihanlistrik.ysn.db.dao.BillLocalDao
import com.tagihanlistrik.ysn.views.base.mvp.BasePresenter
import com.tagihanlistrik.ysn.views.ui.history.adapter.AdapterBillHistory
import javax.inject.Inject

/**
 * Created by yudisetiawan on 1/17/18.
 */
class HistoryPresenter @Inject constructor(val billLocalDao: BillLocalDao) : BasePresenter<HistoryView>() {

    private val TAG = javaClass.simpleName
    private lateinit var adapterBillHistory: AdapterBillHistory

    fun onLoadData() {
        try {
            val billsLocal = billLocalDao.getAllBillsLocal()
            adapterBillHistory = AdapterBillHistory(billsLocal)
            view?.loadData(adapterBillHistory = adapterBillHistory)
        } catch (e: Exception) {
            e.printStackTrace()
            view?.loadDataFailed(message = e.message!!)
        }
    }

}