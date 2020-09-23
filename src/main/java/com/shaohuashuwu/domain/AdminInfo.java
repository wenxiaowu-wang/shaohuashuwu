package com.shaohuashuwu.domain;

import java.io.Serializable;

public class AdminInfo implements Serializable {
    private String admin_id;        //管理员ID
    private String admin_password;  //管理员密码

    public AdminInfo() {
    }

    public AdminInfo(String admin_id, String admin_password) {
        this.admin_id = admin_id;
        this.admin_password = admin_password;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }

    @Override
    public String toString() {
        return "AdminInfo{" +
                "admin_id='" + admin_id + '\'' +
                ", admin_password='" + admin_password + '\'' +
                '}';
    }
}
