package com.example.user.guokun.bean;

import java.util.List;

/**
 * Created by user on 2017/8/16.
 */

public class ChairInfoBean {
    /**
     * status : 1
     * message : 请求成功
     * data : {"id":4,"add_time":"2017-09-09 20:55:42","update_time":"2017-09-09 20:55:42","name":"898602b01315c0001822","mobile":"898602b01315c0001822","status":3,"lat":"39.915177","lng":"116.403851","is_valid":1,"device":{"id":3,"add_time":"2017-09-07 15:55:52","update_time":"2017-09-07 15:55:52","name":"按摩椅","type":1,"brand":"112131","model":"112131","pic":"images/2/2017/09/VRnktQqkORYrArLhihVi4RRAyO7A2V.jpg"},"costs":[{"id":4,"add_time":"2017-09-07 14:20:37","update_time":"2017-09-07 14:20:37","name":"1131","time_len":11,"price":11,"is_valid":1}]}
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
         * id : 4
         * add_time : 2017-09-09 20:55:42
         * update_time : 2017-09-09 20:55:42
         * name : 898602b01315c0001822
         * mobile : 898602b01315c0001822
         * status : 3
         * lat : 39.915177
         * lng : 116.403851
         * is_valid : 1
         * device : {"id":3,"add_time":"2017-09-07 15:55:52","update_time":"2017-09-07 15:55:52","name":"按摩椅","type":1,"brand":"112131","model":"112131","pic":"images/2/2017/09/VRnktQqkORYrArLhihVi4RRAyO7A2V.jpg"}
         * costs : [{"id":4,"add_time":"2017-09-07 14:20:37","update_time":"2017-09-07 14:20:37","name":"1131","time_len":11,"price":11,"is_valid":1}]
         */

        private int id;
        private String add_time;
        private String update_time;
        private String name;
        private String mobile;
        private int status;
        private String lat;
        private String lng;
        private int is_valid;
        private DeviceBean device;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public int getIs_valid() {
            return is_valid;
        }

        public void setIs_valid(int is_valid) {
            this.is_valid = is_valid;
        }

        public DeviceBean getDevice() {
            return device;
        }

        public void setDevice(DeviceBean device) {
            this.device = device;
        }

        public List<CostsBean> getCosts() {
            return costs;
        }

        public void setCosts(List<CostsBean> costs) {
            this.costs = costs;
        }

        public static class DeviceBean {
            /**
             * id : 3
             * add_time : 2017-09-07 15:55:52
             * update_time : 2017-09-07 15:55:52
             * name : 按摩椅
             * type : 1
             * brand : 112131
             * model : 112131
             * pic : images/2/2017/09/VRnktQqkORYrArLhihVi4RRAyO7A2V.jpg
             */

            private int id;
            private String add_time;
            private String update_time;
            private String name;
            private int type;
            private String brand;
            private String model;
            private String pic;

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

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
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
        }

        public static class CostsBean {
            /**
             * id : 4
             * add_time : 2017-09-07 14:20:37
             * update_time : 2017-09-07 14:20:37
             * name : 1131
             * time_len : 11
             * price : 11
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
