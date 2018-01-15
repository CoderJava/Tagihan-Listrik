package com.tagihanlistrik.ysn.views.ui.about

import android.os.Bundle
import android.text.Html
import com.tagihanlistrik.ysn.BuildConfig
import com.tagihanlistrik.ysn.R
import com.tagihanlistrik.ysn.di.component.about.DaggerAboutActivityComponent
import com.tagihanlistrik.ysn.di.module.about.AboutActivityModule
import com.tagihanlistrik.ysn.views.base.BaseActivity
import kotlinx.android.synthetic.main.activity_about.*
import javax.inject.Inject

class AboutActivity : BaseActivity(), AboutView {

    private val TAG = javaClass.simpleName

    @Inject
    lateinit var presenter: AboutPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        initViews()
    }

    private fun initViews() {
        val copyright = "${getString(R.string.copyright_u00a9_2018)} <font color='#AFB42B'>YSN Studio</font>"
        text_view_about_developer_activity_about.text = Html.fromHtml(copyright)

        val versionName = BuildConfig.VERSION_NAME
        text_view_app_version_name_activity_about.text = versionName
    }

    override fun onError() {
        // TODO: do something in here if needed
    }

    override fun onActivityInject() {
        DaggerAboutActivityComponent.builder()
                .appComponent(getAppComponent())
                .aboutActivityModule(AboutActivityModule())
                .build()
                .inject(this)
        presenter.attachView(this)
    }
}
