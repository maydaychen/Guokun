package com.example.user.guokun.bean;

/**
 * Created by user on 2017/8/15.
 */

public class LoginBean {
    /**
     * status : 1
     * message : 请求成功
     * data : {"id":2,"add_time":"2017-09-13 10:39:12","update_time":"2017-09-13 11:53:48","last_time":"2017-09-13 11:33:17","nickname":"18651039625","mobile":"18651039625","sex":0,"imgurl":"","score":0,"deposit":0,"accessToken":{"id":2,"add_time":"2017-09-13 10:43:58","update_time":"2017-09-13 12:45:39","access_token":"4e3e72a23d46e0f2d31bada624ada39","expires_in":1507866779,"refresh_token":"bd547ce0573cfa72ccf7fdfc8cf1e9"}}
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
         * add_time : 2017-09-13 10:39:12
         * update_time : 2017-09-13 11:53:48
         * last_time : 2017-09-13 11:33:17
         * nickname : 18651039625
         * mobile : 18651039625
         * sex : 0
         * imgurl :
         * score : 0
         * deposit : 0
         * accessToken : {"id":2,"add_time":"2017-09-13 10:43:58","update_time":"2017-09-13 12:45:39","access_token":"4e3e72a23d46e0f2d31bada624ada39","expires_in":1507866779,"refresh_token":"bd547ce0573cfa72ccf7fdfc8cf1e9"}
         */

        private int id;
        private String add_time;
        private String update_time;
        private String last_time;
        private String nickname;
        private String mobile;
        private int sex;
        private String imgurl;
        private int score;
        private double deposit;

        public int getTraded() {
            return traded;
        }

        public void setTraded(int traded) {
            this.traded = traded;
        }

        private int traded;
        private AccessTokenBean accessToken;

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

        public String getLast_time() {
            return last_time;
        }

        public void setLast_time(String last_time) {
            this.last_time = last_time;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public double getDeposit() {
            return deposit;
        }

        public void setDeposit(double deposit) {
            this.deposit = deposit;
        }

        public AccessTokenBean getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(AccessTokenBean accessToken) {
            this.accessToken = accessToken;
        }

        public static class AccessTokenBean {
            /**
             * id : 2
             * add_time : 2017-09-13 10:43:58
             * update_time : 2017-09-13 12:45:39
             * access_token : 4e3e72a23d46e0f2d31bada624ada39
             * expires_in : 1507866779
             * refresh_token : bd547ce0573cfa72ccf7fdfc8cf1e9
             */

            private int id;
            private String add_time;
            private String update_time;
            private String access_token;
            private int expires_in;
            private String refresh_token;

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

            public String getAccess_token() {
                return access_token;
            }

            public void setAccess_token(String access_token) {
                this.access_token = access_token;
            }

            public int getExpires_in() {
                return expires_in;
            }

            public void setExpires_in(int expires_in) {
                this.expires_in = expires_in;
            }

            public String getRefresh_token() {
                return refresh_token;
            }

            public void setRefresh_token(String refresh_token) {
                this.refresh_token = refresh_token;
            }
        }
    }
}
