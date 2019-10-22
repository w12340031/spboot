package com.example.spboot.model;

public class TUser {
    private Integer id;

    private String userName;

    private Long length;

    private Integer age;

    public TUser() {
    }

    public TUser(Integer id, String userName, Long length, Integer age) {
        this.id = id;
        this.userName = userName;
        this.length = length;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}