package com.app.server.repository.scheduler;
import com.app.shared.scheduler.ArtJobDetails;

import java.util.List;

public interface ArtJobDetailsRepository {

	List<ArtJobDetails> findAll() throws Exception;
	
	public ArtJobDetails findById(String jobId);
}
