package com.app.server.repository.scheduler;
import com.app.shared.scheduler.ArtScheduleConfig;

import java.util.List;

public interface ArtScheduleConfigRepository {

	void save(ArtScheduleConfig entity);

	void update(ArtScheduleConfig entity);

	List<ArtScheduleConfig> findAll();

	ArtScheduleConfig findById(String findKey);

}
