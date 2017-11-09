package com.example.user.guokun.http;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by user on 2017/9/29.
 */

public class HttpShopMethod {

    public static final String BASE_URL = "http://gk.guokunjiankangkeji.com";
    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private BlueService movieService;

    private HttpShopMethod() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).build();


        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(JsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        movieService = retrofit.create(BlueService.class);
    }

    private static class SingletonHolder {
        private static final HttpShopMethod INSTANCE = new HttpShopMethod();
    }

    //获取单例
    public static HttpShopMethod getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {
        @Override
        public T call(HttpResult<T> httpResult) {
            return httpResult.getOthers();
        }
    }


    public void get_token(Subscriber<JSONObject> subscriber, String apiname, String apipass) {
        movieService.index_info(apiname, apipass)
//                .map(new HttpResultFunc<>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void login(Subscriber<JSONObject> subscriber, String access_token, String mobile) {
        movieService.shop_login(access_token, mobile)
//                .map(new HttpResultFunc<>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void good_list(Subscriber<JSONObject> subscriber, String accessToken, String session) {
        movieService.good_list(accessToken, session)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void good_detail(Subscriber<JSONObject> subscriber, String accessToken, String session, String id) {
        movieService.good_detail(accessToken, session, id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void buy_now(Subscriber<JSONObject> subscriber, String accessToken, String session, String id, String total) {
        movieService.buy_now(accessToken, session, id, total)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void get_address(Subscriber<JSONObject> subscriber, String accessToken, String session) {
        movieService.get_address(accessToken, session)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void add_address(Subscriber<JSONObject> subscriber, String accessToken, String realname, String sessionkey, String mobile, String province, String city, String area, String address) {
        movieService.add_address(accessToken, realname, sessionkey, mobile, province, city, area, address)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void confirm_order(Subscriber<JSONObject> subscriber, String accessToken, String sessionkey, String goodsid, String addressid, String dispatchid) {
        movieService.confirm_order(accessToken, sessionkey, goodsid, addressid, dispatchid)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void pay(Subscriber<JSONObject> subscriber, String accessToken, String sessionkey, String ordersn, String type) {
        movieService.pay(accessToken, ordersn, sessionkey, type)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void orders(Subscriber<JSONObject> subscriber, String accessToken, String sessionkey, int page, String status) {
        movieService.orders(accessToken, sessionkey, page, status)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void change_order(Subscriber<JSONObject> subscriber, String accessToken, String reason, String sessionkey, String orderid, String type) {
        movieService.change_order(accessToken, reason, sessionkey, orderid, type)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
