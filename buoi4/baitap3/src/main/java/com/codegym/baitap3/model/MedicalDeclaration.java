package com.codegym.baitap3.model;

public class MedicalDeclaration {

    // Thông tin cá nhân
    private String name;
    private String yearOfBirth;
    private String gender;
    private String nationality;
    private String idCard;

    // Thông tin đi lại
    private String travelInfo;
    private String vehicleNumber;
    private String seatNumber;
    private String startDay;
    private String startMonth;
    private String startYear;
    private String endDay;
    private String endMonth;
    private String endYear;
    private String travelHistory14Days;

    // Địa chỉ liên lạc
    private String province;
    private String district;
    private String ward;
    private String address;
    private String phone;
    private String email;

    // Triệu chứng (mỗi triệu chứng là một lựa chọn Có/Không)
    private String symptomFever;
    private String symptomCough;
    private String symptomShortBreath;
    private String symptomSoreThroat;
    private String symptomVomit;
    private String symptomDiarrhea;
    private String symptomHemorrhage;
    private String symptomRash;

    // Lịch sử phơi nhiễm
    private String exposureAnimal;
    private String exposureCovid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getTravelInfo() {
        return travelInfo;
    }

    public void setTravelInfo(String travelInfo) {
        this.travelInfo = travelInfo;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public String getTravelHistory14Days() {
        return travelHistory14Days;
    }

    public void setTravelHistory14Days(String travelHistory14Days) {
        this.travelHistory14Days = travelHistory14Days;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSymptomFever() {
        return symptomFever;
    }

    public void setSymptomFever(String symptomFever) {
        this.symptomFever = symptomFever;
    }

    public String getSymptomCough() {
        return symptomCough;
    }

    public void setSymptomCough(String symptomCough) {
        this.symptomCough = symptomCough;
    }

    public String getSymptomShortBreath() {
        return symptomShortBreath;
    }

    public void setSymptomShortBreath(String symptomShortBreath) {
        this.symptomShortBreath = symptomShortBreath;
    }

    public String getSymptomSoreThroat() {
        return symptomSoreThroat;
    }

    public void setSymptomSoreThroat(String symptomSoreThroat) {
        this.symptomSoreThroat = symptomSoreThroat;
    }

    public String getSymptomVomit() {
        return symptomVomit;
    }

    public void setSymptomVomit(String symptomVomit) {
        this.symptomVomit = symptomVomit;
    }

    public String getSymptomDiarrhea() {
        return symptomDiarrhea;
    }

    public void setSymptomDiarrhea(String symptomDiarrhea) {
        this.symptomDiarrhea = symptomDiarrhea;
    }

    public String getSymptomHemorrhage() {
        return symptomHemorrhage;
    }

    public void setSymptomHemorrhage(String symptomHemorrhage) {
        this.symptomHemorrhage = symptomHemorrhage;
    }

    public String getSymptomRash() {
        return symptomRash;
    }

    public void setSymptomRash(String symptomRash) {
        this.symptomRash = symptomRash;
    }

    public String getExposureAnimal() {
        return exposureAnimal;
    }

    public void setExposureAnimal(String exposureAnimal) {
        this.exposureAnimal = exposureAnimal;
    }

    public String getExposureCovid() {
        return exposureCovid;
    }

    public void setExposureCovid(String exposureCovid) {
        this.exposureCovid = exposureCovid;
    }
}