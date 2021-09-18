package com.bqliang.nfushortcuts.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.tools.SharedPreferencesUtil
import com.github.appintro.AppIntro2
import com.github.appintro.AppIntroFragment
import com.zackratos.ultimatebarx.ultimatebarx.navigationBar
import com.zackratos.ultimatebarx.ultimatebarx.statusBar

class IntroActivity : AppIntro2() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isColorTransitionsEnabled = true
        showStatusBar(true)
        statusBar { transparent() }
        navigationBar { transparent() }

        addSlide(
            AppIntroFragment.newInstance(
            title = getString(R.string.welcome),
            description = getString(R.string.touch_and_go),
            titleColor = Color.WHITE,
            descriptionColor = Color.WHITE,
            backgroundColor = Color.parseColor("#7d0000"),
            imageDrawable = R.drawable.intro_ic_nfu_shortcuts
        ))

        addSlide(
            AppIntroFragment.newInstance(
                title = getString(R.string.captive_portal_login),
                description = getString(R.string.onekey_login),
                titleColor = Color.WHITE,
                descriptionColor = Color.WHITE,
                backgroundColor = Color.parseColor("#653ab5"),
                imageDrawable = R.drawable.intro_ic_login
            ))

        addSlide(
            AppIntroFragment.newInstance(
                title = getString(R.string.qr_codes),
                description = getString(R.string.quick_open_code),
                titleColor = Color.WHITE,
                descriptionColor = Color.WHITE,
                backgroundColor = Color.parseColor("#2E7D32"),
                imageDrawable = R.drawable.intro_ic_qr_code
            ))

        addSlide(
            AppIntroFragment.newInstance(
                title = getString(R.string.and_more),
                description = getString(R.string.one_step_ahead),
                titleColor = Color.WHITE,
                descriptionColor = Color.WHITE,
                backgroundColor = Color.parseColor("#0277BD"),
                imageDrawable = R.drawable.intro_ic_more
            ))
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        close()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        close()
    }

    private fun close(){
        SharedPreferencesUtil.saveBoolean("isFirstStart", false)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}