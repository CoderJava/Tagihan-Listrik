package com.tagihanlistrik.ysn.views.ui.history

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.tagihanlistrik.ysn.R
import com.tagihanlistrik.ysn.di.component.history.DaggerHistoryActivityComponent
import com.tagihanlistrik.ysn.di.module.history.HistoryActivityModule
import com.tagihanlistrik.ysn.views.base.BaseActivity
import com.tagihanlistrik.ysn.views.ui.history.adapter.AdapterBillHistory
import kotlinx.android.synthetic.main.activity_history.*
import javax.inject.Inject

class HistoryActivity : BaseActivity(), HistoryView {

    private val TAG = javaClass.simpleName

    @Inject
    lateinit var presenter: HistoryPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        initToolbar()
        doLoadData()
    }

    private fun doLoadData() {
        presenter.onLoadData()
    }

    override fun loadData(adapterBillHistory: AdapterBillHistory) {
        if (adapterBillHistory.itemCount == 0) {
            showDataNotFound()
        } else {
            hideDataNotFound()
        }
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recycler_view_bill_history_activity_history.addItemDecoration(dividerItemDecoration)
        recycler_view_bill_history_activity_history.layoutManager = LinearLayoutManager(this)
        recycler_view_bill_history_activity_history.adapter = adapterBillHistory
    }

    override fun loadDataFailed(message: String) {
        showDataNotFound()
        showSnackbarMessage(message, Snackbar.LENGTH_LONG)
    }

    private fun showDataNotFound() {
        recycler_view_bill_history_activity_history.visibility = View.GONE
        linear_layout_container_data_not_found_activity_history.visibility = View.VISIBLE
    }

    private fun hideDataNotFound() {
        recycler_view_bill_history_activity_history.visibility = View.VISIBLE
        linear_layout_container_data_not_found_activity_history.visibility = View.GONE
    }

    private fun initToolbar() {
        toolbar_activity_history.title = getString(R.string.history_bill)
        setSupportActionBar(toolbar_activity_history)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean =
            item?.itemId.let {
                return when (it) {
                    android.R.id.home -> {
                        onBackPressed()
                        true
                    }
                    else -> {
                        super.onOptionsItemSelected(item)
                    }
                }
            }

    override fun onError() {
        // TODO: do something in here if needed
    }

    override fun onActivityInject() {
        DaggerHistoryActivityComponent.builder()
                .appComponent(getAppComponent())
                .historyActivityModule(HistoryActivityModule())
                .build()
                .inject(this)
        presenter.attachView(this)
    }

    fun Activity.showSnackbarMessage(message: CharSequence, duration: Int) {
        Snackbar.make(findViewById(android.R.id.content), message, duration)
                .show()
    }
}
