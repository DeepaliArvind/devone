package com.app.server.repository.scheduler;
import com.app.shared.scheduler.ArtScheduleConfig;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;

@Repository
@Transactional
public class ArtScheduleConfigRepositoryImpl implements ArtScheduleConfigRepository {
	@Autowired
	private ResourceFactoryManagerHelper emfResource;

	/**
	 * Saves the new <ArtScheduleConfig> object.
	 * 
	 * @return Country
	 * @Params Object of ArtScheduleConfig
	 */
	@Override
	@Transactional
	public void save(final ArtScheduleConfig entity) {
		try {
			final javax.persistence.EntityManager entityManager = emfResource.getResource();
			entityManager.persist(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Updates the <ArtScheduleConfig> object.
	 * 
	 * @Params Object of ArtScheduleConfig
	 */
	@Override
	@Transactional
	public void update(final ArtScheduleConfig entity) {
		try {
			final javax.persistence.EntityManager entityManager = emfResource.getResource();
			entityManager.merge(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method for fetching list of entities
	 */
	@Override
	public List<ArtScheduleConfig> findAll() {
		try {
			final javax.persistence.EntityManager entityManager = emfResource.getResource();
			return entityManager.createQuery("select u from ArtScheduleConfig u").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Return ArtScheduleConfig object by filtering on refernce key <scheudleId>
	 * 
	 * @return ArtScheduleConfig
	 * @Params scheduleId of type String
	 */
	@Override
	public ArtScheduleConfig findById(final String scheduleId) {
		try {
			final javax.persistence.EntityManager entityManager = emfResource.getResource();
			javax.persistence.Query query = entityManager.createQuery("select u from ArtScheduleConfig u where u.schedulerId=:schedulerId");
			query.setParameter("schedulerId", scheduleId);
			return (ArtScheduleConfig) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
