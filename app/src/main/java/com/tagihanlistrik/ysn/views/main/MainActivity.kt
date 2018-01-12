package com.tagihanlistrik.ysn.views.main

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.tagihanlistrik.ysn.R
import com.tagihanlistrik.ysn.di.component.main.DaggerMainActivityComponent
import com.tagihanlistrik.ysn.di.module.main.MainActivityModule
import com.tagihanlistrik.ysn.model.bill.Bill
import com.tagihanlistrik.ysn.views.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView, View.OnClickListener {

    val TAG = javaClass.simpleName

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
        initListeners()
    }

    private fun showLoading() {
        edit_text_customer_id_activity_main.isEnabled = false
        edit_text_phone_number_activity_main.isEnabled = false
        button_check_the_bill_activity_main.visibility = View.GONE
        progress_bar_loading_activity_main.visibility = View.VISIBLE
    }

    private fun initListeners() {
        button_check_the_bill_activity_main.setOnClickListener(this)
        relative_layout_container_user_folder_activity_main.setOnClickListener(this)
    }

    private fun initToolbar() {
        toolbar_activity_main.title = getString(R.string.app_name)
        setSupportActionBar(toolbar_activity_main)
    }

    override fun onError() {
        // TODO: do something in here if needed
    }

    override fun onActivityInject() {
        DaggerMainActivityComponent.builder()
                .appComponent(getAppComponent())
                .mainActivityModule(MainActivityModule())
                .build()
                .inject(this)
        presenter.attachView(this)
    }

    override fun onClick(view: View?) {
        view?.id.let {
            when (it) {
                R.id.button_check_the_bill_activity_main -> {
                    val customerId = edit_text_customer_id_activity_main.text.toString()
                    val phoneNumber = edit_text_phone_number_activity_main.text.toString()
                    showLoading()
                    val viewFocus = currentFocus
                    if (view != null) {
                        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        inputMethodManager.hideSoftInputFromWindow(viewFocus.windowToken, 0)
                    }
                    presenter.onCheckTheBill(customerId = customerId, phoneNumber = phoneNumber)
                }
                R.id.relative_layout_container_user_folder_activity_main -> {
                    // TODO: do something in here
                }
                else -> {
                    /** nothing to do in here */
                }
            }
        }
    }

    override fun checkTheBill(bill: Bill) {
        hideLoading()
        val data = bill.data
        val bundleBill = Bundle()
        bundleBill.putParcelable("dataBill", data)
        Log.d(TAG, "data: $data")
        /*val billBottomSheetDialogFragment = BillBottomSheetDialogFragment()
        billBottomSheetDialogFragment.arguments = bundleBill
        billBottomSheetDialogFragment.show(supportFragmentManager, TAG)*/
    }

    private fun hideLoading() {
        edit_text_customer_id_activity_main.isEnabled = true
        edit_text_phone_number_activity_main.isEnabled = true
        button_check_the_bill_activity_main.visibility = View.VISIBLE
        progress_bar_loading_activity_main.visibility = View.GONE
    }

    override fun checkTheBillFailed(message: String?) {
        hideLoading()
        showDialogMessage(message = message)
    }

    private fun showDialogMessage(message: String?) {
        AlertDialog.Builder(this@MainActivity).let {
            it.setTitle("Info")
            it.setMessage(message)
            it.setPositiveButton("OK", { dialogInterface: DialogInterface?, _: Int ->
                dialogInterface?.dismiss()
            })
            it.create()
        }.show()
    }
}
