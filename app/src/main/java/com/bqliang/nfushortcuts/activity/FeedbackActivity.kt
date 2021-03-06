package com.bqliang.nfushortcuts.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.bqliang.nfushortcuts.databinding.ActivityFeedbackBinding
import com.microsoft.appcenter.analytics.Analytics


class FeedbackActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFeedbackBinding

    companion object {
        const val MY_URL = "https://support.qq.com/product/343448?d-wx-push=1"
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedbackBinding.inflate(layoutInflater)
        Analytics.trackEvent("Open Feedback Page")
        setContentView(binding.root)

        val myWebViewClient: WebViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                super.shouldOverrideUrlLoading(view, request)
                val url = request?.url.toString()
                try {
                    if (url.startsWith("weixin://")) {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        view?.context?.startActivity(intent)
                        return true
                    }
                } catch (e: Exception) {
                    return false
                }
                view?.loadUrl(url)
                return true
            }
        }

        binding.webView.apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            webViewClient = myWebViewClient
            loadUrl(MY_URL)
        }
    }
}