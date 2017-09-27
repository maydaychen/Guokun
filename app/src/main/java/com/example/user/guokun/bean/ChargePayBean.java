package com.example.user.guokun.bean;

/**
 * Created by 译 on 2017/8/15.
 */

public class ChargePayBean {
    /**
     * status : 1
     * data : {"result":"alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_ id=2016082000297193&biz_content=%7B%22out_trade_no%22%3A%2220170 8121543035589%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PA Y%22%2C%22subject%22%3A%22%E4%BD%99%E9%A2%9D%E5%85%85%E5 %80%BC10.0%E5%85%83%22%2C%22total_amount%22%3A%2210.00%22%7 D&charset=utf-8&format=JSON&method=alipay.trade.app.pay&notify_url=11 &sign=UfYjGcWYje4bjYX%2BT5Ymor3YC817RPpF4QoAB%2FZ5rAjfFJna4oUfea Su3k6L2QjuC0HoP%2Fhn54UwORiLTK7uTepzYwPkn915RqcAKoyDdf4fnJoFk%2 FZNFFIxGTBJKjTkmwSiMkysLEFxtuqHXXmlTsZfuh2HctfhjgJZ3VXyw2oct6pMQiS %2B3%2Bc8WQz2bvDqNlBwQSWwoPJUJLvYC6AO9Hh0xjeg%2F9rtczZ7ZgI%2F 1Ntx%2BaWFMnVPnvLMH%2FZEl00tSTPlGEN1d%2BCnh3CJn41hmXds4dRJKCL F8PLbclXTE%2FF4R6Hqni%2FAFm%2BaQfAljXI4uSc6OqQwjjX5g3%2BHpRZZ2Q %3D%3D&sign_type=RSA2&timestamp=2017-08-12+15%3A43%3A03&versio n=1.0"}
     * message : 获取成功
     */

    private int status;
    private DataBean data;
    private String message;

    public int getCode() {
        return status;
    }

    public void setCode(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMag() {
        return message;
    }

    public void setMag(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * result : alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_ id=2016082000297193&biz_content=%7B%22out_trade_no%22%3A%2220170 8121543035589%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PA Y%22%2C%22subject%22%3A%22%E4%BD%99%E9%A2%9D%E5%85%85%E5 %80%BC10.0%E5%85%83%22%2C%22total_amount%22%3A%2210.00%22%7 D&charset=utf-8&format=JSON&method=alipay.trade.app.pay&notify_url=11 &sign=UfYjGcWYje4bjYX%2BT5Ymor3YC817RPpF4QoAB%2FZ5rAjfFJna4oUfea Su3k6L2QjuC0HoP%2Fhn54UwORiLTK7uTepzYwPkn915RqcAKoyDdf4fnJoFk%2 FZNFFIxGTBJKjTkmwSiMkysLEFxtuqHXXmlTsZfuh2HctfhjgJZ3VXyw2oct6pMQiS %2B3%2Bc8WQz2bvDqNlBwQSWwoPJUJLvYC6AO9Hh0xjeg%2F9rtczZ7ZgI%2F 1Ntx%2BaWFMnVPnvLMH%2FZEl00tSTPlGEN1d%2BCnh3CJn41hmXds4dRJKCL F8PLbclXTE%2FF4R6Hqni%2FAFm%2BaQfAljXI4uSc6OqQwjjX5g3%2BHpRZZ2Q %3D%3D&sign_type=RSA2&timestamp=2017-08-12+15%3A43%3A03&versio n=1.0
         */

        private String result;

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }
}
