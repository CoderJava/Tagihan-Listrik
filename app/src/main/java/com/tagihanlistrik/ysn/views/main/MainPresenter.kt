package com.tagihanlistrik.ysn.views.main

import android.content.Context
import com.tagihanlistrik.ysn.BuildConfig
import com.tagihanlistrik.ysn.api.bill.ApiBisaTopUp
import com.tagihanlistrik.ysn.db.SettingsDb
import com.tagihanlistrik.ysn.views.base.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by root on 06/09/17.
 */
class MainPresenter : MvpPresenter<MainView> {

    private val TAG = javaClass.simpleName
    private var mainView: MainView? =  null

    override fun onAttachView(mvpView: MainView) {
        mainView = mvpView
    }

    override fun onDetachView() {
        mainView = null
    }

    fun onLoadData(context: Context) {
        val settingsDb = SettingsDb(context = context)
        val runService by lazy {
            settingsDb.getSettings()?.get(key = "checkAutomatically")
        }
        val customerId by lazy {
            settingsDb.getSettings()?.get(key = "customerId")
        }
        mainView?.loadData(runService!!, customerId!!)
    }

    fun onCheckTheBill(customerId: String, phoneNumber: String) {
        // todo: do something in here
        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL_API_BISA_TOP_UP)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        val apiBisaTopUp = retrofit.create(ApiBisaTopUp::class.java)
        apiBisaTopUp.checkTheBill(
                product = BuildConfig.PRODUCT_PLN,
                phoneNumber = phoneNumber,
                nomorRekening = customerId
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            responseBody: ResponseBody ->

                        },
                        {
                            t: Throwable ->

                        },
                        {

                        }
                )
    }
}