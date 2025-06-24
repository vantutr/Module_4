package com.codegym.baitap1.model;

import javax.validation.constraints.*;

public class User {

    @NotEmpty(message = "Tên không được để trống!")
    @Size(min = 5, max = 45, message = "Tên phải có độ dài từ 5 đến 45 ký tự!")
    private String firstName;

    @NotEmpty(message = "Tên không được để trống!")
    @Size(min = 5, max = 45, message = "Tên phải có độ dài từ 5 đến 45 ký tự!")
    private String lastName;

    @Pattern(regexp = "^0\\d{9}$", message = "Số điện thoại phải bắt đầu từ 0 và có 10 số!")
    private String phoneNumber;

    @Min(value = 18, message = "Tuổi phải lớn hơn hoặc bằng 18!")
    private int age;

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Email không hợp lệ!")
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
