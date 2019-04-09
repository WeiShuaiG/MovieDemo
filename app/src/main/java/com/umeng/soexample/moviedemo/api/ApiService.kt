package com.wd.demo.api

import com.wd.demo.bean.LoginBean
import com.wd.demo.bean.RegisterBean
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable


interface ApiService {
    /**
     * 登录
     */
    @POST(MyApi.LOGIN_URL)
    @FormUrlEncoded
    fun login(@FieldMap params: HashMap<String, String>): Observable<LoginBean>

    /**
     * 注册
     */
    @POST(MyApi.REGISTER_URL)
    @FormUrlEncoded
    fun reg(@FieldMap params: HashMap<String, String>): Observable<RegisterBean>

}