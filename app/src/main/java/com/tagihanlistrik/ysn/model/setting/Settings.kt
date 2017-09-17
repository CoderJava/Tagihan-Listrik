package com.tagihanlistrik.ysn.model.setting

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by root on 17/09/17.
 */
data class Settings (
        @PrimaryKey val id: Int,
        var checkAutomatically: String,
        var customerId: String
) : RealmObject()
