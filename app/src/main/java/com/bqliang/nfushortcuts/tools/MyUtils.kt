package com.bqliang.nfushortcuts.tools

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.model.Shortcut
import com.microsoft.appcenter.utils.HandlerUtils.runOnUiThread
import com.tencent.mmkv.MMKV
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import org.json.JSONObject
import java.io.IOException
import java.time.Duration

val mmkv by lazy { MMKV.defaultMMKV() }

fun getIntent(shortcut: Shortcut) :Intent {

    val uriStr = when(shortcut){
        Shortcut.LIBRARY_CARD -> "nfushortcuts://library_card"
        Shortcut.CAMPUS_BUS -> "alipays://platformapi/startapp?appId=20000067&url=http://nfuedu.zftcloud.com/campusbus_index/ticket/index.html?chInfo=ch_share__chsub_CopyLink"
        Shortcut.ACCESS_CODE -> "alipays://platformapi/startapp?appId=2021002142606387&page=pages%2Findex%2Findex"
        Shortcut.QUICK_SCAN_QRCODE -> "alipays://platformapi/startapp?appId=20000067&url=http://nfuedu.zftcloud.com/index/travel_record/scanCode/path/1?chInfo=ch_share__chsub_CopyLink"
        Shortcut.CAPTIVE_PORTAL_LOGIN -> "nfushortcuts://captive_portal_login"
        Shortcut.FEED_DEVELOPER -> "alipays://platformapi/startapp?appId=20000067&url=https://qr.alipay.com/fkx18192oyczl2lnexuxud1"
        Shortcut.KFC -> "nfushortcuts://kfc"
        Shortcut.ALIPAY_CODE -> "alipayqr://platformapi/startapp?appId=20000056"
        Shortcut.HEALTH_CODE -> "nfushortcuts://health_code"
        Shortcut.CALL_XIAOBAI -> "nfushortcuts://call_xiaobai"
        Shortcut.CAINIAO -> "nfushortcuts://cainiao"
    }

    return Intent().apply {
        action = Intent.ACTION_VIEW
        data = Uri.parse(uriStr)
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }
}


fun loginWIFI(){
    val id = mmkv.decodeString("id", null)
    val password = mmkv.decodeString("password", null)
    if ( id.isNullOrBlank() || password.isNullOrBlank()){
        R.string.error_id_or_pw.showToast(Toast.LENGTH_LONG)
        return
    }

    val TAG = "校园网登录"
    val time = System.currentTimeMillis()
    val client = OkHttpClient()
        .newBuilder()
        .connectTimeout(Duration.ofSeconds(4)) // 连接超时 4 秒
        .build()

    val httpUrl = "http://172.16.30.45/".toHttpUrl()
        .newBuilder()
        .addPathSegments("drcom/login")
        .addQueryParameter("callback", "dr$time")
        .addQueryParameter("DDDDD", id)
        .addQueryParameter("upass", password)
        .addQueryParameter("0MKKey", "123456")
        .addQueryParameter("R1", "0")
        .addQueryParameter("R3", "1")
        .addQueryParameter("R6", "1")
        .addQueryParameter("para", "00")
        .addQueryParameter("v6ip","")
        .addQueryParameter("_", (time - 1000).toString())
        .build()

    val request = Request.Builder()
        .url(httpUrl)
        .addHeader("Host", "172.16.30.45")
        .addHeader("Connection", "keep-alive")
        .addHeader("Accept", "text/javascript, application/javascript, application/ecmascript, application/x-ecmascript, */*; q=0.01")
        .addHeader("DNT", "1")
        .addHeader("X-Requested-With", "XMLHttpRequest")
        .addHeader("User-Agent", "Mozilla/5.0 (Linux; Android 11; M2012K11AC) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Mobile Safari/537.36")
        .addHeader("Referer", "http://172.16.30.45/a79.htm?isReback=1")
        .addHeader("Accept-Encoding", "gzip, deflate")
        .addHeader("Accept-Language", "en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7")
        .build()

    try {
        val response = client.newCall(request).execute()
        response.body?.string()?.parseResponse()
    }catch (e: IOException){
        R.string.login_timeout.showToast(Toast.LENGTH_LONG)
    }
}


fun String.parseResponse() {
    var msg = MyApplication.context.getString(R.string.unknown_error).format("")

    try {
        val jsonString = this
            // 把前后多余信息删掉，仅保留 json 信息
            .substring(this.indexOf('(') + 1, this.lastIndexOf(')'))
            // 因为 json 里面有两个 olno 键，所以把第一个 olno 改为 olno0 防止异常
            .replaceFirst("\"olno", "\"olno0")
        Log.d("校园网登录响应信息", jsonString)
        val json = JSONObject(jsonString)
        val result = json.getInt("result")


        if (result == 1){
            val userName = json.getString("NID")
            MyApplication.context.getString(R.string.login_successfully).showToast()
        }else if (result == 0){
            val errorCode = json.getString("msga")
            msg = when(errorCode){
                "userid error1" -> MyApplication.context.getString(R.string.account_no_exist).format(json.getString("uid"))
                "ldap auth error" -> MyApplication.context.getString(R.string.incorrect_password)
                else -> MyApplication.context.getString(R.string.unknown_error).format(errorCode)
            }
            msg.showToast(Toast.LENGTH_LONG)
        }
    }catch (e: Exception){
        runOnUiThread { msg.showToast(Toast.LENGTH_LONG) }
        e.printStackTrace()
    }
}