package com.evolveback.mithun.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A CFormDetails.
 */
@Entity
@Table(name = "cform_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "cformdetails")
public class CFormDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "place_of_birth")
    private String placeOfBirth;

    @Column(name = "arrived_from")
    private String arrivedFrom;

    @Column(name = "departing_to")
    private String departingTo;

    @Column(name = "passport_no")
    private String passportNo;

    @Column(name = "passport_expiration_date")
    private LocalDate passportExpirationDate;

    @Column(name = "employedin_india")
    private Boolean employedinIndia;

    @Column(name = "passport_issued_authority")
    private String passportIssuedAuthority;

    @Column(name = "date_of_arrival_in_india")
    private LocalDate dateOfArrivalInIndia;

    @Column(name = "place_of_issue")
    private String placeOfIssue;

    @Column(name = "visa_type")
    private String visaType;

    @Column(name = "visa_no")
    private String visaNo;

    @Column(name = "visa_effective_date")
    private LocalDate visaEffectiveDate;

    @Column(name = "visa_expiry_date")
    private LocalDate visaExpiryDate;

    @Column(name = "visa_issued_authority_name")
    private String visaIssuedAuthorityName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public CFormDetails dateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public CFormDetails placeOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
        return this;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getArrivedFrom() {
        return arrivedFrom;
    }

    public CFormDetails arrivedFrom(String arrivedFrom) {
        this.arrivedFrom = arrivedFrom;
        return this;
    }

    public void setArrivedFrom(String arrivedFrom) {
        this.arrivedFrom = arrivedFrom;
    }

    public String getDepartingTo() {
        return departingTo;
    }

    public CFormDetails departingTo(String departingTo) {
        this.departingTo = departingTo;
        return this;
    }

    public void setDepartingTo(String departingTo) {
        this.departingTo = departingTo;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public CFormDetails passportNo(String passportNo) {
        this.passportNo = passportNo;
        return this;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public LocalDate getPassportExpirationDate() {
        return passportExpirationDate;
    }

    public CFormDetails passportExpirationDate(LocalDate passportExpirationDate) {
        this.passportExpirationDate = passportExpirationDate;
        return this;
    }

    public void setPassportExpirationDate(LocalDate passportExpirationDate) {
        this.passportExpirationDate = passportExpirationDate;
    }

    public Boolean isEmployedinIndia() {
        return employedinIndia;
    }

    public CFormDetails employedinIndia(Boolean employedinIndia) {
        this.employedinIndia = employedinIndia;
        return this;
    }

    public void setEmployedinIndia(Boolean employedinIndia) {
        this.employedinIndia = employedinIndia;
    }

    public String getPassportIssuedAuthority() {
        return passportIssuedAuthority;
    }

    public CFormDetails passportIssuedAuthority(String passportIssuedAuthority) {
        this.passportIssuedAuthority = passportIssuedAuthority;
        return this;
    }

    public void setPassportIssuedAuthority(String passportIssuedAuthority) {
        this.passportIssuedAuthority = passportIssuedAuthority;
    }

    public LocalDate getDateOfArrivalInIndia() {
        return dateOfArrivalInIndia;
    }

    public CFormDetails dateOfArrivalInIndia(LocalDate dateOfArrivalInIndia) {
        this.dateOfArrivalInIndia = dateOfArrivalInIndia;
        return this;
    }

    public void setDateOfArrivalInIndia(LocalDate dateOfArrivalInIndia) {
        this.dateOfArrivalInIndia = dateOfArrivalInIndia;
    }

    public String getPlaceOfIssue() {
        return placeOfIssue;
    }

    public CFormDetails placeOfIssue(String placeOfIssue) {
        this.placeOfIssue = placeOfIssue;
        return this;
    }

    public void setPlaceOfIssue(String placeOfIssue) {
        this.placeOfIssue = placeOfIssue;
    }

    public String getVisaType() {
        return visaType;
    }

    public CFormDetails visaType(String visaType) {
        this.visaType = visaType;
        return this;
    }

    public void setVisaType(String visaType) {
        this.visaType = visaType;
    }

    public String getVisaNo() {
        return visaNo;
    }

    public CFormDetails visaNo(String visaNo) {
        this.visaNo = visaNo;
        return this;
    }

    public void setVisaNo(String visaNo) {
        this.visaNo = visaNo;
    }

    public LocalDate getVisaEffectiveDate() {
        return visaEffectiveDate;
    }

    public CFormDetails visaEffectiveDate(LocalDate visaEffectiveDate) {
        this.visaEffectiveDate = visaEffectiveDate;
        return this;
    }

    public void setVisaEffectiveDate(LocalDate visaEffectiveDate) {
        this.visaEffectiveDate = visaEffectiveDate;
    }

    public LocalDate getVisaExpiryDate() {
        return visaExpiryDate;
    }

    public CFormDetails visaExpiryDate(LocalDate visaExpiryDate) {
        this.visaExpiryDate = visaExpiryDate;
        return this;
    }

    public void setVisaExpiryDate(LocalDate visaExpiryDate) {
        this.visaExpiryDate = visaExpiryDate;
    }

    public String getVisaIssuedAuthorityName() {
        return visaIssuedAuthorityName;
    }

    public CFormDetails visaIssuedAuthorityName(String visaIssuedAuthorityName) {
        this.visaIssuedAuthorityName = visaIssuedAuthorityName;
        return this;
    }

    public void setVisaIssuedAuthorityName(String visaIssuedAuthorityName) {
        this.visaIssuedAuthorityName = visaIssuedAuthorityName;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CFormDetails cFormDetails = (CFormDetails) o;
        if (cFormDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cFormDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CFormDetails{" +
            "id=" + getId() +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", placeOfBirth='" + getPlaceOfBirth() + "'" +
            ", arrivedFrom='" + getArrivedFrom() + "'" +
            ", departingTo='" + getDepartingTo() + "'" +
            ", passportNo='" + getPassportNo() + "'" +
            ", passportExpirationDate='" + getPassportExpirationDate() + "'" +
            ", employedinIndia='" + isEmployedinIndia() + "'" +
            ", passportIssuedAuthority='" + getPassportIssuedAuthority() + "'" +
            ", dateOfArrivalInIndia='" + getDateOfArrivalInIndia() + "'" +
            ", placeOfIssue='" + getPlaceOfIssue() + "'" +
            ", visaType='" + getVisaType() + "'" +
            ", visaNo='" + getVisaNo() + "'" +
            ", visaEffectiveDate='" + getVisaEffectiveDate() + "'" +
            ", visaExpiryDate='" + getVisaExpiryDate() + "'" +
            ", visaIssuedAuthorityName='" + getVisaIssuedAuthorityName() + "'" +
            "}";
    }
}
