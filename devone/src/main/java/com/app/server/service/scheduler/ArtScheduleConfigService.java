package com.app.server.service.scheduler;
import com.app.shared.scheduler.ArtJobDetails;

import com.app.shared.scheduler.ArtScheduleConfig;

import java.util.List;

import atg.taglib.json.util.JSONArray;

import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;

public interface ArtScheduleConfigService {

	void save(ArtScheduleConfig entity) throws Exception;

	void update(ArtScheduleConfig entity) throws Exception;

	List<ArtScheduleConfig> findAll();

	ArtScheduleConfig findById(String findKey) throws Exception;

	JSONArray getTreeStores() throws Exception;

	List<ArtJobDetails> findAllJobs() throws Exception;

}
