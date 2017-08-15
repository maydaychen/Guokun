package com.example.user.guokun.bean;

import java.util.List;

/**
 * Created by user on 2017/8/15.
 */

public class VspaBean {
    /**
     * code : 1
     * data : {"list":[{"accounting":{"id":1034,"name":"微盛测试","time_len":6},"business":{"address":"","id":100025},"device":{"id":1012,"name":"GL-600","picture":"material_201706171150739.jpg"},"id":"201708101653091421","is_type":0,"is_used":0,"massageChair":{"id":10069},"mobile":"898602b6101740177935","out_trade_no":"20170810165309707098","pay_date":"2017-08-10 16:53:09","pay_money":0.01,"plat":"ios","runcount":0,"spend_type":1,"status":1,"trade_status":"0","wechatUser":{"id":1000980}}],"pageNum":1,"pageSize":10,"pages":1,"size":9,"total":9}
     * mag : 获取VSPA数据成功
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
         * list : [{"accounting":{"id":1034,"name":"微盛测试","time_len":6},"business":{"address":"","id":100025},"device":{"id":1012,"name":"GL-600","picture":"material_201706171150739.jpg"},"id":"201708101653091421","is_type":0,"is_used":0,"massageChair":{"id":10069},"mobile":"898602b6101740177935","out_trade_no":"20170810165309707098","pay_date":"2017-08-10 16:53:09","pay_money":0.01,"plat":"ios","runcount":0,"spend_type":1,"status":1,"trade_status":"0","wechatUser":{"id":1000980}}]
         * pageNum : 1
         * pageSize : 10
         * pages : 1
         * size : 9
         * total : 9
         */

        private int pageNum;
        private int pageSize;
        private int pages;
        private int size;
        private int total;
        private List<ListBean> list;

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * accounting : {"id":1034,"name":"微盛测试","time_len":6}
             * business : {"address":"","id":100025}
             * device : {"id":1012,"name":"GL-600","picture":"material_201706171150739.jpg"}
             * id : 201708101653091421
             * is_type : 0
             * is_used : 0
             * massageChair : {"id":10069}
             * mobile : 898602b6101740177935
             * out_trade_no : 20170810165309707098
             * pay_date : 2017-08-10 16:53:09
             * pay_money : 0.01
             * plat : ios
             * runcount : 0
             * spend_type : 1
             * status : 1
             * trade_status : 0
             * wechatUser : {"id":1000980}
             */

            private AccountingBean accounting;
            private BusinessBean business;
            private DeviceBean device;
            private String id;
            private int is_type;
            private int is_used;
            private MassageChairBean massageChair;
            private String mobile;
            private String out_trade_no;
            private String pay_date;
            private double pay_money;
            private String plat;
            private int runcount;
            private int spend_type;
            private int status;
            private String trade_status;
            private WechatUserBean wechatUser;

            public AccountingBean getAccounting() {
                return accounting;
            }

            public void setAccounting(AccountingBean accounting) {
                this.accounting = accounting;
            }

            public BusinessBean getBusiness() {
                return business;
            }

            public void setBusiness(BusinessBean business) {
                this.business = business;
            }

            public DeviceBean getDevice() {
                return device;
            }

            public void setDevice(DeviceBean device) {
                this.device = device;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getIs_type() {
                return is_type;
            }

            public void setIs_type(int is_type) {
                this.is_type = is_type;
            }

            public int getIs_used() {
                return is_used;
            }

            public void setIs_used(int is_used) {
                this.is_used = is_used;
            }

            public MassageChairBean getMassageChair() {
                return massageChair;
            }

            public void setMassageChair(MassageChairBean massageChair) {
                this.massageChair = massageChair;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getOut_trade_no() {
                return out_trade_no;
            }

            public void setOut_trade_no(String out_trade_no) {
                this.out_trade_no = out_trade_no;
            }

            public String getPay_date() {
                return pay_date;
            }

            public void setPay_date(String pay_date) {
                this.pay_date = pay_date;
            }

            public double getPay_money() {
                return pay_money;
            }

            public void setPay_money(double pay_money) {
                this.pay_money = pay_money;
            }

            public String getPlat() {
                return plat;
            }

            public void setPlat(String plat) {
                this.plat = plat;
            }

            public int getRuncount() {
                return runcount;
            }

            public void setRuncount(int runcount) {
                this.runcount = runcount;
            }

            public int getSpend_type() {
                return spend_type;
            }

            public void setSpend_type(int spend_type) {
                this.spend_type = spend_type;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getTrade_status() {
                return trade_status;
            }

            public void setTrade_status(String trade_status) {
                this.trade_status = trade_status;
            }

            public WechatUserBean getWechatUser() {
                return wechatUser;
            }

            public void setWechatUser(WechatUserBean wechatUser) {
                this.wechatUser = wechatUser;
            }

            public static class AccountingBean {
                /**
                 * id : 1034
                 * name : 微盛测试
                 * time_len : 6
                 */

                private int id;
                private String name;
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

                public int getTime_len() {
                    return time_len;
                }

                public void setTime_len(int time_len) {
                    this.time_len = time_len;
                }
            }

            public static class BusinessBean {
                /**
                 * address :
                 * id : 100025
                 */

                private String address;
                private int id;

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }
            }

            public static class DeviceBean {
                /**
                 * id : 1012
                 * name : GL-600
                 * picture : material_201706171150739.jpg
                 */

                private int id;
                private String name;
                private String picture;

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

                public String getPicture() {
                    return picture;
                }

                public void setPicture(String picture) {
                    this.picture = picture;
                }
            }

            public static class MassageChairBean {
                /**
                 * id : 10069
                 */

                private int id;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }
            }

            public static class WechatUserBean {
                /**
                 * id : 1000980
                 */

                private int id;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }
            }
        }
    }
}
