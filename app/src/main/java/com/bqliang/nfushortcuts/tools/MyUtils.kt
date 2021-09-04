package com.bqliang.nfushortcuts.tools

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.activity.CallXiaobaiActivity
import com.bqliang.nfushortcuts.activity.LibraryCardActivity
import com.bqliang.nfushortcuts.activity.temp.CaiNiaoActivity
import com.bqliang.nfushortcuts.activity.temp.CaptivePortalLoginActivity
import com.bqliang.nfushortcuts.activity.temp.HealthCodeActivity
import com.bqliang.nfushortcuts.model.Shortcut
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.URL


fun getIntent(shortcut: Shortcut) :Intent {

    val uriStr = when(shortcut){
        Shortcut.LIBRARY_CARD -> "nfushortcuts://library_card"
        Shortcut.CAMPUS_BUS -> "alipays://platformapi/startapp?appId=20000067&url=http://nfuedu.zftcloud.com/campusbus_index/ticket/index.html?chInfo=ch_share__chsub_CopyLink"
        Shortcut.ACCESS_CODE -> "alipays://platformapi/startapp?appId=2021002142606387&page=pages%2Findex%2Findex&enbsv=0.2.2105171134.36&chInfo=ch_share__chsub_CopyLink"
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

fun loginWIFI(userId:String, password:String){

    var connection: HttpURLConnection? = null
    try {
        val response = StringBuffer()
        val url = URL("http://172.16.30.45:80/drcom/login")
        connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.connectTimeout = 3000
        connection.apply {
            setRequestProperty("Host","172.16.30.45")
            setRequestProperty("Connection","keep-alive")
            setRequestProperty("Accept","text/javascript, application/javascript, application/ecmascript, application/x-ecmascript, */*; q=0.01")
            setRequestProperty("DNT","1")
            setRequestProperty("X-Requested-With","XMLHttpRequeste")
            setRequestProperty("User-Agent","Mozilla/5.0 (Linux; Android 10; MI 9) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.81 Mobile Safari/537.36")
            setRequestProperty("Referer","http://172.16.30.45/a79.htm?isReback=1")
            setRequestProperty("Accept-Encoding","gzip, deflate")
            setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9,en-GB;q=0.8,en;q=0.7")
        }
        val output = DataOutputStream(connection.outputStream)
        output.writeBytes("callback=dr${System.currentTimeMillis()}&DDDDD=${userId}" +
                "&upass=${password}&0MKKey=123456&R1=0&R3=1&R6=1&para=00&v6ip=&_=${System.currentTimeMillis()}")


        val input = connection.inputStream

        val reader = BufferedReader(InputStreamReader(input))
        reader.use {
            reader.forEachLine {
                response.append(it)
            }
        }

//        Log.d("loginWIFI","网页源代码：" + response.toString())
//        Log.d("loginWIFI", "是否登录成功：" + response.toString().contains("Dr.COMWebLoginID_3.htm").toString())

        if(response.toString().contains("Dr.COMWebLoginID_3.htm")) MyApplication.context.resources.getString(R.string.login_successfully).showToast()
        else MyApplication.context.resources.getString(R.string.login_unsuccessfully).showToast()

    }catch (e:SocketTimeoutException){
        MyApplication.context.resources.getString(R.string.login_timeout).showToast(Toast.LENGTH_LONG)
    }finally {
        connection?.disconnect()
    }
}