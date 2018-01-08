package com.example.user.guokun.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 译 on 2017/8/17.
 */

public class ChairNearbyBean {
    /**
     * status : 1
     * data : {"list":[{"distance_um":1646,"id":10123,"latitude":"26.011","longitude":"119.011","name":"怡和出厂001"}],"pageNum":1,"pageSize":10,"pages":1,"size":4,"total":4}
     * message : 获取附 近设备数据成功
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
         * list : [{"distance_um":1646,"id":10123,"latitude":"26.011","longitude":"119.011","name":"怡和出厂001"}]
         * pageNum : 1
         * pageSize : 10
         * pages : 1
         * size : 4
         * total : 4
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

        public static class ListBean implements Serializable {
            /**
             * distance_um : 1646
             * id : 10123
             * latitude : 26.011
             * longitude : 119.011
             * name : 怡和出厂001
             */
            private String address;
            private int distance;
            private int id;
            private String lat;
            private String lng;
            private String name;

            public int getDistance_um() {
                return distance;
            }

            public void setDistance_um(int distance_um) {
                this.distance = distance_um;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLatitude() {
                return lat;
            }

            public void setLatitude(String lat) {
                this.lat = lat;
            }

            public String getLongitude() {
                return lng;
            }

            public void setLongitude(String lng) {
                this.lng = lng;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
        }
    }
}
