package com.app.server.service.scheduler;
import com.app.shared.scheduler.ArtJobDetails;

import com.app.shared.scheduler.ArtScheduleConfig;

import com.app.server.repository.scheduler.ArtJobDetailsRepository;

import com.app.server.repository.scheduler.ArtScheduleConfigRepository;

import com.app.shared.scheduler.SchedulerGeneration;
import com.app.server.repository.appbasicsetup.aaa.PublicApiRepository;
import com.app.shared.appbasicsetup.aaa.PublicApi;
import com.app.shared.scheduler.ArtJobDetails;
import com.app.shared.scheduler.ArtScheduleConfig;

import java.util.Iterator;
import java.util.List;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ArtScheduleConfigServiceImpl implements ArtScheduleConfigService {

	@Autowired
	private ArtScheduleConfigRepository scheduleConfigRepo;

	@Autowired
	private SchedulerGeneration schedulerGeneration;

	@Autowired
	private ArtJobDetailsRepository artJobDetailsRepository;

	@Autowired
	private PublicApiRepository<PublicApi> publicApiRepository;
	
	@Override
	public void save(ArtScheduleConfig entity) throws Exception {
		try {
			entity.setSchedulerExpression(schedulerGeneration.generateSchedulerExpression(entity));
		} catch (Exception e) {
			e.printStackTrace();
		}
		scheduleConfigRepo.save(entity);
		saveSchedulerInPublicApi(entity);
	}
	
	private void saveSchedulerInPublicApi(ArtScheduleConfig entity) throws Exception {
		JSONObject object = new JSONObject();
		ArtJobDetails jobDetails = artJobDetailsRepository.findById(entity.getJobDetails().getJobId());
		JSONObject processJson = new JSONObject(jobDetails.getProcessJson()).getJSONObject("jobDetails").getJSONObject("callingService");
		object.put("resourceMapping", "/"+processJson.getString("url"));
		object.put("responseContentType", "");
		object.put("requestMethodType", processJson.getString("methodType"));
		object.put("requestContentType", processJson.getString("contentType"));
		object.put("isSystemScheduler", processJson.getBoolean("isSystemScheduler"));
		PublicApi publicApi = new PublicApi();
		publicApi.setApiData(object.toString());
		publicApi.setSchedulerDetails(entity.getSchedulerId());
		publicApiRepository.save(publicApi);
	}

	@Override
	public void update(ArtScheduleConfig entity) throws Exception {
		try {
			entity.setSchedulerExpression(schedulerGeneration.generateSchedulerExpression(entity));
		} catch (atg.taglib.json.util.JSONException e) {
			e.printStackTrace();
		}
		scheduleConfigRepo.update(entity);
		updateSchedulerInPublicApi(entity);
	}
	
	private void updateSchedulerInPublicApi(ArtScheduleConfig entity) throws Exception {
		JSONObject object = new JSONObject();
		ArtJobDetails jobDetails = artJobDetailsRepository.findById(entity.getJobDetails().getJobId());
		JSONObject processJson = new JSONObject(jobDetails.getProcessJson()).getJSONObject("jobDetails").getJSONObject("callingService");
		object.put("resourceMapping", "/"+processJson.getString("url"));
		object.put("responseContentType", "");
		object.put("requestMethodType", processJson.getString("methodType"));
		object.put("requestContentType", processJson.getString("contentType"));
		object.put("isSystemScheduler", processJson.getBoolean("isSystemScheduler"));
		List<PublicApi> publicApis = publicApiRepository.findAll();
		for (PublicApi publicApi : publicApis) {
			JSONObject publicUrlJSON = new JSONObject(publicApi.getApiData());
			boolean isSystemScheduler = publicUrlJSON.has("isSystemScheduler") ? publicUrlJSON.getBoolean("isSystemScheduler") : false;
			if(isSystemScheduler && publicApi.getSchedulerDetails().equals(entity.getSchedulerId())) {
				publicApi.setApiData(object.toString());
				publicApiRepository.update(publicApi);
				break;
			}
		}
	}

	@Override
	public List<ArtScheduleConfig> findAll() {
		return scheduleConfigRepo.findAll();
	}

	@Override
	public ArtScheduleConfig findById(String findKey) throws Exception {
		return scheduleConfigRepo.findById(findKey);
	}

	@Override
	public JSONArray getTreeStores() throws Exception {
		final List<ArtScheduleConfig> lstEntity = scheduleConfigRepo.findAll();
		JSONArray treeStoreJSONArray = new JSONArray();
		for (Iterator<ArtScheduleConfig> lstEntityIterator = lstEntity.iterator(); lstEntityIterator.hasNext();) {
			ArtScheduleConfig entity = (ArtScheduleConfig) lstEntityIterator.next();
			treeStoreJSONArray.add(entity.toTreeNode());
		}
		return treeStoreJSONArray;
	}

	@Override
	public List<ArtJobDetails> findAllJobs() throws Exception {
		try {
			return artJobDetailsRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
