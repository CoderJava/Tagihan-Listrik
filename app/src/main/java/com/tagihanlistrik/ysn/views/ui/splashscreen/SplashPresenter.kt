package com.tagihanlistrik.ysn.views.ui.splashscreen

import com.tagihanlistrik.ysn.views.base.mvp.BasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by yudisetiawan on 3/12/18.
 */
class SplashPresenter @Inject constructor() : BasePresenter<SplashView>() {

    private val TAG = javaClass.simpleName

    fun onSetTimer() {
        Observable
                .just(true)
                .delay(1000 * 3, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            view?.setTimer()
                        }
                )
    }


}