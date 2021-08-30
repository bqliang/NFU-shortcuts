package com.bqliang.nfushortcuts.tools

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.activity.CallXiaobaiActivity
import com.bqliang.nfushortcuts.activity.LibraryCardActivity
import com.bqliang.nfushortcuts.activity.TempCaptivePortalLoginActivity
import com.bqliang.nfushortcuts.activity.TempHealthCodeActivity
import com.bqliang.nfushortcuts.model.Shortcut
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.URL


fun getIntent(shortcut: Shortcut) :Intent {

    val intent = Intent().setAction(Intent.ACTION_VIEW)

    when(shortcut){
        Shortcut.LIBRARY_CARD -> { return intent.setClass(MyApplication.context, LibraryCardActivity::class.java) }
        Shortcut.CAMPUS_BUS -> intent.data = Uri.parse("alipays://platformapi/startapp?appId=20000067&url=http://nfuedu.zftcloud.com/campusbus_index/ticket/index.html?chInfo=ch_share__chsub_CopyLink")
        Shortcut.ACCESS_CODE -> intent.data = Uri.parse("alipays://platformapi/startapp?appId=2021002142606387&page=pages%2Findex%2Findex&enbsv=0.2.2105171134.36&chInfo=ch_share__chsub_CopyLink")
        Shortcut.QUICK_SCAN_QRCODE -> { intent.data = Uri.parse("alipays://platformapi/startapp?appId=20000067&url=http://nfuedu.zftcloud.com/index/travel_record/scanCode/path/1?chInfo=ch_share__chsub_CopyLink") }
        Shortcut.CAPTIVE_PORTAL_LOGIN -> { intent.setClass(MyApplication.context, TempCaptivePortalLoginActivity::class.java) }
        Shortcut.FEED_DEVELOPER -> { intent.data = Uri.parse("alipays://platformapi/startapp?appId=20000067&url=https://qr.alipay.com/fkx18192oyczl2lnexuxud1") }
        Shortcut.KFC -> intent.data = Uri.parse("alipays://platformapi/startapp?appId=2018090361289233&page=preorderHome%2Fpages%2Fenter%2Fhome%2Findex&enbsv=0.2.2108231924.16&chInfo=ch_share__chsub_CopyLink")
        Shortcut.ALIPAY_CODE -> intent.data = Uri.parse("alipayqr://platformapi/startapp?appId=20000056")
        Shortcut.HEALTH_CODE -> { return intent.setClass(MyApplication.context, TempHealthCodeActivity::class.java) }
        Shortcut.CALL_XIAOBAI -> { return intent.setClass(MyApplication.context, CallXiaobaiActivity::class.java) }
        Shortcut.CAINIAO_GUOGUO -> intent.data = Uri.parse("taobao://huodong.m.taobao.com/act/snipcodeupdate.html?_ariver_appid=11509317&app=chrome&cpp=1&x_object_type=miniapp&query=from%3Dpinpai&suid=b76ebea2-58b3-46f1-a3d2-4986e54ba3e0&point=%7B%22from%22%3A%22h5%22%2C%22url%22%3A%22https%3A%2F%2Fg.alicdn.com%2Fminiapp-biz%2Ftemporary-huodong-huanduan%2F0.0.1%2Findex.html%22%2C%22h5_uid%22%3A%22xtJ2GVzgCSMCAQ6V0JG3lwUD%22%7D&spm=a21bpk.21128592.0.0&cv=null&sourceType=other&processByTRiver=true&bxsign=scdQhsaEFrgE0Ohl-470q_0vnHFFIPsYKjt62ovlXPvTTLA9rloD3ov8lSKERHnf3fBcpO1eC9zQkEuIHBAoomahrOG1QQbO8GKcZHFA0x-_H4&x_miniapp_id=11509317&un=0cb30115ab892e626b6c0986c1a65891&from=pinpai&short_name=h.f1gsxJq&sm=9a1cc4&page=pages%2Findex%2Findex&shareurl=true&share_crt_v=1&ut_sk=1.YFGv2Y9m%2Ff0DAKjLXArcnLu5_21646297_1630328949532.QRCodeAnti.NewMiniapp")
    }
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    return intent
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