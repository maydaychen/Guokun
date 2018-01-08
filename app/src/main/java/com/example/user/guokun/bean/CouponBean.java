package com.example.user.guokun.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 2017/8/25.
 */

public class CouponBean {
    /**
     * status : 1
     * message : 请求成功
     * data : {"pageNum":1,"pageSize":10,"size":3,"pages":1,"total":3,"list":[{"id":1,"add_time":"2017-09-14 15:21:44","update_time":"2017-09-14 15:21:46","type":1,"name":"1121","money":11,"start_time":"2017-09-13 15:21:51","end_time":"2017-09-15 15:21:55","status":0},{"id":3,"add_time":"2017-09-14 15:22:23","update_time":"2017-09-14 15:22:27","type":1,"name":"121","money":1,"start_time":"2017-09-14 15:22:30","end_time":"2017-09-22 15:22:35","status":0},{"id":2,"add_time":"2017-09-14 15:22:05","update_time":"2017-09-14 15:22:07","type":1,"name":"12","money":11,"start_time":"2017-09-04 15:22:12","end_time":"2017-09-05 15:22:15","status":3}]}
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
         * size : 3
         * pages : 1
         * total : 3
         * list : [{"id":1,"add_time":"2017-09-14 15:21:44","update_time":"2017-09-14 15:21:46","type":1,"name":"1121","money":11,"start_time":"2017-09-13 15:21:51","end_time":"2017-09-15 15:21:55","status":0},{"id":3,"add_time":"2017-09-14 15:22:23","update_time":"2017-09-14 15:22:27","type":1,"name":"121","money":1,"start_time":"2017-09-14 15:22:30","end_time":"2017-09-22 15:22:35","status":0},{"id":2,"add_time":"2017-09-14 15:22:05","update_time":"2017-09-14 15:22:07","type":1,"name":"12","money":11,"start_time":"2017-09-04 15:22:12","end_time":"2017-09-05 15:22:15","status":3}]
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

        public static class ListBean implements Serializable{
            /**
             * id : 1
             * add_time : 2017-09-14 15:21:44
             * update_time : 2017-09-14 15:21:46
             * type : 1
             * name : 1121
             * money : 11
             * start_time : 2017-09-13 15:21:51
             * end_time : 2017-09-15 15:21:55
             * status : 0
             */

            private int id;
            private String add_time;
            private String update_time;
            private int type;
            private String name;
            private double money;
            private String start_time;
            private String end_time;
            private int status;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
                this.money = money;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
