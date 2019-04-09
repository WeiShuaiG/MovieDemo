package com.wd.demo.mvp.contract


import com.wd.demo.bean.LoginBean
import com.wd.demo.mvp.ModelCallback


interface LoginContract {
    open abstract class LoginPresenter {
        abstract fun login(params: HashMap<String, String>)
    }

    interface ILoginModel {
        fun login(params: HashMap<String, String>, modelCallback: ModelCallback<LoginBean>)
    }

    interface ILoginView {
        fun loginSuccess(loginBean: LoginBean)
        fun error(msg: String)
    }
}