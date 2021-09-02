package co.com.ceiba.mobile.pruebadeingreso.pojo;

import com.google.gson.annotations.SerializedName;

public class User {
    public User() {
    }

    public User(int id, String name, String email, String phone, String userName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.userName = userName;
    }

    @SerializedName("id")
    int id;

    @SerializedName("name")
    String name;

    @SerializedName("email")
    String email;

    @SerializedName("phone")
    String phone;

    @SerializedName("userName")
    String userName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
