package com.example.user.guokun.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 2017/9/29.
 */

public class BuyNowBean implements Serializable{
    /**
     * statusCode : 1
     * result : {"orderGoods":[{"goodsid":"4","title":"竹享","weight":"1000.00","issendfree":"0","isnodiscount":"0","thumb":"http://duoyunjiav2.wshoto.com/attachment/images/2/2017/07/LwQEua5Wt2KBKOalLBou5eaXxauvOo.jpg","marketprice":"99.00","storeids":"","isverify":"1","deduct":"0.00","total":1}],"addressLists":[{"id":"40","uniacid":"2","openid":"ombk8wiE8F5XPNFtBEga5s2voS_E","realname":"2号","mobile":"18912393729","province":"西藏区","city":"拉萨市","area":"城关区","address":"巴尔库路天海大酒店(测试)","isdefault":"1","zipcode":"","deleted":"0"}],"defaultAddress":{"id":"40","uniacid":"2","openid":"ombk8wiE8F5XPNFtBEga5s2voS_E","realname":"2号","mobile":"18912393729","province":"西藏区","city":"拉萨市","area":"城关区","address":"巴尔库路天海大酒店(测试)","isdefault":"1","zipcode":"","deleted":"0"},"dispatches":[{"id":"1","uniacid":"2","dispatchname":"商家配送","dispatchtype":"0","displayorder":"0","firstprice":"0.00","secondprice":"0.00","firstweight":"1000","secondweight":"1000","express":"","areas":"","carriers":"a:0:{}","enabled":"1","price":10}],"memberDiscount":{"total":1,"goodsprice":99,"realprice":79.2,"deductprice":0,"discountprice":19.8},"shopSet":{"style":"default","name":"朵云家","img":"","logo":"","signimg":"","levelname":"普通会员","levelurl":"","bindmobile":1}}
     */

    private int statusCode;
    private ResultBean result;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable{
        /**
         * orderGoods : [{"goodsid":"4","title":"竹享","weight":"1000.00","issendfree":"0","isnodiscount":"0","thumb":"http://duoyunjiav2.wshoto.com/attachment/images/2/2017/07/LwQEua5Wt2KBKOalLBou5eaXxauvOo.jpg","marketprice":"99.00","storeids":"","isverify":"1","deduct":"0.00","total":1}]
         * addressLists : [{"id":"40","uniacid":"2","openid":"ombk8wiE8F5XPNFtBEga5s2voS_E","realname":"2号","mobile":"18912393729","province":"西藏区","city":"拉萨市","area":"城关区","address":"巴尔库路天海大酒店(测试)","isdefault":"1","zipcode":"","deleted":"0"}]
         * defaultAddress : {"id":"40","uniacid":"2","openid":"ombk8wiE8F5XPNFtBEga5s2voS_E","realname":"2号","mobile":"18912393729","province":"西藏区","city":"拉萨市","area":"城关区","address":"巴尔库路天海大酒店(测试)","isdefault":"1","zipcode":"","deleted":"0"}
         * dispatches : [{"id":"1","uniacid":"2","dispatchname":"商家配送","dispatchtype":"0","displayorder":"0","firstprice":"0.00","secondprice":"0.00","firstweight":"1000","secondweight":"1000","express":"","areas":"","carriers":"a:0:{}","enabled":"1","price":10}]
         * memberDiscount : {"total":1,"goodsprice":99,"realprice":79.2,"deductprice":0,"discountprice":19.8}
         * shopSet : {"style":"default","name":"朵云家","img":"","logo":"","signimg":"","levelname":"普通会员","levelurl":"","bindmobile":1}
         */

        private DefaultAddressBean defaultAddress;
        private MemberDiscountBean memberDiscount;
        private ShopSetBean shopSet;
        private List<OrderGoodsBean> orderGoods;
        private List<AddressListsBean> addressLists;
        private List<DispatchesBean> dispatches;

        public DefaultAddressBean getDefaultAddress() {
            return defaultAddress;
        }

        public void setDefaultAddress(DefaultAddressBean defaultAddress) {
            this.defaultAddress = defaultAddress;
        }

        public MemberDiscountBean getMemberDiscount() {
            return memberDiscount;
        }

        public void setMemberDiscount(MemberDiscountBean memberDiscount) {
            this.memberDiscount = memberDiscount;
        }

        public ShopSetBean getShopSet() {
            return shopSet;
        }

        public void setShopSet(ShopSetBean shopSet) {
            this.shopSet = shopSet;
        }

        public List<OrderGoodsBean> getOrderGoods() {
            return orderGoods;
        }

        public void setOrderGoods(List<OrderGoodsBean> orderGoods) {
            this.orderGoods = orderGoods;
        }

        public List<AddressListsBean> getAddressLists() {
            return addressLists;
        }

        public void setAddressLists(List<AddressListsBean> addressLists) {
            this.addressLists = addressLists;
        }

        public List<DispatchesBean> getDispatches() {
            return dispatches;
        }

        public void setDispatches(List<DispatchesBean> dispatches) {
            this.dispatches = dispatches;
        }

        public static class DefaultAddressBean implements Serializable{
            /**
             * id : 40
             * uniacid : 2
             * openid : ombk8wiE8F5XPNFtBEga5s2voS_E
             * realname : 2号
             * mobile : 18912393729
             * province : 西藏区
             * city : 拉萨市
             * area : 城关区
             * address : 巴尔库路天海大酒店(测试)
             * isdefault : 1
             * zipcode :
             * deleted : 0
             */

            private String id;
            private String uniacid;
            private String openid;
            private String realname;
            private String mobile;
            private String province;
            private String city;
            private String area;
            private String address;
            private String isdefault;
            private String zipcode;
            private String deleted;

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

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getIsdefault() {
                return isdefault;
            }

            public void setIsdefault(String isdefault) {
                this.isdefault = isdefault;
            }

            public String getZipcode() {
                return zipcode;
            }

            public void setZipcode(String zipcode) {
                this.zipcode = zipcode;
            }

            public String getDeleted() {
                return deleted;
            }

            public void setDeleted(String deleted) {
                this.deleted = deleted;
            }
        }

        public static class MemberDiscountBean implements Serializable{
            /**
             * total : 1
             * goodsprice : 99
             * realprice : 79.2
             * deductprice : 0
             * discountprice : 19.8
             */

            private int total;
            private int goodsprice;
            private double realprice;
            private int deductprice;
            private double discountprice;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getGoodsprice() {
                return goodsprice;
            }

            public void setGoodsprice(int goodsprice) {
                this.goodsprice = goodsprice;
            }

            public double getRealprice() {
                return realprice;
            }

            public void setRealprice(double realprice) {
                this.realprice = realprice;
            }

            public int getDeductprice() {
                return deductprice;
            }

            public void setDeductprice(int deductprice) {
                this.deductprice = deductprice;
            }

            public double getDiscountprice() {
                return discountprice;
            }

            public void setDiscountprice(double discountprice) {
                this.discountprice = discountprice;
            }
        }

        public static class ShopSetBean implements Serializable{
            /**
             * style : default
             * name : 朵云家
             * img :
             * logo :
             * signimg :
             * levelname : 普通会员
             * levelurl :
             * bindmobile : 1
             */

            private String style;
            private String name;
            private String img;
            private String logo;
            private String signimg;
            private String levelname;
            private String levelurl;
            private int bindmobile;

            public String getStyle() {
                return style;
            }

            public void setStyle(String style) {
                this.style = style;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getSignimg() {
                return signimg;
            }

            public void setSignimg(String signimg) {
                this.signimg = signimg;
            }

            public String getLevelname() {
                return levelname;
            }

            public void setLevelname(String levelname) {
                this.levelname = levelname;
            }

            public String getLevelurl() {
                return levelurl;
            }

            public void setLevelurl(String levelurl) {
                this.levelurl = levelurl;
            }

            public int getBindmobile() {
                return bindmobile;
            }

            public void setBindmobile(int bindmobile) {
                this.bindmobile = bindmobile;
            }
        }

        public static class OrderGoodsBean implements Serializable{
            /**
             * goodsid : 4
             * title : 竹享
             * weight : 1000.00
             * issendfree : 0
             * isnodiscount : 0
             * thumb : http://duoyunjiav2.wshoto.com/attachment/images/2/2017/07/LwQEua5Wt2KBKOalLBou5eaXxauvOo.jpg
             * marketprice : 99.00
             * storeids :
             * isverify : 1
             * deduct : 0.00
             * total : 1
             */

            private String goodsid;
            private String title;
            private String weight;
            private String issendfree;
            private String isnodiscount;
            private String thumb;
            private String marketprice;
            private String storeids;
            private String isverify;
            private String deduct;
            private int total;

            public String getGoodsid() {
                return goodsid;
            }

            public void setGoodsid(String goodsid) {
                this.goodsid = goodsid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getIssendfree() {
                return issendfree;
            }

            public void setIssendfree(String issendfree) {
                this.issendfree = issendfree;
            }

            public String getIsnodiscount() {
                return isnodiscount;
            }

            public void setIsnodiscount(String isnodiscount) {
                this.isnodiscount = isnodiscount;
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

            public String getStoreids() {
                return storeids;
            }

            public void setStoreids(String storeids) {
                this.storeids = storeids;
            }

            public String getIsverify() {
                return isverify;
            }

            public void setIsverify(String isverify) {
                this.isverify = isverify;
            }

            public String getDeduct() {
                return deduct;
            }

            public void setDeduct(String deduct) {
                this.deduct = deduct;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }
        }

        public static class AddressListsBean implements Serializable{
            /**
             * id : 40
             * uniacid : 2
             * openid : ombk8wiE8F5XPNFtBEga5s2voS_E
             * realname : 2号
             * mobile : 18912393729
             * province : 西藏区
             * city : 拉萨市
             * area : 城关区
             * address : 巴尔库路天海大酒店(测试)
             * isdefault : 1
             * zipcode :
             * deleted : 0
             */

            private String id;
            private String uniacid;
            private String openid;
            private String realname;
            private String mobile;
            private String province;
            private String city;
            private String area;
            private String address;
            private String isdefault;
            private String zipcode;
            private String deleted;

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

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getIsdefault() {
                return isdefault;
            }

            public void setIsdefault(String isdefault) {
                this.isdefault = isdefault;
            }

            public String getZipcode() {
                return zipcode;
            }

            public void setZipcode(String zipcode) {
                this.zipcode = zipcode;
            }

            public String getDeleted() {
                return deleted;
            }

            public void setDeleted(String deleted) {
                this.deleted = deleted;
            }
        }

        public static class DispatchesBean implements Serializable{
            /**
             * id : 1
             * uniacid : 2
             * dispatchname : 商家配送
             * dispatchtype : 0
             * displayorder : 0
             * firstprice : 0.00
             * secondprice : 0.00
             * firstweight : 1000
             * secondweight : 1000
             * express :
             * areas :
             * carriers : a:0:{}
             * enabled : 1
             * price : 10
             */

            private String id;
            private String uniacid;
            private String dispatchname;
            private String dispatchtype;
            private String displayorder;
            private String firstprice;
            private String secondprice;
            private String firstweight;
            private String secondweight;
            private String express;
            private String areas;
            private String carriers;
            private String enabled;
            private int price;

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

            public String getDispatchname() {
                return dispatchname;
            }

            public void setDispatchname(String dispatchname) {
                this.dispatchname = dispatchname;
            }

            public String getDispatchtype() {
                return dispatchtype;
            }

            public void setDispatchtype(String dispatchtype) {
                this.dispatchtype = dispatchtype;
            }

            public String getDisplayorder() {
                return displayorder;
            }

            public void setDisplayorder(String displayorder) {
                this.displayorder = displayorder;
            }

            public String getFirstprice() {
                return firstprice;
            }

            public void setFirstprice(String firstprice) {
                this.firstprice = firstprice;
            }

            public String getSecondprice() {
                return secondprice;
            }

            public void setSecondprice(String secondprice) {
                this.secondprice = secondprice;
            }

            public String getFirstweight() {
                return firstweight;
            }

            public void setFirstweight(String firstweight) {
                this.firstweight = firstweight;
            }

            public String getSecondweight() {
                return secondweight;
            }

            public void setSecondweight(String secondweight) {
                this.secondweight = secondweight;
            }

            public String getExpress() {
                return express;
            }

            public void setExpress(String express) {
                this.express = express;
            }

            public String getAreas() {
                return areas;
            }

            public void setAreas(String areas) {
                this.areas = areas;
            }

            public String getCarriers() {
                return carriers;
            }

            public void setCarriers(String carriers) {
                this.carriers = carriers;
            }

            public String getEnabled() {
                return enabled;
            }

            public void setEnabled(String enabled) {
                this.enabled = enabled;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }
        }
    }
}
