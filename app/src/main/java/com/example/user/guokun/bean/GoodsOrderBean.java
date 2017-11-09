package com.example.user.guokun.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 2017/10/9.
 */

public class GoodsOrderBean implements Serializable{
    /**
     * statusCode : 1
     * result : [{"id":"12","uniacid":"2","openid":"ucb1cc5ac6c42c0f1e5dfe4c17d6f687f","agentid":"0","ordersn":"SH201709291458049781","price":"2999.00","goodsprice":"2999.00","discountprice":"0.00","status":"0","paytype":"0","transid":"","remark":"","addressid":"2","dispatchprice":"0.00","dispatchid":"6","createtime":"2017-09-29 14:58:04","dispatchtype":"0","carrier":"a:0:{}","refundid":"0","iscomment":"0","creditadd":"0","deleted":"0","userdeleted":"0","finishtime":0,"paytime":0,"expresscom":"","expresssn":"","express":"","sendtime":0,"fetchtime":"0","cash":"0","canceltime":null,"cancelpaytime":"0","refundtime":"0","isverify":"0","verified":"0","verifyopenid":"","verifycode":"0","verifytime":"0","verifystoreid":"0","deductprice":"0.00","deductcredit":"0","deductcredit2":"0.00","deductenough":"0.00","address":"a:12:{s:2:\"id\";s:1:\"2\";s:7:\"uniacid\";s:1:\"2\";s:6:\"openid\";s:33:\"ucb1cc5ac6c42c0f1e5dfe4c17d6f687f\";s:8:\"realname\";s:7:\"liyafei\";s:6:\"mobile\";s:11:\"12346545613\";s:8:\"province\";s:9:\"北京市\";s:4:\"city\";s:9:\"北京市\";s:4:\"area\";s:9:\"东城区\";s:7:\"address\";s:15:\"fdasfasd","goods":[{"total":"1","optionname":"","realprice":"2999.00","price":"2999.00","id":"1","title":"按摩椅1号","thumb":"gk.guokunjiankangkeji.com/attachment/images/2/2017/09/gp99tTN945Ngcvfec4XP0tLF4E9vG0.jpg","marketprice":null,"productprice":null,"optiontitle":null}],"statusstr":"待付款","canrefund":false}]
     */

    private int statusCode;
    private List<ResultBean> result;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 12
         * uniacid : 2
         * openid : ucb1cc5ac6c42c0f1e5dfe4c17d6f687f
         * agentid : 0
         * ordersn : SH201709291458049781
         * price : 2999.00
         * goodsprice : 2999.00
         * discountprice : 0.00
         * status : 0
         * paytype : 0
         * transid : 
         * remark : 
         * addressid : 2
         * dispatchprice : 0.00
         * dispatchid : 6
         * createtime : 2017-09-29 14:58:04
         * dispatchtype : 0
         * carrier : a:0:{}
         * refundid : 0
         * iscomment : 0
         * creditadd : 0
         * deleted : 0
         * userdeleted : 0
         * finishtime : 0
         * paytime : 0
         * expresscom : 
         * expresssn : 
         * express : 
         * sendtime : 0
         * fetchtime : 0
         * cash : 0
         * canceltime : null
         * cancelpaytime : 0
         * refundtime : 0
         * isverify : 0
         * verified : 0
         * verifyopenid : 
         * verifycode : 0
         * verifytime : 0
         * verifystoreid : 0
         * deductprice : 0.00
         * deductcredit : 0
         * deductcredit2 : 0.00
         * deductenough : 0.00
         * address : a:12:{s:2:"id";s:1:"2";s:7:"uniacid";s:1:"2";s:6:"openid";s:33:"ucb1cc5ac6c42c0f1e5dfe4c17d6f687f";s:8:"realname";s:7:"liyafei";s:6:"mobile";s:11:"12346545613";s:8:"province";s:9:"北京市";s:4:"city";s:9:"北京市";s:4:"area";s:9:"东城区";s:7:"address";s:15:"fdasfasd
         * goods : [{"total":"1","optionname":"","realprice":"2999.00","price":"2999.00","id":"1","title":"按摩椅1号","thumb":"gk.guokunjiankangkeji.com/attachment/images/2/2017/09/gp99tTN945Ngcvfec4XP0tLF4E9vG0.jpg","marketprice":null,"productprice":null,"optiontitle":null}]
         * statusstr : 待付款
         * canrefund : false
         */

        private String id;
        private String uniacid;
        private String openid;
        private String agentid;
        private String ordersn;
        private String price;
        private String goodsprice;
        private String discountprice;
        private String status;
        private String paytype;
        private String transid;
        private String remark;
        private String addressid;
        private String dispatchprice;
        private String dispatchid;
        private String createtime;
        private String dispatchtype;
        private String carrier;
        private String refundid;
        private String iscomment;
        private String creditadd;
        private String deleted;
        private String userdeleted;
        private String finishtime;
        private String paytime;
        private String expresscom;
        private String expresssn;
        private String express;
        private String sendtime;
        private String fetchtime;
        private String cash;
        private String canceltime;
        private String cancelpaytime;
        private String refundtime;
        private String isverify;
        private String verified;
        private String verifyopenid;
        private String verifycode;
        private String verifytime;
        private String verifystoreid;
        private String deductprice;
        private String deductcredit;
        private String deductcredit2;
        private String deductenough;
        private String address;
        private String statusstr;
        private boolean canrefund;
        private List<GoodsBean> goods;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUniacid() {
            return uniacid;
        }

        public void setUniacid(String uniacid) {
            this.uniacid = uniacid;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getAgentid() {
            return agentid;
        }

        public void setAgentid(String agentid) {
            this.agentid = agentid;
        }

        public String getOrdersn() {
            return ordersn;
        }

        public void setOrdersn(String ordersn) {
            this.ordersn = ordersn;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getGoodsprice() {
            return goodsprice;
        }

        public void setGoodsprice(String goodsprice) {
            this.goodsprice = goodsprice;
        }

        public String getDiscountprice() {
            return discountprice;
        }

        public void setDiscountprice(String discountprice) {
            this.discountprice = discountprice;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPaytype() {
            return paytype;
        }

        public void setPaytype(String paytype) {
            this.paytype = paytype;
        }

        public String getTransid() {
            return transid;
        }

        public void setTransid(String transid) {
            this.transid = transid;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getAddressid() {
            return addressid;
        }

        public void setAddressid(String addressid) {
            this.addressid = addressid;
        }

        public String getDispatchprice() {
            return dispatchprice;
        }

        public void setDispatchprice(String dispatchprice) {
            this.dispatchprice = dispatchprice;
        }

        public String getDispatchid() {
            return dispatchid;
        }

        public void setDispatchid(String dispatchid) {
            this.dispatchid = dispatchid;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getDispatchtype() {
            return dispatchtype;
        }

        public void setDispatchtype(String dispatchtype) {
            this.dispatchtype = dispatchtype;
        }

        public String getCarrier() {
            return carrier;
        }

        public void setCarrier(String carrier) {
            this.carrier = carrier;
        }

        public String getRefundid() {
            return refundid;
        }

        public void setRefundid(String refundid) {
            this.refundid = refundid;
        }

        public String getIscomment() {
            return iscomment;
        }

        public void setIscomment(String iscomment) {
            this.iscomment = iscomment;
        }

        public String getCreditadd() {
            return creditadd;
        }

        public void setCreditadd(String creditadd) {
            this.creditadd = creditadd;
        }

        public String getDeleted() {
            return deleted;
        }

        public void setDeleted(String deleted) {
            this.deleted = deleted;
        }

        public String getUserdeleted() {
            return userdeleted;
        }

        public void setUserdeleted(String userdeleted) {
            this.userdeleted = userdeleted;
        }

        public String getFinishtime() {
            return finishtime;
        }

        public void setFinishtime(String finishtime) {
            this.finishtime = finishtime;
        }

        public String getPaytime() {
            return paytime;
        }

        public void setPaytime(String paytime) {
            this.paytime = paytime;
        }

        public String getExpresscom() {
            return expresscom;
        }

        public void setExpresscom(String expresscom) {
            this.expresscom = expresscom;
        }

        public String getExpresssn() {
            return expresssn;
        }

        public void setExpresssn(String expresssn) {
            this.expresssn = expresssn;
        }

        public String getExpress() {
            return express;
        }

        public void setExpress(String express) {
            this.express = express;
        }

        public String getSendtime() {
            return sendtime;
        }

        public void setSendtime(String sendtime) {
            this.sendtime = sendtime;
        }

        public String getFetchtime() {
            return fetchtime;
        }

        public void setFetchtime(String fetchtime) {
            this.fetchtime = fetchtime;
        }

        public String getCash() {
            return cash;
        }

        public void setCash(String cash) {
            this.cash = cash;
        }

        public String getCanceltime() {
            return canceltime;
        }

        public void setCanceltime(String canceltime) {
            this.canceltime = canceltime;
        }

        public String getCancelpaytime() {
            return cancelpaytime;
        }

        public void setCancelpaytime(String cancelpaytime) {
            this.cancelpaytime = cancelpaytime;
        }

        public String getRefundtime() {
            return refundtime;
        }

        public void setRefundtime(String refundtime) {
            this.refundtime = refundtime;
        }

        public String getIsverify() {
            return isverify;
        }

        public void setIsverify(String isverify) {
            this.isverify = isverify;
        }

        public String getVerified() {
            return verified;
        }

        public void setVerified(String verified) {
            this.verified = verified;
        }

        public String getVerifyopenid() {
            return verifyopenid;
        }

        public void setVerifyopenid(String verifyopenid) {
            this.verifyopenid = verifyopenid;
        }

        public String getVerifycode() {
            return verifycode;
        }

        public void setVerifycode(String verifycode) {
            this.verifycode = verifycode;
        }

        public String getVerifytime() {
            return verifytime;
        }

        public void setVerifytime(String verifytime) {
            this.verifytime = verifytime;
        }

        public String getVerifystoreid() {
            return verifystoreid;
        }

        public void setVerifystoreid(String verifystoreid) {
            this.verifystoreid = verifystoreid;
        }

        public String getDeductprice() {
            return deductprice;
        }

        public void setDeductprice(String deductprice) {
            this.deductprice = deductprice;
        }

        public String getDeductcredit() {
            return deductcredit;
        }

        public void setDeductcredit(String deductcredit) {
            this.deductcredit = deductcredit;
        }

        public String getDeductcredit2() {
            return deductcredit2;
        }

        public void setDeductcredit2(String deductcredit2) {
            this.deductcredit2 = deductcredit2;
        }

        public String getDeductenough() {
            return deductenough;
        }

        public void setDeductenough(String deductenough) {
            this.deductenough = deductenough;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getStatusstr() {
            return statusstr;
        }

        public void setStatusstr(String statusstr) {
            this.statusstr = statusstr;
        }

        public boolean isCanrefund() {
            return canrefund;
        }

        public void setCanrefund(boolean canrefund) {
            this.canrefund = canrefund;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * total : 1
             * optionname : 
             * realprice : 2999.00
             * price : 2999.00
             * id : 1
             * title : 按摩椅1号
             * thumb : gk.guokunjiankangkeji.com/attachment/images/2/2017/09/gp99tTN945Ngcvfec4XP0tLF4E9vG0.jpg
             * marketprice : null
             * productprice : null
             * optiontitle : null
             */

            private String total;
            private String optionname;
            private String realprice;
            private String price;
            private String id;
            private String title;
            private String thumb;
            private String marketprice;
            private String productprice;
            private String optiontitle;

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getOptionname() {
                return optionname;
            }

            public void setOptionname(String optionname) {
                this.optionname = optionname;
            }

            public String getRealprice() {
                return realprice;
            }

            public void setRealprice(String realprice) {
                this.realprice = realprice;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getMarketprice() {
                return marketprice;
            }

            public void setMarketprice(String marketprice) {
                this.marketprice = marketprice;
            }

            public String getProductprice() {
                return productprice;
            }

            public void setProductprice(String productprice) {
                this.productprice = productprice;
            }

            public String getOptiontitle() {
                return optiontitle;
            }

            public void setOptiontitle(String optiontitle) {
                this.optiontitle = optiontitle;
            }
        }
    }
}
