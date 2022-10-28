package com.xiamenTourism.model;


public class TourismModel {


    public String UserName;
    public String Email;
    public String Password;
    public String PhoneNumber;
    public int Id;


    public TourismModel(int id, String UserName, String Email, String PhoneNumber, String Password) {
        Id = id;
        this.UserName = UserName;
        this.Email = Email;
        this.Password = Password;
        this.PhoneNumber = PhoneNumber;

    }

}
