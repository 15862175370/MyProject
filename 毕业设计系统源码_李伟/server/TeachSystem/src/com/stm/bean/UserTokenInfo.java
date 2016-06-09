package com.stm.bean;

public class UserTokenInfo {
	 /**
     * code : 200
     * userId : a5a3db34c4e34796ac0d174090716a6c
     * token : Xv5DDSKXm0PTVeYND93Ck3JfwJgjwYMuvkGUg6qTY5D/E96SGgsPbgNI4NAWoHnNSACFI5X2sTSPrVuo4fs+qT0UGPBW+SC8u+MXDlt7/pa2NKr4vd0+Ed25Wo0v0n9Jt6018PgAbyQ=
     */

    private int code;
    private String userId;
    private String token;

    public void setCode(int code) {
        this.code = code;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getCode() {
        return code;
    }

    public String getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }

}
