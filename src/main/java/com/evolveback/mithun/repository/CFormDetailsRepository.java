package com.evolveback.mithun.repository;

import com.evolveback.mithun.domain.CFormDetails;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the CFormDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CFormDetailsRepository extends JpaRepository<CFormDetails, Long> {

}
