package com.evolveback.mithun.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.evolveback.mithun.domain.CFormDetails;

import com.evolveback.mithun.repository.CFormDetailsRepository;
import com.evolveback.mithun.repository.search.CFormDetailsSearchRepository;
import com.evolveback.mithun.web.rest.util.HeaderUtil;
import com.evolveback.mithun.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing CFormDetails.
 */
@RestController
@RequestMapping("/api")
public class CFormDetailsResource {

    private final Logger log = LoggerFactory.getLogger(CFormDetailsResource.class);

    private static final String ENTITY_NAME = "cFormDetails";

    private final CFormDetailsRepository cFormDetailsRepository;

    private final CFormDetailsSearchRepository cFormDetailsSearchRepository;

    public CFormDetailsResource(CFormDetailsRepository cFormDetailsRepository, CFormDetailsSearchRepository cFormDetailsSearchRepository) {
        this.cFormDetailsRepository = cFormDetailsRepository;
        this.cFormDetailsSearchRepository = cFormDetailsSearchRepository;
    }

    /**
     * POST  /c-form-details : Create a new cFormDetails.
     *
     * @param cFormDetails the cFormDetails to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cFormDetails, or with status 400 (Bad Request) if the cFormDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/c-form-details")
    @Timed
    public ResponseEntity<CFormDetails> createCFormDetails(@RequestBody CFormDetails cFormDetails) throws URISyntaxException {
        log.debug("REST request to save CFormDetails : {}", cFormDetails);
        if (cFormDetails.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new cFormDetails cannot already have an ID")).body(null);
        }
        CFormDetails result = cFormDetailsRepository.save(cFormDetails);
        cFormDetailsSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/c-form-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /c-form-details : Updates an existing cFormDetails.
     *
     * @param cFormDetails the cFormDetails to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cFormDetails,
     * or with status 400 (Bad Request) if the cFormDetails is not valid,
     * or with status 500 (Internal Server Error) if the cFormDetails couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/c-form-details")
    @Timed
    public ResponseEntity<CFormDetails> updateCFormDetails(@RequestBody CFormDetails cFormDetails) throws URISyntaxException {
        log.debug("REST request to update CFormDetails : {}", cFormDetails);
        if (cFormDetails.getId() == null) {
            return createCFormDetails(cFormDetails);
        }
        CFormDetails result = cFormDetailsRepository.save(cFormDetails);
        cFormDetailsSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cFormDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /c-form-details : get all the cFormDetails.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of cFormDetails in body
     */
    @GetMapping("/c-form-details")
    @Timed
    public ResponseEntity<List<CFormDetails>> getAllCFormDetails(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of CFormDetails");
        Page<CFormDetails> page = cFormDetailsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/c-form-details");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /c-form-details/:id : get the "id" cFormDetails.
     *
     * @param id the id of the cFormDetails to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cFormDetails, or with status 404 (Not Found)
     */
    @GetMapping("/c-form-details/{id}")
    @Timed
    public ResponseEntity<CFormDetails> getCFormDetails(@PathVariable Long id) {
        log.debug("REST request to get CFormDetails : {}", id);
        CFormDetails cFormDetails = cFormDetailsRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(cFormDetails));
    }

    /**
     * DELETE  /c-form-details/:id : delete the "id" cFormDetails.
     *
     * @param id the id of the cFormDetails to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/c-form-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteCFormDetails(@PathVariable Long id) {
        log.debug("REST request to delete CFormDetails : {}", id);
        cFormDetailsRepository.delete(id);
        cFormDetailsSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/c-form-details?query=:query : search for the cFormDetails corresponding
     * to the query.
     *
     * @param query the query of the cFormDetails search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/c-form-details")
    @Timed
    public ResponseEntity<List<CFormDetails>> searchCFormDetails(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of CFormDetails for query {}", query);
        Page<CFormDetails> page = cFormDetailsSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/c-form-details");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
