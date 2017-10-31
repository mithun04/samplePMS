package com.evolveback.mithun.web.rest;

import com.evolveback.mithun.SamplePmsApp;

import com.evolveback.mithun.domain.CFormDetails;
import com.evolveback.mithun.repository.CFormDetailsRepository;
import com.evolveback.mithun.repository.search.CFormDetailsSearchRepository;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CFormDetailsResource REST controller.
 *
 * @see CFormDetailsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SamplePmsApp.class)
public class CFormDetailsResourceIntTest {

    private static final LocalDate DEFAULT_DATE_OF_BIRTH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_OF_BIRTH = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PLACE_OF_BIRTH = "AAAAAAAAAA";
    private static final String UPDATED_PLACE_OF_BIRTH = "BBBBBBBBBB";

    private static final String DEFAULT_ARRIVED_FROM = "AAAAAAAAAA";
    private static final String UPDATED_ARRIVED_FROM = "BBBBBBBBBB";

    private static final String DEFAULT_DEPARTING_TO = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTING_TO = "BBBBBBBBBB";

    private static final String DEFAULT_PASSPORT_NO = "AAAAAAAAAA";
    private static final String UPDATED_PASSPORT_NO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PASSPORT_EXPIRATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PASSPORT_EXPIRATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_EMPLOYEDIN_INDIA = false;
    private static final Boolean UPDATED_EMPLOYEDIN_INDIA = true;

    private static final String DEFAULT_PASSPORT_ISSUED_AUTHORITY = "AAAAAAAAAA";
    private static final String UPDATED_PASSPORT_ISSUED_AUTHORITY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_OF_ARRIVAL_IN_INDIA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_OF_ARRIVAL_IN_INDIA = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PLACE_OF_ISSUE = "AAAAAAAAAA";
    private static final String UPDATED_PLACE_OF_ISSUE = "BBBBBBBBBB";

    private static final String DEFAULT_VISA_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_VISA_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_VISA_NO = "AAAAAAAAAA";
    private static final String UPDATED_VISA_NO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_VISA_EFFECTIVE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_VISA_EFFECTIVE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_VISA_EXPIRY_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_VISA_EXPIRY_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_VISA_ISSUED_AUTHORITY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_VISA_ISSUED_AUTHORITY_NAME = "BBBBBBBBBB";

    @Autowired
    private CFormDetailsRepository cFormDetailsRepository;

    @Autowired
    private CFormDetailsSearchRepository cFormDetailsSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCFormDetailsMockMvc;

    private CFormDetails cFormDetails;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CFormDetailsResource cFormDetailsResource = new CFormDetailsResource(cFormDetailsRepository, cFormDetailsSearchRepository);
        this.restCFormDetailsMockMvc = MockMvcBuilders.standaloneSetup(cFormDetailsResource)
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
    public static CFormDetails createEntity(EntityManager em) {
        CFormDetails cFormDetails = new CFormDetails()
            .dateOfBirth(DEFAULT_DATE_OF_BIRTH)
            .placeOfBirth(DEFAULT_PLACE_OF_BIRTH)
            .arrivedFrom(DEFAULT_ARRIVED_FROM)
            .departingTo(DEFAULT_DEPARTING_TO)
            .passportNo(DEFAULT_PASSPORT_NO)
            .passportExpirationDate(DEFAULT_PASSPORT_EXPIRATION_DATE)
            .employedinIndia(DEFAULT_EMPLOYEDIN_INDIA)
            .passportIssuedAuthority(DEFAULT_PASSPORT_ISSUED_AUTHORITY)
            .dateOfArrivalInIndia(DEFAULT_DATE_OF_ARRIVAL_IN_INDIA)
            .placeOfIssue(DEFAULT_PLACE_OF_ISSUE)
            .visaType(DEFAULT_VISA_TYPE)
            .visaNo(DEFAULT_VISA_NO)
            .visaEffectiveDate(DEFAULT_VISA_EFFECTIVE_DATE)
            .visaExpiryDate(DEFAULT_VISA_EXPIRY_DATE)
            .visaIssuedAuthorityName(DEFAULT_VISA_ISSUED_AUTHORITY_NAME);
        return cFormDetails;
    }

    @Before
    public void initTest() {
        cFormDetailsSearchRepository.deleteAll();
        cFormDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createCFormDetails() throws Exception {
        int databaseSizeBeforeCreate = cFormDetailsRepository.findAll().size();

        // Create the CFormDetails
        restCFormDetailsMockMvc.perform(post("/api/c-form-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cFormDetails)))
            .andExpect(status().isCreated());

        // Validate the CFormDetails in the database
        List<CFormDetails> cFormDetailsList = cFormDetailsRepository.findAll();
        assertThat(cFormDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        CFormDetails testCFormDetails = cFormDetailsList.get(cFormDetailsList.size() - 1);
        assertThat(testCFormDetails.getDateOfBirth()).isEqualTo(DEFAULT_DATE_OF_BIRTH);
        assertThat(testCFormDetails.getPlaceOfBirth()).isEqualTo(DEFAULT_PLACE_OF_BIRTH);
        assertThat(testCFormDetails.getArrivedFrom()).isEqualTo(DEFAULT_ARRIVED_FROM);
        assertThat(testCFormDetails.getDepartingTo()).isEqualTo(DEFAULT_DEPARTING_TO);
        assertThat(testCFormDetails.getPassportNo()).isEqualTo(DEFAULT_PASSPORT_NO);
        assertThat(testCFormDetails.getPassportExpirationDate()).isEqualTo(DEFAULT_PASSPORT_EXPIRATION_DATE);
        assertThat(testCFormDetails.isEmployedinIndia()).isEqualTo(DEFAULT_EMPLOYEDIN_INDIA);
        assertThat(testCFormDetails.getPassportIssuedAuthority()).isEqualTo(DEFAULT_PASSPORT_ISSUED_AUTHORITY);
        assertThat(testCFormDetails.getDateOfArrivalInIndia()).isEqualTo(DEFAULT_DATE_OF_ARRIVAL_IN_INDIA);
        assertThat(testCFormDetails.getPlaceOfIssue()).isEqualTo(DEFAULT_PLACE_OF_ISSUE);
        assertThat(testCFormDetails.getVisaType()).isEqualTo(DEFAULT_VISA_TYPE);
        assertThat(testCFormDetails.getVisaNo()).isEqualTo(DEFAULT_VISA_NO);
        assertThat(testCFormDetails.getVisaEffectiveDate()).isEqualTo(DEFAULT_VISA_EFFECTIVE_DATE);
        assertThat(testCFormDetails.getVisaExpiryDate()).isEqualTo(DEFAULT_VISA_EXPIRY_DATE);
        assertThat(testCFormDetails.getVisaIssuedAuthorityName()).isEqualTo(DEFAULT_VISA_ISSUED_AUTHORITY_NAME);

        // Validate the CFormDetails in Elasticsearch
        CFormDetails cFormDetailsEs = cFormDetailsSearchRepository.findOne(testCFormDetails.getId());
        assertThat(cFormDetailsEs).isEqualToComparingFieldByField(testCFormDetails);
    }

    @Test
    @Transactional
    public void createCFormDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cFormDetailsRepository.findAll().size();

        // Create the CFormDetails with an existing ID
        cFormDetails.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCFormDetailsMockMvc.perform(post("/api/c-form-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cFormDetails)))
            .andExpect(status().isBadRequest());

        // Validate the CFormDetails in the database
        List<CFormDetails> cFormDetailsList = cFormDetailsRepository.findAll();
        assertThat(cFormDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCFormDetails() throws Exception {
        // Initialize the database
        cFormDetailsRepository.saveAndFlush(cFormDetails);

        // Get all the cFormDetailsList
        restCFormDetailsMockMvc.perform(get("/api/c-form-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cFormDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateOfBirth").value(hasItem(DEFAULT_DATE_OF_BIRTH.toString())))
            .andExpect(jsonPath("$.[*].placeOfBirth").value(hasItem(DEFAULT_PLACE_OF_BIRTH.toString())))
            .andExpect(jsonPath("$.[*].arrivedFrom").value(hasItem(DEFAULT_ARRIVED_FROM.toString())))
            .andExpect(jsonPath("$.[*].departingTo").value(hasItem(DEFAULT_DEPARTING_TO.toString())))
            .andExpect(jsonPath("$.[*].passportNo").value(hasItem(DEFAULT_PASSPORT_NO.toString())))
            .andExpect(jsonPath("$.[*].passportExpirationDate").value(hasItem(DEFAULT_PASSPORT_EXPIRATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].employedinIndia").value(hasItem(DEFAULT_EMPLOYEDIN_INDIA.booleanValue())))
            .andExpect(jsonPath("$.[*].passportIssuedAuthority").value(hasItem(DEFAULT_PASSPORT_ISSUED_AUTHORITY.toString())))
            .andExpect(jsonPath("$.[*].dateOfArrivalInIndia").value(hasItem(DEFAULT_DATE_OF_ARRIVAL_IN_INDIA.toString())))
            .andExpect(jsonPath("$.[*].placeOfIssue").value(hasItem(DEFAULT_PLACE_OF_ISSUE.toString())))
            .andExpect(jsonPath("$.[*].visaType").value(hasItem(DEFAULT_VISA_TYPE.toString())))
            .andExpect(jsonPath("$.[*].visaNo").value(hasItem(DEFAULT_VISA_NO.toString())))
            .andExpect(jsonPath("$.[*].visaEffectiveDate").value(hasItem(DEFAULT_VISA_EFFECTIVE_DATE.toString())))
            .andExpect(jsonPath("$.[*].visaExpiryDate").value(hasItem(DEFAULT_VISA_EXPIRY_DATE.toString())))
            .andExpect(jsonPath("$.[*].visaIssuedAuthorityName").value(hasItem(DEFAULT_VISA_ISSUED_AUTHORITY_NAME.toString())));
    }

    @Test
    @Transactional
    public void getCFormDetails() throws Exception {
        // Initialize the database
        cFormDetailsRepository.saveAndFlush(cFormDetails);

        // Get the cFormDetails
        restCFormDetailsMockMvc.perform(get("/api/c-form-details/{id}", cFormDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cFormDetails.getId().intValue()))
            .andExpect(jsonPath("$.dateOfBirth").value(DEFAULT_DATE_OF_BIRTH.toString()))
            .andExpect(jsonPath("$.placeOfBirth").value(DEFAULT_PLACE_OF_BIRTH.toString()))
            .andExpect(jsonPath("$.arrivedFrom").value(DEFAULT_ARRIVED_FROM.toString()))
            .andExpect(jsonPath("$.departingTo").value(DEFAULT_DEPARTING_TO.toString()))
            .andExpect(jsonPath("$.passportNo").value(DEFAULT_PASSPORT_NO.toString()))
            .andExpect(jsonPath("$.passportExpirationDate").value(DEFAULT_PASSPORT_EXPIRATION_DATE.toString()))
            .andExpect(jsonPath("$.employedinIndia").value(DEFAULT_EMPLOYEDIN_INDIA.booleanValue()))
            .andExpect(jsonPath("$.passportIssuedAuthority").value(DEFAULT_PASSPORT_ISSUED_AUTHORITY.toString()))
            .andExpect(jsonPath("$.dateOfArrivalInIndia").value(DEFAULT_DATE_OF_ARRIVAL_IN_INDIA.toString()))
            .andExpect(jsonPath("$.placeOfIssue").value(DEFAULT_PLACE_OF_ISSUE.toString()))
            .andExpect(jsonPath("$.visaType").value(DEFAULT_VISA_TYPE.toString()))
            .andExpect(jsonPath("$.visaNo").value(DEFAULT_VISA_NO.toString()))
            .andExpect(jsonPath("$.visaEffectiveDate").value(DEFAULT_VISA_EFFECTIVE_DATE.toString()))
            .andExpect(jsonPath("$.visaExpiryDate").value(DEFAULT_VISA_EXPIRY_DATE.toString()))
            .andExpect(jsonPath("$.visaIssuedAuthorityName").value(DEFAULT_VISA_ISSUED_AUTHORITY_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCFormDetails() throws Exception {
        // Get the cFormDetails
        restCFormDetailsMockMvc.perform(get("/api/c-form-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCFormDetails() throws Exception {
        // Initialize the database
        cFormDetailsRepository.saveAndFlush(cFormDetails);
        cFormDetailsSearchRepository.save(cFormDetails);
        int databaseSizeBeforeUpdate = cFormDetailsRepository.findAll().size();

        // Update the cFormDetails
        CFormDetails updatedCFormDetails = cFormDetailsRepository.findOne(cFormDetails.getId());
        updatedCFormDetails
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .placeOfBirth(UPDATED_PLACE_OF_BIRTH)
            .arrivedFrom(UPDATED_ARRIVED_FROM)
            .departingTo(UPDATED_DEPARTING_TO)
            .passportNo(UPDATED_PASSPORT_NO)
            .passportExpirationDate(UPDATED_PASSPORT_EXPIRATION_DATE)
            .employedinIndia(UPDATED_EMPLOYEDIN_INDIA)
            .passportIssuedAuthority(UPDATED_PASSPORT_ISSUED_AUTHORITY)
            .dateOfArrivalInIndia(UPDATED_DATE_OF_ARRIVAL_IN_INDIA)
            .placeOfIssue(UPDATED_PLACE_OF_ISSUE)
            .visaType(UPDATED_VISA_TYPE)
            .visaNo(UPDATED_VISA_NO)
            .visaEffectiveDate(UPDATED_VISA_EFFECTIVE_DATE)
            .visaExpiryDate(UPDATED_VISA_EXPIRY_DATE)
            .visaIssuedAuthorityName(UPDATED_VISA_ISSUED_AUTHORITY_NAME);

        restCFormDetailsMockMvc.perform(put("/api/c-form-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCFormDetails)))
            .andExpect(status().isOk());

        // Validate the CFormDetails in the database
        List<CFormDetails> cFormDetailsList = cFormDetailsRepository.findAll();
        assertThat(cFormDetailsList).hasSize(databaseSizeBeforeUpdate);
        CFormDetails testCFormDetails = cFormDetailsList.get(cFormDetailsList.size() - 1);
        assertThat(testCFormDetails.getDateOfBirth()).isEqualTo(UPDATED_DATE_OF_BIRTH);
        assertThat(testCFormDetails.getPlaceOfBirth()).isEqualTo(UPDATED_PLACE_OF_BIRTH);
        assertThat(testCFormDetails.getArrivedFrom()).isEqualTo(UPDATED_ARRIVED_FROM);
        assertThat(testCFormDetails.getDepartingTo()).isEqualTo(UPDATED_DEPARTING_TO);
        assertThat(testCFormDetails.getPassportNo()).isEqualTo(UPDATED_PASSPORT_NO);
        assertThat(testCFormDetails.getPassportExpirationDate()).isEqualTo(UPDATED_PASSPORT_EXPIRATION_DATE);
        assertThat(testCFormDetails.isEmployedinIndia()).isEqualTo(UPDATED_EMPLOYEDIN_INDIA);
        assertThat(testCFormDetails.getPassportIssuedAuthority()).isEqualTo(UPDATED_PASSPORT_ISSUED_AUTHORITY);
        assertThat(testCFormDetails.getDateOfArrivalInIndia()).isEqualTo(UPDATED_DATE_OF_ARRIVAL_IN_INDIA);
        assertThat(testCFormDetails.getPlaceOfIssue()).isEqualTo(UPDATED_PLACE_OF_ISSUE);
        assertThat(testCFormDetails.getVisaType()).isEqualTo(UPDATED_VISA_TYPE);
        assertThat(testCFormDetails.getVisaNo()).isEqualTo(UPDATED_VISA_NO);
        assertThat(testCFormDetails.getVisaEffectiveDate()).isEqualTo(UPDATED_VISA_EFFECTIVE_DATE);
        assertThat(testCFormDetails.getVisaExpiryDate()).isEqualTo(UPDATED_VISA_EXPIRY_DATE);
        assertThat(testCFormDetails.getVisaIssuedAuthorityName()).isEqualTo(UPDATED_VISA_ISSUED_AUTHORITY_NAME);

        // Validate the CFormDetails in Elasticsearch
        CFormDetails cFormDetailsEs = cFormDetailsSearchRepository.findOne(testCFormDetails.getId());
        assertThat(cFormDetailsEs).isEqualToComparingFieldByField(testCFormDetails);
    }

    @Test
    @Transactional
    public void updateNonExistingCFormDetails() throws Exception {
        int databaseSizeBeforeUpdate = cFormDetailsRepository.findAll().size();

        // Create the CFormDetails

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCFormDetailsMockMvc.perform(put("/api/c-form-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cFormDetails)))
            .andExpect(status().isCreated());

        // Validate the CFormDetails in the database
        List<CFormDetails> cFormDetailsList = cFormDetailsRepository.findAll();
        assertThat(cFormDetailsList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCFormDetails() throws Exception {
        // Initialize the database
        cFormDetailsRepository.saveAndFlush(cFormDetails);
        cFormDetailsSearchRepository.save(cFormDetails);
        int databaseSizeBeforeDelete = cFormDetailsRepository.findAll().size();

        // Get the cFormDetails
        restCFormDetailsMockMvc.perform(delete("/api/c-form-details/{id}", cFormDetails.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean cFormDetailsExistsInEs = cFormDetailsSearchRepository.exists(cFormDetails.getId());
        assertThat(cFormDetailsExistsInEs).isFalse();

        // Validate the database is empty
        List<CFormDetails> cFormDetailsList = cFormDetailsRepository.findAll();
        assertThat(cFormDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchCFormDetails() throws Exception {
        // Initialize the database
        cFormDetailsRepository.saveAndFlush(cFormDetails);
        cFormDetailsSearchRepository.save(cFormDetails);

        // Search the cFormDetails
        restCFormDetailsMockMvc.perform(get("/api/_search/c-form-details?query=id:" + cFormDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cFormDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateOfBirth").value(hasItem(DEFAULT_DATE_OF_BIRTH.toString())))
            .andExpect(jsonPath("$.[*].placeOfBirth").value(hasItem(DEFAULT_PLACE_OF_BIRTH.toString())))
            .andExpect(jsonPath("$.[*].arrivedFrom").value(hasItem(DEFAULT_ARRIVED_FROM.toString())))
            .andExpect(jsonPath("$.[*].departingTo").value(hasItem(DEFAULT_DEPARTING_TO.toString())))
            .andExpect(jsonPath("$.[*].passportNo").value(hasItem(DEFAULT_PASSPORT_NO.toString())))
            .andExpect(jsonPath("$.[*].passportExpirationDate").value(hasItem(DEFAULT_PASSPORT_EXPIRATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].employedinIndia").value(hasItem(DEFAULT_EMPLOYEDIN_INDIA.booleanValue())))
            .andExpect(jsonPath("$.[*].passportIssuedAuthority").value(hasItem(DEFAULT_PASSPORT_ISSUED_AUTHORITY.toString())))
            .andExpect(jsonPath("$.[*].dateOfArrivalInIndia").value(hasItem(DEFAULT_DATE_OF_ARRIVAL_IN_INDIA.toString())))
            .andExpect(jsonPath("$.[*].placeOfIssue").value(hasItem(DEFAULT_PLACE_OF_ISSUE.toString())))
            .andExpect(jsonPath("$.[*].visaType").value(hasItem(DEFAULT_VISA_TYPE.toString())))
            .andExpect(jsonPath("$.[*].visaNo").value(hasItem(DEFAULT_VISA_NO.toString())))
            .andExpect(jsonPath("$.[*].visaEffectiveDate").value(hasItem(DEFAULT_VISA_EFFECTIVE_DATE.toString())))
            .andExpect(jsonPath("$.[*].visaExpiryDate").value(hasItem(DEFAULT_VISA_EXPIRY_DATE.toString())))
            .andExpect(jsonPath("$.[*].visaIssuedAuthorityName").value(hasItem(DEFAULT_VISA_ISSUED_AUTHORITY_NAME.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CFormDetails.class);
        CFormDetails cFormDetails1 = new CFormDetails();
        cFormDetails1.setId(1L);
        CFormDetails cFormDetails2 = new CFormDetails();
        cFormDetails2.setId(cFormDetails1.getId());
        assertThat(cFormDetails1).isEqualTo(cFormDetails2);
        cFormDetails2.setId(2L);
        assertThat(cFormDetails1).isNotEqualTo(cFormDetails2);
        cFormDetails1.setId(null);
        assertThat(cFormDetails1).isNotEqualTo(cFormDetails2);
    }
}
