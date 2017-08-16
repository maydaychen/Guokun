package com.example.user.guokun.bean;

import java.util.List;

/**
 * Created by user on 2017/8/16.
 */

public class ChairInfoBean {
    /**
     * code : 1
     * data : {"accountingList":[{"id":1034,"name":"微盛测试","prices":0.01,"time_len":6},{"id":1035,"name":"微盛测试","prices":6,"time_len":18},{"id":1036,"name":"微盛测试","prices":9,"time_len":30}],"id":10117,"isservice":1,"mobile":"898 602b6101740177983","name":"177983","payList":[{"balance":169.91,"name":"余 额支付","payType":0},{"name":"微信支付","payType":1},{"name":"支付宝支付","payType":2}]}
     * mag : 获取成功
     */

    private int code;
    private DataBean data;
    private String mag;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMag() {
        return mag;
    }

    public void setMag(String mag) {
        this.mag = mag;
    }

    public static class DataBean {
        /**
         * accountingList : [{"id":1034,"name":"微盛测试","prices":0.01,"time_len":6},{"id":1035,"name":"微盛测试","prices":6,"time_len":18},{"id":1036,"name":"微盛测试","prices":9,"time_len":30}]
         * id : 10117
         * isservice : 1
         * mobile : 898 602b6101740177983
         * name : 177983
         * payList : [{"balance":169.91,"name":"余 额支付","payType":0},{"name":"微信支付","payType":1},{"name":"支付宝支付","payType":2}]
         */

        private int id;
        private int isservice;
        private String mobile;
        private String name;
        private List<AccountingListBean> accountingList;
        private List<PayListBean> payList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsservice() {
            return isservice;
        }

        public void setIsservice(int isservice) {
            this.isservice = isservice;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<AccountingListBean> getAccountingList() {
            return accountingList;
        }

        public void setAccountingList(List<AccountingListBean> accountingList) {
            this.accountingList = accountingList;
        }

        public List<PayListBean> getPayList() {
            return payList;
        }

        public void setPayList(List<PayListBean> payList) {
            this.payList = payList;
        }

        public static class AccountingListBean {
            /**
             * id : 1034
             * name : 微盛测试
             * prices : 0.01
             * time_len : 6
             */

            private int id;
            private String name;
            private double prices;
            private int time_len;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public double getPrices() {
                return prices;
            }

            public void setPrices(double prices) {
                this.prices = prices;
            }

            public int getTime_len() {
                return time_len;
            }

            public void setTime_len(int time_len) {
                this.time_len = time_len;
            }
        }

        public static class PayListBean {
            /**
             * balance : 169.91
             * name : 余 额支付
             * payType : 0
             */

            private double balance;
            private String name;
            private int payType;

            public double getBalance() {
                return balance;
            }

            public void setBalance(double balance) {
                this.balance = balance;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getPayType() {
                return payType;
            }

            public void setPayType(int payType) {
                this.payType = payType;
            }
        }
    }
}
