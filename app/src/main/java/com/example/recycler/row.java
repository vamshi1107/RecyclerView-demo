package com.example.recycler;

public class row {
    String firstname;
    String lastname;
    int image;
    String url;
    row(int image,String firstname,String lastname,String url){
        this.image=image;
        this.firstname=firstname;
        this.lastname=lastname;
        this.url=url;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public int getImage() {
        return image;
    }

    public String getLastname() {
        return lastname;
    }
}

