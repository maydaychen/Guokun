package com.example.user.guokun.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 2017/8/15.
 */

public class LoginBean {
    /**
     * code : 1
     * data : {"accessToken":{"access_token":"747dbe436cddc2b14dc3f6464 173f0","add_time":"2017-08-08 15:46:18","expires_in":2592000000,"id":4,"refres h_token":"1271e04ae448791b7a64b037c3c25cd","update_time":"2017-08-12 15:33:42"},"head_imgurl":"","id":1001135,"insert_dt":"2017-08-08 13:57:10.0","m obile":"18651039625","nick_name":"18651039625","user_deposit":0,"user_scor e":0,"wx_sex":0}
     * mag : 登录成功
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
         * accessToken : {"access_token":"747dbe436cddc2b14dc3f6464 173f0","add_time":"2017-08-08 15:46:18","expires_in":2592000000,"id":4,"refres h_token":"1271e04ae448791b7a64b037c3c25cd","update_time":"2017-08-12 15:33:42"}
         * head_imgurl :
         * id : 1001135
         * insert_dt : 2017-08-08 13:57:10.0
         * m obile : 18651039625
         * nick_name : 18651039625
         * user_deposit : 0
         * user_scor e : 0
         * wx_sex : 0
         */

        private AccessTokenBean accessToken;
        private String head_imgurl;
        private int id;
        private String insert_dt;
        @SerializedName("m obile")
        private String _$MObile196; // FIXME check this code
        private String nick_name;
        private int user_deposit;
        @SerializedName("user_scor e")
        private int _$User_scorE192; // FIXME check this code
        private int wx_sex;

        public AccessTokenBean getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(AccessTokenBean accessToken) {
            this.accessToken = accessToken;
        }

        public String getHead_imgurl() {
            return head_imgurl;
        }

        public void setHead_imgurl(String head_imgurl) {
            this.head_imgurl = head_imgurl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getInsert_dt() {
            return insert_dt;
        }

        public void setInsert_dt(String insert_dt) {
            this.insert_dt = insert_dt;
        }

        public String get_$MObile196() {
            return _$MObile196;
        }

        public void set_$MObile196(String _$MObile196) {
            this._$MObile196 = _$MObile196;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public int getUser_deposit() {
            return user_deposit;
        }

        public void setUser_deposit(int user_deposit) {
            this.user_deposit = user_deposit;
        }

        public int get_$User_scorE192() {
            return _$User_scorE192;
        }

        public void set_$User_scorE192(int _$User_scorE192) {
            this._$User_scorE192 = _$User_scorE192;
        }

        public int getWx_sex() {
            return wx_sex;
        }

        public void setWx_sex(int wx_sex) {
            this.wx_sex = wx_sex;
        }

        public static class AccessTokenBean {
            /**
             * access_token : 747dbe436cddc2b14dc3f6464 173f0
             * add_time : 2017-08-08 15:46:18
             * expires_in : 2592000000
             * id : 4
             * refres h_token : 1271e04ae448791b7a64b037c3c25cd
             * update_time : 2017-08-12 15:33:42
             */

            private String access_token;
            private String add_time;
            private long expires_in;
            private int id;
            @SerializedName("refres h_token")
            private String _$RefresH_token22; // FIXME check this code
            private String update_time;

            public String getAccess_token() {
                return access_token;
            }

            public void setAccess_token(String access_token) {
                this.access_token = access_token;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public long getExpires_in() {
                return expires_in;
            }

            public void setExpires_in(long expires_in) {
                this.expires_in = expires_in;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String get_$RefresH_token22() {
                return _$RefresH_token22;
            }

            public void set_$RefresH_token22(String _$RefresH_token22) {
                this._$RefresH_token22 = _$RefresH_token22;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }
        }
    }
}
