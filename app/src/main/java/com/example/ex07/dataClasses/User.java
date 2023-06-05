package com.example.ex07.dataClasses;

public class User {

    //region Attributes
    private String firstName;
    private String LastName;
    private String email;
    private String password;
    //endregion

    //region Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws Exception {
        isBlankOrEmpty(firstName);
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) throws Exception {
        isBlankOrEmpty(firstName);
        LastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception {
        isBlankOrEmpty(firstName);
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws Exception {
        isBlankOrEmpty(firstName);
        this.password = password;
    }
    //endregion

    //region Constructor
    public User(String firstName, String lastName, String email, String password) throws Exception {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setPassword(password);
    }
    //endregion

    //region Method : isBlankOrEmpty
    private void isBlankOrEmpty(String param) throws Exception {
        if(param.isBlank()){
            throw new Exception("field is blank or empty");
        }
    }
    //endregion

}
