package com.example.mireamobile;

import java.io.Serializable;

public class StudentData implements Serializable {
    private final String name;
    private final String group;
    private final String age;
    private final String mark;

    public StudentData(String name, String group, String age, String mark) {
        this.name = name;
        this.group = group;
        this.age = age;
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public String getAge() {
        return age;
    }

    public String getMark() {
        return mark;
    }
}
