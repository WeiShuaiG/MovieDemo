package com.wd.demo.mvp.presenter

import com.wd.demo.bean.LoginBean
import com.wd.demo.mvp.ModelCallback
import com.wd.demo.mvp.contract.LoginContract
import com.wd.demo.mvp.model.LoginModel


class LoginPresenter : LoginContract.LoginPresenter() {
    lateinit var loginModel: LoginModel
    lateinit var iLoginView: LoginContract.ILoginView
    /**
     * 绑定view
     */
    fun attach(iLoginView: LoginContract.ILoginView) {
        this.iLoginView = iLoginView
        loginModel = LoginModel()

    }

    override fun login(params: HashMap<String, String>) {
        loginModel.login(params, object : ModelCallback<LoginBean> {
            override fun success(t: LoginBean) {
                iLoginView.loginSuccess(t)
            }

            override fun failure(msg: String) {
                iLoginView.error(msg)
            }

        })

    }

    /**
     * 解绑
     */
    fun detach() {
        if (iLoginView != null) {
            iLoginView == null
        }
    }

}