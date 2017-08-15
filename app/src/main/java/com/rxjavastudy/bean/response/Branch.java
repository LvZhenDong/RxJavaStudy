package com.rxjavastudy.bean.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/8/15.
 */

public class Branch {

    /**
     * name : master
     * commit : {"sha":"6dcb09b5b57875f334f61aebed695e2e4193db5e","url":"https://api.github.com/repos/octocat/Hello-World/commits/c5b97d5ae6c19d5c5df71a34c7fbeeda2479ccbc"}
     * protected : true
     * protection_url : https://api.github.com/repos/octocat/Hello-World/branches/master/protection
     */

    private String name;
    private CommitBean commit;
    @SerializedName("protected")
    private boolean protectedX;
    private String protection_url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CommitBean getCommit() {
        return commit;
    }

    public void setCommit(CommitBean commit) {
        this.commit = commit;
    }

    public boolean isProtectedX() {
        return protectedX;
    }

    public void setProtectedX(boolean protectedX) {
        this.protectedX = protectedX;
    }

    public String getProtection_url() {
        return protection_url;
    }

    public void setProtection_url(String protection_url) {
        this.protection_url = protection_url;
    }

    public static class CommitBean {
        /**
         * sha : 6dcb09b5b57875f334f61aebed695e2e4193db5e
         * url : https://api.github.com/repos/octocat/Hello-World/commits/c5b97d5ae6c19d5c5df71a34c7fbeeda2479ccbc
         */

        private String sha;
        private String url;

        public String getSha() {
            return sha;
        }

        public void setSha(String sha) {
            this.sha = sha;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
