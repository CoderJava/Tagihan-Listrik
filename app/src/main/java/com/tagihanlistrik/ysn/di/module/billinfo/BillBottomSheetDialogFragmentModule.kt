package com.tagihanlistrik.ysn.di.module.billinfo

import com.tagihanlistrik.ysn.db.dao.BillLocalDao
import com.tagihanlistrik.ysn.di.FragmentScope
import com.tagihanlistrik.ysn.views.ui.billinfo.BillPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by yudisetiawan on 1/14/18.
 */

@Module
class BillBottomSheetDialogFragmentModule {

    @Provides
    @FragmentScope
    internal fun providesBillPresenter(billLocalDao: BillLocalDao): BillPresenter {
        return BillPresenter(billLocalDao)
    }

}