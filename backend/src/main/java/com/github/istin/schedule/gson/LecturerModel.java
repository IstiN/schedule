package com.github.istin.schedule.gson;

/**
 * Created by Yury on 15.10.2015.
 */
public class LecturerModel {

    private String id;
    private String name;
    private String surname;
    private String patronym;
    private String post;
    private String phone;
    private String descr;
    private String email;
    private String skype;
    private String fullname;

    public LecturerModel() {
    }

    public LecturerModel(String id, String name, String surname, String patronym, String post, String phone, String descr,
                         String email, String skype) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronym = patronym;
        this.post = post;
        this.phone = phone;
        this.descr = descr;
        this.email = email;
        this.skype = skype;
    }

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronym() {
        return patronym;
    }

    public void setPatronym(String patronym) {
        this.patronym = patronym;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getFullname() {
        return (fullname != null) ? fullname : surname + " " + name + " " + patronym;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString() {

        return getFullname();
    }

}

