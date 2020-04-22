package com.example.grocerylist.model;

import java.util.UUID;

public class Item {
    String id = UUID.randomUUID().toString();
    String name;
    Integer count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
