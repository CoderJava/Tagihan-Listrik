package com.tagihanlistrik.ysn.views.main

import android.content.Context
import com.tagihanlistrik.ysn.db.SettingsDb
import com.tagihanlistrik.ysn.views.base.MvpPresenter

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
        val settingsData = settingsDb.getSettings()
        if (settingsData != null) {
            // TODO: do something in here
        }
    }
}