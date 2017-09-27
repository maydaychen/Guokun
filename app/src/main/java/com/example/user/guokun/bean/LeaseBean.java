package com.example.user.guokun.bean;

import java.util.List;

/**
 * Created by user on 2017/9/26.
 */

public class LeaseBean {
    /**
     * status : 1
     * message : 请求成功
     * data : {"size":2,"list":[{"id":2,"add_time":"2017-09-22 17:50:08","update_time":"2017-09-25 18:04:17","period":30,"price":0.01,"fee":10,"pledge":100,"start_time":"2017-09-22 17:50:00","end_time":"2017-11-21 17:49:50","is_valid":1,"mobile":"898602b01315c0003530","model":"GL1006","pic":"images/2/2017/09/wrUK0lhuNhnlRggxezMxhenKOl9h6O.jpg","status":1}]}
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
         * size : 2
         * list : [{"id":2,"add_time":"2017-09-22 17:50:08","update_time":"2017-09-25 18:04:17","period":30,"price":0.01,"fee":10,"pledge":100,"start_time":"2017-09-22 17:50:00","end_time":"2017-11-21 17:49:50","is_valid":1,"mobile":"898602b01315c0003530","model":"GL1006","pic":"images/2/2017/09/wrUK0lhuNhnlRggxezMxhenKOl9h6O.jpg","status":1}]
         */

        private int size;
        private List<ListBean> list;

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 2
             * add_time : 2017-09-22 17:50:08
             * update_time : 2017-09-25 18:04:17
             * period : 30
             * price : 0.01
             * fee : 10
             * pledge : 100
             * start_time : 2017-09-22 17:50:00
             * end_time : 2017-11-21 17:49:50
             * is_valid : 1
             * mobile : 898602b01315c0003530
             * model : GL1006
             * pic : images/2/2017/09/wrUK0lhuNhnlRggxezMxhenKOl9h6O.jpg
             * status : 1
             */

            private int id;
            private String add_time;
            private String update_time;
            private int period;
            private double price;
            private int fee;
            private int pledge;
            private String start_time;
            private String end_time;
            private int is_valid;
            private String mobile;
            private String model;
            private String pic;
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

            public int getPeriod() {
                return period;
            }

            public void setPeriod(int period) {
                this.period = period;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public int getFee() {
                return fee;
            }

            public void setFee(int fee) {
                this.fee = fee;
            }

            public int getPledge() {
                return pledge;
            }

            public void setPledge(int pledge) {
                this.pledge = pledge;
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

            public int getIs_valid() {
                return is_valid;
            }

            public void setIs_valid(int is_valid) {
                this.is_valid = is_valid;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
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
