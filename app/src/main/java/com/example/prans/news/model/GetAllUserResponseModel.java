package com.example.prans.news.model;

import java.util.List;

public class GetAllUserResponseModel {
    private String status;
    private List<UserModel> userModels;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<UserModel> getUserModels() {
        return userModels;
    }

    public void setUserModels(List<UserModel> userModels) {
        this.userModels = userModels;
    }
}
