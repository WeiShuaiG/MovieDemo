package com.wd.demo.mvp.model

import android.annotation.SuppressLint
import com.wd.demo.api.ApiService
import com.wd.demo.bean.LoginBean
import com.wd.demo.mvp.ModelCallback
import com.wd.demo.mvp.contract.LoginContract
import com.wd.demo.net.NetScheduler
import com.wd.demo.net.RetrofitManager
import java.util.function.Consumer


class LoginModel : LoginContract.ILoginModel {
    @SuppressLint("CheckResult")
    override fun login(params: HashMap<String, String>, modelCallback: ModelCallback<LoginBean>) {
        val subscribe: Any = RetrofitManager.instance.createSerivce(ApiService::class.java)
            .login(params)
            .compose(NetScheduler().compose())
            .subscribe(Consumer {
                modelCallback.success(it)
            })
    }

}