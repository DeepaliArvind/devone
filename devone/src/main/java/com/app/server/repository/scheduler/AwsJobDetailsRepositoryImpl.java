package com.app.server.repository.scheduler;
import com.app.shared.scheduler.ArtJobDetails;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;

@Repository
public class AwsJobDetailsRepositoryImpl implements ArtJobDetailsRepository {

	@Autowired
	private ResourceFactoryManagerHelper emfResource;

	/**
	 * Method for fetching list of entities
	 */
	@Override
	@Transactional
	public List<ArtJobDetails> findAll() throws Exception {
		final javax.persistence.EntityManager entityManager = emfResource.getResource();
		return entityManager.createQuery("select u from ArtJobDetails u").getResultList();
	}

	/**
	 * Return ArtJobDetails object by filtering on refernce key <jobId>
	 * 
	 * @return ArtJobDetails
	 * @Params jobId of type String
	 */
	@Override
	public ArtJobDetails findById(String jobId) {
		javax.persistence.EntityManager entityManager = emfResource.getResource();
		Query query = entityManager.createQuery("select u from ArtJobDetails u where u.jobId =:jobId");
		query.setParameter("jobId", jobId);
		return (ArtJobDetails) query.getSingleResult();
	}
}
