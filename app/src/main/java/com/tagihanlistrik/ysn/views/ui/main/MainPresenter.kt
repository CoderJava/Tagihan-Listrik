package com.tagihanlistrik.ysn.views.ui.main

import com.tagihanlistrik.ysn.api.bill.Endpoints
import com.tagihanlistrik.ysn.model.bill.Bill
import com.tagihanlistrik.ysn.model.bill.Data
import com.tagihanlistrik.ysn.views.base.mvp.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import javax.inject.Inject

/**
 * Created by yudisetiawan on 1/12/18.
 */
class MainPresenter @Inject constructor(var api: Endpoints) : BasePresenter<MainView>() {

    private val TAG = javaClass.simpleName

    fun onCheckTheBill(customerId: String, phoneNumber: String) {
        when {
            customerId.trim().isEmpty() -> view?.checkTheBillFailed("Customer ID is empty")
            phoneNumber.trim().isEmpty() -> view?.checkTheBillFailed("Phone Number is empty")
            else -> {
                api.checkTheBill(customerId = customerId, phoneNumber = phoneNumber)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {
                                    responseBody: ResponseBody ->
                                    val jsonObjectResponseBody = JSONObject(responseBody.string())
                                    val error = jsonObjectResponseBody.getBoolean("error")
                                    val message = jsonObjectResponseBody.getString("message")
                                    when (error) {
                                        true -> {
                                            view?.checkTheBillFailed(message)
                                        }
                                        else -> {
                                            val jsonObjectData = jsonObjectResponseBody.getJSONObject("data")
                                            val customerId = jsonObjectData.getString("no_pelanggan")
                                            val productName = jsonObjectData.getString("product_name")
                                            val amountTheBill = jsonObjectData.getInt("jumlah_tagihan")
                                            val amountPaid = jsonObjectData.getInt("jumlah_bayar")
                                            val admin = jsonObjectData.getInt("admin")
                                            val paidOff: Int
                                            if (jsonObjectData.isNull("terbayar")) {
                                                paidOff = 0
                                            } else {
                                                paidOff = jsonObjectData.getInt("terbayar")
                                            }
                                            val customerName = jsonObjectData.getString("nama")
                                            val period = jsonObjectData.getString("periode")
                                            val billId = jsonObjectData.getInt("tagihan_id")
                                            val data = Data(
                                                    customerId = customerId,
                                                    productName = productName,
                                                    amountTheBill = amountTheBill,
                                                    amountPaid = amountPaid,
                                                    admin = admin,
                                                    paidOff = paidOff,
                                                    customerName = customerName,
                                                    period = period,
                                                    billId = billId
                                            )
                                            val bill = Bill(
                                                    error = error,
                                                    message = message,
                                                    data = data
                                            )
                                            view?.checkTheBill(bill)
                                        }
                                    }
                                },
                                {
                                    throwable: Throwable ->
                                    throwable.printStackTrace()
                                    if (throwable is HttpException) {
                                        val responseBodyError = throwable.response().errorBody()
                                        val jsonObjectResponseBodyError = JSONObject(responseBodyError?.string())
                                        if (jsonObjectResponseBodyError.has("error_message")) {
                                            var errorMessage = "Error"
                                            val jsonObjectErrorMessage = jsonObjectResponseBodyError
                                                    .getJSONObject("error_message")
                                            val iteratorKeyErrorMessage = jsonObjectErrorMessage.keys()
                                            while (iteratorKeyErrorMessage.hasNext()) {
                                                val keyErrorMessage = iteratorKeyErrorMessage.next()
                                                val jsonArrayErrorMessage = jsonObjectErrorMessage
                                                        .getJSONArray(keyErrorMessage)
                                                repeat(jsonArrayErrorMessage.length()) { a ->
                                                    errorMessage += "\n-" + jsonArrayErrorMessage[a]
                                                }
                                            }
                                            view?.checkTheBillFailed(message = errorMessage)
                                        } else if (jsonObjectResponseBodyError.has("data")) {
                                            jsonObjectResponseBodyError.getString("message").let {
                                                when {
                                                    it.contains("tagihan sudah terbayar", true) ->
                                                        view?.checkTheBillFailed(message = "Tagihan belum tersedia")
                                                    else ->
                                                        view?.checkTheBillFailed(message = it)
                                                }
                                            }
                                        }
                                    } else {
                                        view?.checkTheBillFailed(message = throwable.message)
                                    }
                                }
                        )
            }
        }
    }

}