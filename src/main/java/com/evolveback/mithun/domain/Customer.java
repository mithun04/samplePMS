package com.evolveback.mithun.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Customer.
 */
@Entity
@Table(name = "customer")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "crm_contact_id")
    private String crmContactId;

    @Column(name = "lead_id")
    private String leadId;

    @Column(name = "gst_number")
    private String gstNumber;

    @Embedded
    private Profile profile = new Profile();

    public Customer() {
    }

    public Customer(String crmContactId, String leadId, String gstNumber, Profile profile) {
        this.crmContactId = crmContactId;
        this.leadId = leadId;
        this.gstNumber = gstNumber;
        this.profile = profile;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCrmContactId() {
        return crmContactId;
    }

    public Customer crmContactId(String crmContactId) {
        this.crmContactId = crmContactId;
        return this;
    }

    public void setCrmContactId(String crmContactId) {
        this.crmContactId = crmContactId;
    }

    public String getLeadId() {
        return leadId;
    }

    public Customer leadId(String leadId) {
        this.leadId = leadId;
        return this;
    }

    public void setLeadId(String leadId) {
        this.leadId = leadId;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public Customer gstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
        return this;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Customer profile(Profile profile) {
        this.profile = profile;
        return this;
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
        Customer customer = (Customer) o;
        if (customer.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Customer{" +
            "id=" + id +
            ", crmContactId='" + crmContactId + '\'' +
            ", leadId='" + leadId + '\'' +
            ", gstNumber='" + gstNumber + '\'' +
            ", profile=" + profile +
            '}';
    }
}
