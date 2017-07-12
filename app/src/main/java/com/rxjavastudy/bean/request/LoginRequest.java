package com.rxjavastudy.bean.request;

/**
 * Created by Administrator on 2017/7/3.
 */

public class LoginRequest {

    /**
     * areaId : string
     * customerId : 0
     * imToken : string
     * lastLoginAddress : string
     * loginName : string
     * password : string
     * registerChannel : string
     * rememberMe : true
     * status : string
     * xPoint : 0
     * yPoint : 0
     */

    private String areaId;
    private int customerId;
    private String imToken;
    private String lastLoginAddress;
    private String loginName;
    private String password;
    private String registerChannel;
    private boolean rememberMe;
    private String status;
    private int xPoint;
    private int yPoint;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getImToken() {
        return imToken;
    }

    public void setImToken(String imToken) {
        this.imToken = imToken;
    }

    public String getLastLoginAddress() {
        return lastLoginAddress;
    }

    public void setLastLoginAddress(String lastLoginAddress) {
        this.lastLoginAddress = lastLoginAddress;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegisterChannel() {
        return registerChannel;
    }

    public void setRegisterChannel(String registerChannel) {
        this.registerChannel = registerChannel;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getXPoint() {
        return xPoint;
    }

    public void setXPoint(int xPoint) {
        this.xPoint = xPoint;
    }

    public int getYPoint() {
        return yPoint;
    }

    public void setYPoint(int yPoint) {
        this.yPoint = yPoint;
    }
}
