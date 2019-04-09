package com.wd.demo.mvp.presenter

import com.wd.demo.bean.RegisterBean
import com.wd.demo.mvp.ModelCallback
import com.wd.demo.mvp.contract.RegisterContract
import com.wd.demo.mvp.model.RegisterModel


class RegisterPresenter : RegisterContract.RegisterPresenter() {
    lateinit var registerModel: RegisterModel
    lateinit var iRegisterView: RegisterContract.IRegisterView
    fun attach(iRegisterView: RegisterContract.IRegisterView) {
        this.iRegisterView = iRegisterView
        registerModel = RegisterModel()
    }

    override fun reg(params: HashMap<String, String>) {
        registerModel.reg(params, object : ModelCallback<RegisterBean> {
            override fun success(t: RegisterBean) = iRegisterView.regSuccess(t)

            override fun failure(msg: String) {
                iRegisterView.error(msg)
            }

        })
    }

    fun detach() {
        if (iRegisterView != null) {
            iRegisterView == null
        }
    }

}