package com.app.shared.scheduler;
import java.sql.Timestamp;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;

@Entity
@Table(name = "art_job_details")
@JsonIdentityInfo(generator = PropertyGenerator.class, property = "jobId")
public class ArtJobDetails {

	@Id
	@GeneratedValue(generator = "UUIDGenerator")
	@Column(name = "jobId")
	private String jobId;
	@Column(name = "jobName")
	private String jobName;
	@Column(name = "beanName")
	private String beanName;
	@Column(name = "currentStatus")
	private String currentStatus;
	@Column(name = "processJson")
	private String processJson;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "artJobDetails")
	private ArtScheduleConfig artScheduleConfig;

	@Column(updatable = true)
	private Timestamp statusTime = new Timestamp(System.currentTimeMillis());
	@Transient
	private Map<String, Object> jobParams;
	@Transient
	private final String JOB_STARTED = "JOB_STARTED";
	@Transient
	private final String JOB_FINISHED = "JOB_FINISHED";
	@Transient
	private final String JOB_FAILED = "JOB_FAILED";
	@Transient
	private final String JOB_ALREADY_RUNNING = "JOB_ALREADY_RUNNING";

	public String getJobId() {
		return jobId;
	}

	public void setJobId(final String jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(final String jobName) {
		this.jobName = jobName;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(final String beanName) {
		this.beanName = beanName;
	}

	public void setJobParams(final Map<String, Object> jobParams) {
		this.jobParams = jobParams;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(final String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public void setJobStarted() {
		this.currentStatus = JOB_STARTED;
	}

	public void setJobFinished() {
		this.currentStatus = JOB_FINISHED;
	}

	public void setJobFailed() {
		this.currentStatus = JOB_FAILED;
	}

	public void setJobAlreadyRunning() {
		this.currentStatus = JOB_ALREADY_RUNNING;
	}

	public ArtScheduleConfig getScheduler() {
		return artScheduleConfig;
	}

	public void setScheduler(final ArtScheduleConfig artScheduleConfig) {
		this.artScheduleConfig = artScheduleConfig;
	}
	
	public String getProcessJson() {
		return processJson;
	}
	
	public void setProcessJson(final String processJson) {
		this.processJson = processJson;
	}
}
