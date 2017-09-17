package com.tagihanlistrik.ysn.db

import android.content.Context
import com.tagihanlistrik.ysn.model.setting.Settings
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults
import io.realm.exceptions.RealmMigrationNeededException
import kotlin.properties.Delegates

/**
 * Created by root on 17/09/17.
 */
class SettingsDb(context: Context) {

    private val TAG = javaClass.simpleName
    private var realm: Realm by Delegates.notNull()

    init {
        val realmConfiguration = RealmConfiguration.Builder(context)
                .build()
        Realm.setDefaultConfiguration(realmConfiguration)
        try {
            realm = Realm.getInstance(realmConfiguration)
        } catch (e: RealmMigrationNeededException) {
            e.printStackTrace()
        }
    }

    /** Save new data */
    fun saveSettings(checkAutomatically: String,
                            customerId: String): Boolean {
        val settings = Settings(
                id = (System.currentTimeMillis() / 1000).toInt(),
                checkAutomatically = checkAutomatically,
                customerId = customerId
        )
        realm.beginTransaction()
        realm.copyToRealm(settings)
        realm.commitTransaction()
        return true
    }

    /** Get data */
    fun getSettings(): Map<String, String>? {
        val data = HashMap<String, String>()
        val realmResults = realm
                .where(Settings::class.java)
                .findAll()
        if (realmResults.size > 0) {
            val checkAutomatically = realmResults[0].checkAutomatically
            val customerId = realmResults[0].customerId
            data.put(key = "checkAutomatically", value = checkAutomatically)
            data.put(key = "customerId", value = customerId)
            return data
        }
        return null
    }

    /** Update data */
    fun updateSettings(checkAutomatically: String, customerId: String): Boolean {
        realm.beginTransaction()
        val settings = realm
                .where(Settings::class.java)
                .findFirst()
        settings.checkAutomatically = checkAutomatically
        settings.customerId = customerId
        realm.commitTransaction()
        return true
    }

    /** Delete settings */
    fun deleteSettings(): Boolean {
        val realmResult = realm
                .where(Settings::class.java)
                .findAll()
        realm.beginTransaction()
        realmResult.removeAt(0)
        realmResult.clear()
        realm.commitTransaction()
        return true
    }

}