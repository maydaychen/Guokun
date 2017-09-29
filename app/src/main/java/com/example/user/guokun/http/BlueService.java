package com.example.user.guokun.http;

import com.example.user.guokun.bean.ChairInfoBean;
import com.example.user.guokun.bean.ChairNearbyBean;
import com.example.user.guokun.bean.ChargeBean;
import com.example.user.guokun.bean.CheckInfoBean;
import com.example.user.guokun.bean.CouponBean;
import com.example.user.guokun.bean.LeaseBean;
import com.example.user.guokun.bean.LeaseDetailBean;
import com.example.user.guokun.bean.LoginBean;
import com.example.user.guokun.bean.ResultBean;
import com.example.user.guokun.bean.UserInfoBean;
import com.example.user.guokun.bean.VspaBean;

import org.json.JSONObject;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

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
    @POST("/vspa/order/list.do")
    rx.Observable<VspaBean> vspa_order(@Field("accessToken") String accessToken,
                                       @Field("pageNum") int pageNum, @Field("pageSize") int pageSize);

    @FormUrlEncoded
    @POST("/terminal/info.do")
    rx.Observable<ChairInfoBean> char_info(@Field("accessToken") String accessToken, @Field("mobile") String mobile);

    @FormUrlEncoded
    @POST("/shop/list.do")
    rx.Observable<ChairNearbyBean> char_nearby(@Field("accessToken") String accessToken, @Field("lng") String lng, @Field("lat") String lat,
                                               @Field("pageNum") int pageNum, @Field("pageSize") int pageSize);

    @FormUrlEncoded
    @POST("/recharge/list.do")
    rx.Observable<ChargeBean> charge(@Field("accessToken") String accessToken);

    @FormUrlEncoded
    @POST("/recharge/pay.do")
    rx.Observable<JSONObject> charge_pay(@Field("accessToken") String accessToken, @Field("payType") String payType, @Field("rechargeId") int rechargeId);

    @FormUrlEncoded
    @POST("/terminal/pay.do")
    rx.Observable<JSONObject> pay(@Field("accessToken") String accessToken, @Field("costId") int costId, @Field("mobile") String mobile,
                                  @Field("plat") String plat, @Field("payType") String payType, @Field("couponId") String couponId, @Field("psw") String psw);

    @FormUrlEncoded
    @POST("/terminal/using.do")
    rx.Observable<CheckInfoBean> check_info(@Field("accessToken") String accessToken, @Field("tradeNo") String outTradeNo);

    @FormUrlEncoded
    @POST("/coupon/pay/list.do")
    rx.Observable<CouponBean> useable_coupon(@Field("accessToken") String accessToken);

    @FormUrlEncoded
    @POST("/coupon/list.do")
    rx.Observable<CouponBean> all_coupon(@Field("accessToken") String accessToken,
                                         @Field("pageNum") int pageNum, @Field("pageSize") int pageSize);


    @FormUrlEncoded
    @POST("/user/setpas.do")
    rx.Observable<ResultBean> set_pass(@Field("accessToken") String accessToken, @Field("psw") String psw);

    @FormUrlEncoded
    @POST("/lease/list.do")
    rx.Observable<LeaseBean> lease_list(@Field("accessToken") String accessToken);

    @FormUrlEncoded
    @POST("/lease/info.do")
    rx.Observable<LeaseDetailBean> lease_info(@Field("accessToken") String accessToken, @Field("leaseId") int leaseId);

    @FormUrlEncoded
    @POST("/lease/start.do")
    rx.Observable<ResultBean> lease_start(@Field("accessToken") String accessToken, @Field("leaseId") int leaseId,
                                          @Field("costId") int costId);

    @FormUrlEncoded
    @POST("/lease/pay.do")
    rx.Observable<JSONObject> lease_pay(@Field("accessToken") String accessToken, @Field("leaseId") int leaseId,
                                        @Field("payType") String payType);


    //下面是商城
    @GET("/login/ApiLogin")
    rx.Observable<JSONObject> index_info(@Query("apiname") String apiname, @Query("apipass") String apipass);

    @Headers("addons: ewei_shop")
    @FormUrlEncoded
    @POST("/login")
    rx.Observable<JSONObject> shop_login(@Field("access_token") String access_token, @Field("mobile") String mobile);

    @Headers("addons: ewei_shop")
    @GET("/products")
    rx.Observable<JSONObject> good_list(@Query("access_token") String access_token, @Query("sessionkey") String sessionkey);

    @Headers("addons: ewei_shop")
    @GET("/products")
    rx.Observable<JSONObject> good_detail(@Query("access_token") String access_token, @Query("sessionkey") String sessionkey,
                                          @Query("id") String id);

    @Headers("addons: ewei_shop")
    @GET("/orders/confirm")
    rx.Observable<JSONObject> buy_now(@Query("access_token") String access_token, @Query("sessionkey") String sessionkey,
                                      @Query("goodsid") String goodsid, @Query("total") String total);

    @Headers("addons: ewei_shop")
    @GET("/addresses")
    rx.Observable<JSONObject> get_address(@Query("access_token") String access_token, @Query("sessionkey") String sessionkey);

    @Headers("addons: ewei_shop")
    @FormUrlEncoded
    @POST("/addresses")
    rx.Observable<JSONObject> add_address(@Field("access_token") String access_token, @Field("realname") String realname,
                                          @Field("sessionkey") String sessionkey, @Field("mobile") String mobile,
                                          @Field("province") String province, @Field("city") String city,
                                          @Field("area") String area, @Field("address") String address);

    @Headers("addons: ewei_shop")
    @FormUrlEncoded
    @POST("/orders/confirm")
    rx.Observable<JSONObject> confirm_order(@Field("access_token") String access_token, @Field("sessionkey") String sessionkey, @Field("goods") String goods,
                                            @Field("addressid") String addressid, @Field("dispatchid") String dispatchid);

    @Headers("addons: ewei_shop")
    @FormUrlEncoded
    @POST("/orders/payment")
    rx.Observable<JSONObject> pay(@Field("access_token") String access_token, @Field("ordersn") String ordersn,
                                  @Field("sessionkey") String sessionkey, @Field("type") String type);
}