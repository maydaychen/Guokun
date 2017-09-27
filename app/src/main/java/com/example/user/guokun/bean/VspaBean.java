package com.example.user.guokun.bean;

import java.util.List;

/**
 * Created by user on 2017/8/15.
 */

public class VspaBean {
    /**
     * status : 1
     * message : 请求成功
     * data : {"pageNum":1,"pageSize":10,"size":6,"pages":1,"total":6,"list":[{"id":23,"order_no":"20170912203533453868","fee":0.01,"discounts":0,"time_len":0,"cost_name":"","pay_time":null}]}
     */

    private int status;
    private String message;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * pageNum : 1
         * pageSize : 10
         * size : 6
         * pages : 1
         * total : 6
         * list : [{"id":23,"order_no":"20170912203533453868","fee":0.01,"discounts":0,"time_len":0,"cost_name":"","pay_time":null}]
         */

        private int pageNum;
        private int pageSize;
        private int size;
        private int pages;
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

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
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
             * id : 23
             * order_no : 20170912203533453868
             * fee : 0.01
             * discounts : 0
             * time_len : 0
             * cost_name : 
             * pay_time : null
             */

            private int id;
            private String order_no;
            private double fee;
            private int discounts;
            private int time_len;
            private String cost_name;
            private String pay_time;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getOrder_no() {
                return order_no;
            }

            public void setOrder_no(String order_no) {
                this.order_no = order_no;
            }

            public double getFee() {
                return fee;
            }

            public void setFee(double fee) {
                this.fee = fee;
            }

            public int getDiscounts() {
                return discounts;
            }

            public void setDiscounts(int discounts) {
                this.discounts = discounts;
            }

            public int getTime_len() {
                return time_len;
            }

            public void setTime_len(int time_len) {
                this.time_len = time_len;
            }

            public String getCost_name() {
                return cost_name;
            }

            public void setCost_name(String cost_name) {
                this.cost_name = cost_name;
            }

            public String getPay_time() {
                return pay_time;
            }

            public void setPay_time(String pay_time) {
                this.pay_time = pay_time;
            }
        }
    }
}
