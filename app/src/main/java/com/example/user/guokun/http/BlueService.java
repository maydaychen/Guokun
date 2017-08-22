package com.example.user.guokun.http;

import com.example.user.guokun.bean.ChairInfoBean;
import com.example.user.guokun.bean.ChairNearbyBean;
import com.example.user.guokun.bean.ChargeBean;
import com.example.user.guokun.bean.CheckInfoBean;
import com.example.user.guokun.bean.LoginBean;
import com.example.user.guokun.bean.ResultBean;
import com.example.user.guokun.bean.UserInfoBean;
import com.example.user.guokun.bean.VspaBean;

import org.json.JSONObject;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 作者：JTR on 2016/11/24 14:15
 * 邮箱：2091320109@qq.com
 */
public interface BlueService {
    @FormUrlEncoded
    @POST("/user/send.do")
    rx.Observable<ResultBean> send_code(@Field("mobile") String mobile);

    @FormUrlEncoded
    @POST("/user/login.do")
    rx.Observable<LoginBean> login(@Field("mobile") String mobile, @Field("code") String code);

    @FormUrlEncoded
    @POST("/user/info.do")
    rx.Observable<UserInfoBean> user_info(@Field("accessToken") String accessToken);

    @FormUrlEncoded
    @POST("/order/vspa/list.do")
    rx.Observable<VspaBean> vspa_order(@Field("accessToken") String accessToken,
                                       @Field("pageNum") int pageNum, @Field("pageSize") int pageSize);

    @FormUrlEncoded
    @POST("/massagechair/info.do")
    rx.Observable<ChairInfoBean> char_info(@Field("accessToken") String accessToken, @Field("mobile") String mobile);

    @FormUrlEncoded
    @POST("/massagechair/list.do")
    rx.Observable<ChairNearbyBean> char_nearby(@Field("accessToken") String accessToken, @Field("lng") String lng, @Field("lat") String lat,
                                               @Field("pageNum") int pageNum, @Field("pageSize") int pageSize);

    @FormUrlEncoded
    @POST("/recharge/list.do")
    rx.Observable<ChargeBean> charge(@Field("accessToken") String accessToken);

    @FormUrlEncoded
    @POST("/recharge/pay.do")
    rx.Observable<JSONObject> charge_pay(@Field("accessToken") String accessToken, @Field("payType") String payType, @Field("rechargeId") int rechargeId);

    @FormUrlEncoded
    @POST("/pay/index.do")
    rx.Observable<JSONObject> pay(@Field("accessToken") String accessToken, @Field("accountingId") int accountingId, @Field("mobile") String mobile,
                                  @Field("plat") String plat, @Field("payType") String payType);

    @FormUrlEncoded
    @POST("/pay/using.do")
    rx.Observable<CheckInfoBean> check_info(@Field("accessToken") String accessToken, @Field("outTradeNo") String outTradeNo);
}