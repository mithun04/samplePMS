package com.evolveback.mithun.web.rest;

import com.evolveback.mithun.SamplePmsApp;

import com.evolveback.mithun.domain.Customer;
import com.evolveback.mithun.domain.Profile;
import com.evolveback.mithun.repository.CustomerRepository;
import com.evolveback.mithun.repository.search.CustomerSearchRepository;
import com.evolveback.mithun.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CustomerResource REST controller.
 *
 * @see CustomerResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SamplePmsApp.class)
public class CustomerResourceIntTest {

    private static final String DEFAULT_CRM_CONTACT_ID = "AAAAAAAAAA";
    private static final String UPDATED_CRM_CONTACT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_LEAD_ID = "AAAAAAAAAA";
    private static final String UPDATED_LEAD_ID = "BBBBBBBBBB";

    private static final String DEFAULT_GST_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_GST_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_GENDER = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE_NO = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE_NO = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_ID = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_PROVINCE = "AAAAAAAAAA";
    private static final String UPDATED_PROVINCE = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_POSTAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_POSTAL_CODE = "BBBBBBBBBB";

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerSearchRepository customerSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCustomerMockMvc;

    private Customer customer;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CustomerResource customerResource = new CustomerResource(customerRepository, customerSearchRepository);
        this.restCustomerMockMvc = MockMvcBuilders.standaloneSetup(customerResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Customer createEntity(EntityManager em) {
        Customer customer = new Customer()
            .crmContactId(DEFAULT_CRM_CONTACT_ID)
            .leadId(DEFAULT_LEAD_ID)
            .gstNumber(DEFAULT_GST_NUMBER)
            .profile(new Profile(
                DEFAULT_TITLE,
                DEFAULT_FIRST_NAME,
                DEFAULT_LAST_NAME,
                DEFAULT_GENDER,
                DEFAULT_TELEPHONE_NO,
                DEFAULT_MOBILE_NO,
                DEFAULT_EMAIL_ID,
                DEFAULT_ADDRESS,
                DEFAULT_CITY,
                DEFAULT_PROVINCE,
                DEFAULT_COUNTRY,
                DEFAULT_POSTAL_CODE
            ));
        return customer;
    }

    @Before
    public void initTest() {
        customerSearchRepository.deleteAll();
        customer = createEntity(em);
    }

    @Test
    @Transactional
    public void createCustomer() throws Exception {
        int databaseSizeBeforeCreate = customerRepository.findAll().size();

        // Create the Customer
        restCustomerMockMvc.perform(post("/api/customers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customer)))
            .andExpect(status().isCreated());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeCreate + 1);
        Customer testCustomer = customerList.get(customerList.size() - 1);
        assertThat(testCustomer.getCrmContactId()).isEqualTo(DEFAULT_CRM_CONTACT_ID);
        assertThat(testCustomer.getLeadId()).isEqualTo(DEFAULT_LEAD_ID);
        assertThat(testCustomer.getGstNumber()).isEqualTo(DEFAULT_GST_NUMBER);
        assertThat(testCustomer.getProfile().getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testCustomer.getProfile().getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testCustomer.getProfile().getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testCustomer.getProfile().getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testCustomer.getProfile().getTelephoneNo()).isEqualTo(DEFAULT_TELEPHONE_NO);
        assertThat(testCustomer.getProfile().getMobileNo()).isEqualTo(DEFAULT_MOBILE_NO);
        assertThat(testCustomer.getProfile().getEmailId()).isEqualTo(DEFAULT_EMAIL_ID);
        assertThat(testCustomer.getProfile().getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testCustomer.getProfile().getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testCustomer.getProfile().getProvince()).isEqualTo(DEFAULT_PROVINCE);
        assertThat(testCustomer.getProfile().getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testCustomer.getProfile().getPostalCode()).isEqualTo(DEFAULT_POSTAL_CODE);

        // Validate the Customer in Elasticsearch
        Customer customerEs = customerSearchRepository.findOne(testCustomer.getId());
        assertThat(customerEs).isEqualToComparingFieldByField(testCustomer);
    }

    @Test
    @Transactional
    public void createCustomerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = customerRepository.findAll().size();

        // Create the Customer with an existing ID
        customer.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomerMockMvc.perform(post("/api/customers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customer)))
            .andExpect(status().isBadRequest());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCustomers() throws Exception {
        // Initialize the database
        customerRepository.saveAndFlush(customer);

        // Get all the customerList
        restCustomerMockMvc.perform(get("/api/customers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customer.getId().intValue())))
            .andExpect(jsonPath("$.[*].crmContactId").value(hasItem(DEFAULT_CRM_CONTACT_ID.toString())))
            .andExpect(jsonPath("$.[*].leadId").value(hasItem(DEFAULT_LEAD_ID.toString())))
            .andExpect(jsonPath("$.[*].gstNumber").value(hasItem(DEFAULT_GST_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].first_name").value(hasItem(DEFAULT_FIRST_NAME.toString())))
            .andExpect(jsonPath("$.[*].last_name").value(hasItem(DEFAULT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].telephone_no").value(hasItem(DEFAULT_TELEPHONE_NO.toString())))
            .andExpect(jsonPath("$.[*].mobile_no").value(hasItem(DEFAULT_MOBILE_NO.toString())))
            .andExpect(jsonPath("$.[*].email_id").value(hasItem(DEFAULT_EMAIL_ID.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())))
            .andExpect(jsonPath("$.[*].province").value(hasItem(DEFAULT_PROVINCE.toString())))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY.toString())))
            .andExpect(jsonPath("$.[*].postal_code").value(hasItem(DEFAULT_POSTAL_CODE.toString())));
    }

    @Test
    @Transactional
    public void getCustomer() throws Exception {
        // Initialize the database
        customerRepository.saveAndFlush(customer);

        // Get the customer
        restCustomerMockMvc.perform(get("/api/customers/{id}", customer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(customer.getId().intValue()))
            .andExpect(jsonPath("$.crmContactId").value(DEFAULT_CRM_CONTACT_ID.toString()))
            .andExpect(jsonPath("$.leadId").value(DEFAULT_LEAD_ID.toString()))
            .andExpect(jsonPath("$.gstNumber").value(DEFAULT_GST_NUMBER.toString()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.first_name").value(DEFAULT_FIRST_NAME.toString()))
            .andExpect(jsonPath("$.last_name").value(DEFAULT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.telephone_no").value(DEFAULT_TELEPHONE_NO.toString()))
            .andExpect(jsonPath("$.mobile_no").value(DEFAULT_MOBILE_NO.toString()))
            .andExpect(jsonPath("$.email_id").value(DEFAULT_EMAIL_ID.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.toString()))
            .andExpect(jsonPath("$.province").value(DEFAULT_PROVINCE.toString()))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY.toString()))
            .andExpect(jsonPath("$.postal_code").value(DEFAULT_POSTAL_CODE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCustomer() throws Exception {
        // Get the customer
        restCustomerMockMvc.perform(get("/api/customers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCustomer() throws Exception {
        // Initialize the database
        customerRepository.saveAndFlush(customer);
        customerSearchRepository.save(customer);
        int databaseSizeBeforeUpdate = customerRepository.findAll().size();

        // Update the customer
        Customer updatedCustomer = customerRepository.findOne(customer.getId());
        updatedCustomer
            .crmContactId(UPDATED_CRM_CONTACT_ID)
            .leadId(UPDATED_LEAD_ID)
            .gstNumber(UPDATED_GST_NUMBER)
            .profile(new Profile(
                DEFAULT_TITLE,
                DEFAULT_FIRST_NAME,
                DEFAULT_LAST_NAME,
                DEFAULT_GENDER,
                DEFAULT_TELEPHONE_NO,
                DEFAULT_MOBILE_NO,
                DEFAULT_EMAIL_ID,
                DEFAULT_ADDRESS,
                DEFAULT_CITY,
                DEFAULT_PROVINCE,
                DEFAULT_COUNTRY,
                DEFAULT_POSTAL_CODE
            ));

        restCustomerMockMvc.perform(put("/api/customers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCustomer)))
            .andExpect(status().isOk());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
        Customer testCustomer = customerList.get(customerList.size() - 1);
        assertThat(testCustomer.getCrmContactId()).isEqualTo(UPDATED_CRM_CONTACT_ID);
        assertThat(testCustomer.getLeadId()).isEqualTo(UPDATED_LEAD_ID);
        assertThat(testCustomer.getGstNumber()).isEqualTo(UPDATED_GST_NUMBER);
        assertThat(testCustomer.getProfile().getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testCustomer.getProfile().getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testCustomer.getProfile().getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testCustomer.getProfile().getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testCustomer.getProfile().getTelephoneNo()).isEqualTo(UPDATED_TELEPHONE_NO);
        assertThat(testCustomer.getProfile().getMobileNo()).isEqualTo(UPDATED_MOBILE_NO);
        assertThat(testCustomer.getProfile().getEmailId()).isEqualTo(UPDATED_EMAIL_ID);
        assertThat(testCustomer.getProfile().getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testCustomer.getProfile().getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testCustomer.getProfile().getProvince()).isEqualTo(UPDATED_PROVINCE);
        assertThat(testCustomer.getProfile().getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testCustomer.getProfile().getPostalCode()).isEqualTo(UPDATED_POSTAL_CODE);

        // Validate the Customer in Elasticsearch
        Customer customerEs = customerSearchRepository.findOne(testCustomer.getId());
        assertThat(customerEs).isEqualToComparingFieldByField(testCustomer);
    }

    @Test
    @Transactional
    public void updateNonExistingCustomer() throws Exception {
        int databaseSizeBeforeUpdate = customerRepository.findAll().size();

        // Create the Customer

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCustomerMockMvc.perform(put("/api/customers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customer)))
            .andExpect(status().isCreated());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCustomer() throws Exception {
        // Initialize the database
        customerRepository.saveAndFlush(customer);
        customerSearchRepository.save(customer);
        int databaseSizeBeforeDelete = customerRepository.findAll().size();

        // Get the customer
        restCustomerMockMvc.perform(delete("/api/customers/{id}", customer.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean customerExistsInEs = customerSearchRepository.exists(customer.getId());
        assertThat(customerExistsInEs).isFalse();

        // Validate the database is empty
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchCustomer() throws Exception {
        // Initialize the database
        customerRepository.saveAndFlush(customer);
        customerSearchRepository.save(customer);

        // Search the customer
        restCustomerMockMvc.perform(get("/api/_search/customers?query=id:" + customer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customer.getId().intValue())))
            .andExpect(jsonPath("$.[*].crmContactId").value(hasItem(DEFAULT_CRM_CONTACT_ID.toString())))
            .andExpect(jsonPath("$.[*].leadId").value(hasItem(DEFAULT_LEAD_ID.toString())))
            .andExpect(jsonPath("$.[*].gstNumber").value(hasItem(DEFAULT_GST_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].first_name").value(hasItem(DEFAULT_FIRST_NAME.toString())))
            .andExpect(jsonPath("$.[*].last_name").value(hasItem(DEFAULT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].telephone_no").value(hasItem(DEFAULT_TELEPHONE_NO.toString())))
            .andExpect(jsonPath("$.[*].mobile_no").value(hasItem(DEFAULT_MOBILE_NO.toString())))
            .andExpect(jsonPath("$.[*].email_id").value(hasItem(DEFAULT_EMAIL_ID.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())))
            .andExpect(jsonPath("$.[*].province").value(hasItem(DEFAULT_PROVINCE.toString())))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY.toString())))
            .andExpect(jsonPath("$.[*].postal_code").value(hasItem(DEFAULT_POSTAL_CODE.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Customer.class);
        Customer customer1 = new Customer();
        customer1.setId(1L);
        Customer customer2 = new Customer();
        customer2.setId(customer1.getId());
        assertThat(customer1).isEqualTo(customer2);
        customer2.setId(2L);
        assertThat(customer1).isNotEqualTo(customer2);
        customer1.setId(null);
        assertThat(customer1).isNotEqualTo(customer2);
    }
}
