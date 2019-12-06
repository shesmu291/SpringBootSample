package com.cko.sampleSpringProject.model;

//import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;

@Entity
public class Films {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String title;

    @Column
    private int rating;

    @Column
    private int age;

    public Films(){

    }

    public Films(String title, int rating, int age) {
        this.title = title;
        this.rating = rating;
        this.age = age;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getRating() {
        return rating;
    }

    public int getAge() {
        return age;
    }
}
