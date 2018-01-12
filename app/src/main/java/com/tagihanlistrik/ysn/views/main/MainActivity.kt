package com.tagihanlistrik.ysn.views.main

import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.tagihanlistrik.ysn.R
import com.tagihanlistrik.ysn.model.bill.Bill
import com.tagihanlistrik.ysn.views.bill.BillBottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView, View.OnClickListener {

    private val TAG = javaClass.simpleName
    private var mainPresenter: MainPresenter? = null
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initPresenter()
        onAttachMvp()
        initToolbar()
        initListeners()
        doLoadData()
    }

    private fun initToolbar() {
        toolbar_activity_main.title = getString(R.string.app_name)
        setSupportActionBar(toolbar_activity_main)
    }

    private fun initListeners() {
        button_check_the_bill_activity_main
                .setOnClickListener(this)
        relative_layout_container_user_folder_activity_main
                .setOnClickListener(this)
    }

    private fun doLoadData() {
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please wait")
        progressDialog.setCancelable(false)
        mainPresenter?.onLoadData()
    }

    private fun initPresenter() {
        mainPresenter = MainPresenter()
    }

    override fun onResume() {
        onAttachMvp()
        super.onResume()
    }

    override fun onDestroy() {
        onDetachMvp()
        super.onDestroy()
    }

    override fun onAttachMvp() {
        mainPresenter?.onAttachView(this)
    }

    override fun onDetachMvp() {
        mainPresenter?.onDetachView()
    }

    override fun loadData() {
        // todo: do something in here (pending)
        /*if (runService.equals("enabled", true)) {
            // todo: do something in here (pending)
            *//*startService(Intent(this, ServiceTagihanListrik::class.java))*//*
        }

        if (!customerId.equals("not set", true) && runService.equals("enabled", true)) {
            edit_text_customer_id_activity_main.setText(customerId)
        }

        val bundle = intent.extras
        if (bundle != null) {
            val customerId = bundle.getString("customerId")
            edit_text_customer_id_activity_main.setText(customerId)
        }*/
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.button_check_the_bill_activity_main -> {
                val customerId = edit_text_customer_id_activity_main
                        .text
                        .toString()
                        .trim()
                val phoneNumber = edit_text_phone_number_activity_main
                        .text
                        .toString()
                        .trim()
                when {
                    customerId.isEmpty() -> Toast.makeText(
                            this@MainActivity,
                            "Customer ID is empty",
                            Toast.LENGTH_SHORT
                    ).show()
                    phoneNumber.isEmpty() -> Toast.makeText(
                            this@MainActivity,
                            "Phone Number is empty",
                            Toast.LENGTH_SHORT
                    ).show()
                    else -> {
                        showProgressDialog()
                        hideSoftKeyboard()
                        mainPresenter?.onCheckTheBill(
                                customerId = customerId,
                                phoneNumber = phoneNumber
                        )
                    }
                }
            }
            R.id.relative_layout_container_user_folder_activity_main -> {
                // todo: do something in here (pending)
            }
            else -> {
                /** nothing to do in here */
            }
        }
    }

    private fun hideSoftKeyboard() {
        val view = currentFocus
        if (view != null) {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }

    }

    private fun showProgressDialog() {
        progressDialog.show()
    }

    override fun checkTheBillFailed(message: String?) {
        dismissProgressDialog()
        showDialogMessage(message = message)
    }

    private fun showDialogMessage(message: String?) {
        val alertDialogBuilder = AlertDialog.Builder(this@MainActivity)
        val alertDialogMessage = alertDialogBuilder
                .setTitle("Info")
                .setMessage(message)
                .setPositiveButton("OK", { dialogInterface: DialogInterface?, _: Int ->
                    dialogInterface?.dismiss()
                })
                .create()
        alertDialogMessage.show()
    }

    override fun checkTheBill(bill: Bill) {
        dismissProgressDialog()
        val data = bill.data
        val bundleBill = Bundle()
        bundleBill.putParcelable("dataBill", data)
        val billBottomSheetDialogFragment = BillBottomSheetDialogFragment()
        billBottomSheetDialogFragment.arguments = bundleBill
        billBottomSheetDialogFragment.show(supportFragmentManager, TAG)
    }

    private fun dismissProgressDialog() {
        if (progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }
}
