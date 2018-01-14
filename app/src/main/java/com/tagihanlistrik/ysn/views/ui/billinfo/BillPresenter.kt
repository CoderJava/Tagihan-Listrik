package com.tagihanlistrik.ysn.views.ui.billinfo

import com.tagihanlistrik.ysn.db.dao.BillLocalDao
import com.tagihanlistrik.ysn.db.entity.BillLocal
import com.tagihanlistrik.ysn.model.bill.Data
import com.tagihanlistrik.ysn.views.base.mvp.BasePresenter
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by yudisetiawan on 1/14/18.
 */
class BillPresenter @Inject constructor(private val billLocalDao: BillLocalDao) : BasePresenter<BillView>() {

    private val TAG = javaClass.simpleName

    fun onSaveDataBill(billData: Data) {
        val billLocal = BillLocal()
        billLocal.customerId = billData.customerId
        billLocal.customerName = billData.customerName
        billLocal.period = billData.period
        billLocal.bill = billData.amountTheBill
        billLocal.admin = billData.admin
        billLocal.paid = billData.amountPaid
        CompositeDisposable()
                .add(Observable.create { e: ObservableEmitter<Boolean> ->
                    val alreadyData = billLocalDao.getBillsLocalByCustomerIdAndPeriod(billLocal.customerId, billLocal.period)
                    if (alreadyData.isNotEmpty()) {
                        e.onNext(true)
                    } else {
                        billLocalDao.insertBillLocal(billLocal = billLocal)
                        e.onNext(false)
                    }
                }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { isUpdate: Boolean ->
                                    isUpdate.let {
                                        when (it) {
                                            true -> {
                                                view?.saveDataBill(true)
                                            }
                                            else -> {
                                                view?.saveDataBill(false)
                                            }
                                        }
                                    }
                                },
                                { t: Throwable ->
                                    view?.saveDataBillFailed(t.message!!)
                                }
                        )
                )
    }

}