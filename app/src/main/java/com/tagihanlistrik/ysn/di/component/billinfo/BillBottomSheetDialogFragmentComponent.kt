package com.tagihanlistrik.ysn.di.component.billinfo

import com.tagihanlistrik.ysn.di.FragmentScope
import com.tagihanlistrik.ysn.di.component.AppComponent
import com.tagihanlistrik.ysn.di.module.billinfo.BillBottomSheetDialogFragmentModule
import com.tagihanlistrik.ysn.views.ui.billinfo.BillBottomSheetDialogFragment
import dagger.Component

/**
 * Created by yudisetiawan on 1/14/18.
 */

@FragmentScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(BillBottomSheetDialogFragmentModule::class))
interface BillBottomSheetDialogFragmentComponent {

    fun inject(billBottomSheetDialogFragment: BillBottomSheetDialogFragment)

}
