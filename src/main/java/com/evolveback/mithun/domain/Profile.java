package com.evolveback.mithun.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class Profile {

    @Column(name="title")
    private String title;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="gender")
    private String gender;

    @Column(name="telephone_no")
    private String telephoneNo;

    @Column(name="mobile_no")
    private String mobileNo;

    @Column(name="email_id")
    private String emailId;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "province")
    private String province;

    @Column(name = "country")
    private String country;

    @Column(name = "postal_code")
    private String postalCode;

    public Profile() {
    }

    public Profile(String title, String firstName, String lastName, String gender, String telephoneNo, String mobileNo, String emailId, String address, String city, String province, String country, String postalCode) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.telephoneNo = telephoneNo;
        this.mobileNo = mobileNo;
        this.emailId = emailId;
        this.address = address;
        this.city = city;
        this.province = province;
        this.country = country;
        this.postalCode = postalCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTelephoneNo() {
        return telephoneNo;
    }

    public void setTelephoneNo(String telephoneNo) {
        this.telephoneNo = telephoneNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Transient
    public String getFullName(){
        String fullName="";
        fullName=title==null?fullName.concat(""):fullName.concat(title);
        fullName=firstName==null?fullName.concat(""):fullName.concat(" "+firstName);
        fullName=lastName==null?fullName.concat(""):fullName.concat(" "+lastName);
        return fullName;
    }

    @Transient
    public String getFullAddress(){
        String fullAddress="";
        fullAddress=address==null?fullAddress.concat(""):fullAddress.concat(address);
        fullAddress=city==null?fullAddress.concat(""):fullAddress.concat(","+city);
        fullAddress=province==null?fullAddress.concat(""):fullAddress.concat(","+province);
        fullAddress=country==null?fullAddress.concat(""):fullAddress.concat(","+country);
        fullAddress=postalCode==null?fullAddress.concat(""):fullAddress.concat("-"+postalCode);
        return fullAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profile profile = (Profile) o;

        if (title != null ? !title.equals(profile.title) : profile.title != null) return false;
        if (firstName != null ? !firstName.equals(profile.firstName) : profile.firstName != null) return false;
        if (lastName != null ? !lastName.equals(profile.lastName) : profile.lastName != null) return false;
        if (gender != null ? !gender.equals(profile.gender) : profile.gender != null) return false;
        if (telephoneNo != null ? !telephoneNo.equals(profile.telephoneNo) : profile.telephoneNo != null) return false;
        if (mobileNo != null ? !mobileNo.equals(profile.mobileNo) : profile.mobileNo != null) return false;
        if (emailId != null ? !emailId.equals(profile.emailId) : profile.emailId != null) return false;
        if (address != null ? !address.equals(profile.address) : profile.address != null) return false;
        if (city != null ? !city.equals(profile.city) : profile.city != null) return false;
        if (province != null ? !province.equals(profile.province) : profile.province != null) return false;
        if (country != null ? !country.equals(profile.country) : profile.country != null) return false;
        return postalCode != null ? postalCode.equals(profile.postalCode) : profile.postalCode == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (telephoneNo != null ? telephoneNo.hashCode() : 0);
        result = 31 * result + (mobileNo != null ? mobileNo.hashCode() : 0);
        result = 31 * result + (emailId != null ? emailId.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "title='" + title + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", telephoneNo='" + telephoneNo + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", emailId='" + emailId + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
