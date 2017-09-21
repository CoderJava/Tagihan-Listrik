package com.tagihanlistrik.ysn.views.main

import android.content.Context
import android.util.Log
import com.tagihanlistrik.ysn.BuildConfig
import com.tagihanlistrik.ysn.api.bill.ApiBisaTopUp
import com.tagihanlistrik.ysn.views.base.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by root on 06/09/17.
 */
class MainPresenter : MvpPresenter<MainView> {

    private val TAG = javaClass.simpleName
    private var mainView: MainView? = null

    override fun onAttachView(mvpView: MainView) {
        mainView = mvpView
    }

    override fun onDetachView() {
        mainView = null
    }

    fun onLoadData(context: Context) {
        /*val settingsDb = SettingsDb(context = context)
        val runService by lazy {
            settingsDb.getSettings()?.get(key = "checkAutomatically")
        }
        val customerId by lazy {
            settingsDb.getSettings()?.get(key = "customerId")
        }*/
        mainView?.loadData()
    }

    fun onCheckTheBill(customerId: String, phoneNumber: String) {
        /*val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.interceptors().add(Interceptor {
            chain: Interceptor.Chain? ->
            val newRequest = chain?.request()
                    ?.newBuilder()
                    ?.addHeader("X-Authorization", BuildConfig.API_KEY)
                    ?.addHeader("Accept", "application/json")
                    ?.addHeader("Content-Type", "application/json")
                    ?.build()
            chain?.proceed(newRequest)
        })*/
        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL_API_BISA_TOP_UP)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        val apiBisaTopUp = retrofit.create(ApiBisaTopUp::class.java)
        apiBisaTopUp.checkTheBill(
                phoneNumber = phoneNumber,
                nomorRekening = customerId
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { response: ResponseBody ->
                            val jsonObjectResponseBody = JSONObject(
                                    response.string()
                            )
                            // todo: do something in here
                        },
                        { throwable: Throwable ->
                            Log.d(TAG, "onError: " + throwable.message)
                            throwable.printStackTrace()
                            if (throwable is HttpException) {
                                val responseBodyError = throwable.response().errorBody()
                                val jsonObjectResponseBodyError = JSONObject(responseBodyError?.string())
                                if (jsonObjectResponseBodyError.has("error_message")) {
                                    // Error message because field required not fill it
                                    var errorMessage = "Error"
                                    val jsonObjectErrorMessage = jsonObjectResponseBodyError
                                            .getJSONObject("error_message")
                                    val iteratorKeyErrorMessage = jsonObjectErrorMessage.keys()
                                    while (iteratorKeyErrorMessage.hasNext()) {
                                        val keyErrorMessage = iteratorKeyErrorMessage.next()
                                        val jsonArrayErrorMessage = jsonObjectErrorMessage
                                                .getJSONArray(keyErrorMessage)
                                        repeat(jsonArrayErrorMessage.length()) { a ->
                                            errorMessage += "\n- " + jsonArrayErrorMessage[a]
                                        }
                                    }
                                    mainView?.checkTheBillFailed(message = errorMessage)
                                } else if (jsonObjectResponseBodyError.has("data")) {
                                    // Because, bill has been paid off or other
                                    val message = jsonObjectResponseBodyError.getString("message")
                                    if (message.contains("tagihan sudah terbayar", true)) {
                                        mainView?.checkTheBillFailed(message = "Tagihan belum tersedia")
                                    } else {
                                        mainView?.checkTheBillFailed(message = message)
                                    }
                                }
                            } else {
                                mainView?.checkTheBillFailed(message = throwable.message)
                            }
                        }
                )
    }

}