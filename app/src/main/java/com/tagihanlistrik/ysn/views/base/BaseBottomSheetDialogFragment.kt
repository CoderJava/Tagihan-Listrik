package com.tagihanlistrik.ysn.views.base

import android.annotation.SuppressLint
import android.app.Dialog
import android.support.design.widget.BottomSheetDialogFragment
import com.tagihanlistrik.ysn.di.component.AppComponent
import com.tagihanlistrik.ysn.views.base.mvp.BasePresenter
import com.tagihanlistrik.ysn.views.base.mvp.BaseView

/**
 * Created by yudisetiawan on 1/14/18.
 */

abstract class BaseBottomSheetDialogFragment : BottomSheetDialogFragment(), BaseView {

    private var presenter: BasePresenter<*>? = null

    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog?, style: Int) {
        super.setupDialog(dialog, style)
        onBottomSheetDialogFragmentInject()
    }

    protected abstract fun onBottomSheetDialogFragmentInject()

    fun getAppComponent(): AppComponent = App.appComponent

    override fun setPresenter(presenter: BasePresenter<*>) {
        this.presenter = presenter
    }

    override fun onStart() {
        super.onStart()
        // TODO: do something in here if needed
    }

    override fun onStop() {
        super.onStop()
        // TODO: do something in here if needed
    }

    override fun onDestroy() {
        super.onDestroy()
        // TODO: do something in here if needed
        presenter?.detachView()
        presenter = null
    }

}