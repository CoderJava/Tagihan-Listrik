package com.tagihanlistrik.ysn.di.module.history

import com.tagihanlistrik.ysn.db.dao.BillLocalDao
import com.tagihanlistrik.ysn.di.ActivityScope
import com.tagihanlistrik.ysn.views.ui.history.HistoryPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by yudisetiawan on 1/17/18.
 */

@Module
class HistoryActivityModule {

    @Provides
    @ActivityScope
    internal fun provideHistoryPresenter(billLocalDao: BillLocalDao): HistoryPresenter {
        return HistoryPresenter(billLocalDao)
    }

}