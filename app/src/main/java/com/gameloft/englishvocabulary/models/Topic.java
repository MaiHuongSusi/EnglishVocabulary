package com.gameloft.englishvocabulary.models;

import java.io.Serializable;

/**
 * Created by tai.nguyenduc on 8/23/2017.
 */

//implements Serializable để đặt tuần tự các thành phần của Topic. để khi gọi topic ở các hàm kia, nó se lấy theo trình tự id->name
public class Topic implements Serializable{
    private int id;
    private String name;

    public Topic(int id, String name){
        this.id = id;
        this.name = name;
    }

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

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
