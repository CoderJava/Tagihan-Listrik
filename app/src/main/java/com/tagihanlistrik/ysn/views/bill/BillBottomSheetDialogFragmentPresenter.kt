package com.tagihanlistrik.ysn.views.bill

import com.tagihanlistrik.ysn.views.base.MvpPresenter

/**
 * Created by yudisetiawan on 1/7/18.
 */
class BillBottomSheetDialogFragmentPresenter : MvpPresenter<BillBottomSheetDialogFragmentView>{

    private val TAG = javaClass.simpleName
    private var billBottomSheetDialogFragmentView: BillBottomSheetDialogFragmentView? = null

    override fun onAttachView(mvpView: BillBottomSheetDialogFragmentView) {
        billBottomSheetDialogFragmentView = mvpView
    }

    override fun onDetachView() {
        billBottomSheetDialogFragmentView = null
    }
}