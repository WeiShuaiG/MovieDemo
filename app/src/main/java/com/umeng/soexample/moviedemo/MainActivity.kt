package com.umeng.soexample.moviedemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.ToastUtils
import com.umeng.soexample.moviedemo.utils.RsaCoder
import com.wd.demo.bean.LoginBean
import com.wd.demo.mvp.contract.LoginContract
import com.wd.demo.mvp.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener, LoginContract.ILoginView {
    var loginPresenter = LoginPresenter()
    override fun loginSuccess(loginBean: LoginBean) {
        ToastUtils.showShort(loginBean.message)
    }

    override fun error(msg: String) {
        ToastUtils.showShort(msg)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.login_btn -> login()


        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        login_btn.setOnClickListener(this)
        loginPresenter.attach(this)

    }

    fun login() {

        //        获取账号密码
        val phone = phone.text.toString().trim()
        val pwd = pwd.text.toString().trim()
//        密码加密
        val encryptByPublicKey = RsaCoder.encryptByPublicKey(pwd)

        var parmas = HashMap<String, String>()
        parmas.put("phone", phone)
        parmas.put("pwd", encryptByPublicKey)
        loginPresenter.login(parmas)

    }

    override fun onDestroy() {
        super.onDestroy()
        loginPresenter.detach()
    }
}