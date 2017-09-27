package com.example.user.guokun.bean;

import java.util.List;

/**
 * Created by user on 2017/9/26.
 */

public class LeaseDetailBean {
    /**
     * status : 1
     * message : 请求成功
     * data : {"id":2,"add_time":"2017-09-22 17:50:08","update_time":"2017-09-25 18:04:17","period":30,"price":0.01,"fee":10,"pledge":100,"start_time":"2017-09-22 17:50:00","end_time":"2017-11-21 17:49:50","costs":[{"id":6,"add_time":"2017-09-21 08:50:54","update_time":"2017-09-22 15:24:41","name":"缓解压力","time_len":1,"price":0.01,"is_valid":1}]}
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
         * id : 2
         * add_time : 2017-09-22 17:50:08
         * update_time : 2017-09-25 18:04:17
         * period : 30
         * price : 0.01
         * fee : 10
         * pledge : 100
         * start_time : 2017-09-22 17:50:00
         * end_time : 2017-11-21 17:49:50
         * costs : [{"id":6,"add_time":"2017-09-21 08:50:54","update_time":"2017-09-22 15:24:41","name":"缓解压力","time_len":1,"price":0.01,"is_valid":1}]
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
        private List<CostsBean> costs;

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

        public List<CostsBean> getCosts() {
            return costs;
        }

        public void setCosts(List<CostsBean> costs) {
            this.costs = costs;
        }

        public static class CostsBean {
            /**
             * id : 6
             * add_time : 2017-09-21 08:50:54
             * update_time : 2017-09-22 15:24:41
             * name : 缓解压力
             * time_len : 1
             * price : 0.01
             * is_valid : 1
             */

            private int id;
            private String add_time;
            private String update_time;
            private String name;
            private int time_len;
            private double price;
            private int is_valid;

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

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public int getIs_valid() {
                return is_valid;
            }

            public void setIs_valid(int is_valid) {
                this.is_valid = is_valid;
            }
        }
    }
}
