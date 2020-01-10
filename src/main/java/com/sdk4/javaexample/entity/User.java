package com.sdk4.javaexample.entity;

/**
 * 用户信息
 */
public class User {
    // id
    private Integer id;

    // 用户姓名
    private String name;

    // 年龄
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
