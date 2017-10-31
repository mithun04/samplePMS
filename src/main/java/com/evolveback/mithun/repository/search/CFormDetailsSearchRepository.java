package com.evolveback.mithun.repository.search;

import com.evolveback.mithun.domain.CFormDetails;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the CFormDetails entity.
 */
public interface CFormDetailsSearchRepository extends ElasticsearchRepository<CFormDetails, Long> {
}
