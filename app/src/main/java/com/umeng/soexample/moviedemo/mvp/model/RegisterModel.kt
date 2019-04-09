package com.wd.demo.mvp.model

import android.annotation.SuppressLint
import com.wd.demo.api.ApiService
import com.wd.demo.bean.RegisterBean
import com.wd.demo.mvp.ModelCallback
import com.wd.demo.mvp.contract.RegisterContract
import com.wd.demo.net.NetScheduler
import com.wd.demo.net.RetrofitManager
import java.util.function.Consumer


class RegisterModel : RegisterContract.IRegisterModel {
    @SuppressLint("CheckResult")
    override fun reg(params: HashMap<String, String>, modelCallback: ModelCallback<RegisterBean>) {
        val subscribe: Any = RetrofitManager.instance.createSerivce(ApiService::class.java)
            .reg(params)
            .compose(NetScheduler().compose())
            .subscribe(Consumer(modelCallback::success))
    }

}