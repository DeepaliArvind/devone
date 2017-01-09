package com.app.shared.scheduler;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;

@Entity
@Table(name = "art_schedule_config")
public class ArtScheduleConfig {

	@Id
	@GeneratedValue(generator = "UUIDGenerator")
	@Column(name = "schedule_id")
	private String schedulerId;

	@Column(name = "schedule_name")
	private String scheduleName;

	@Column(name = "scheduler_expression")
	private String schedulerExpression;

	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "schedule_job", referencedColumnName = "jobId")
	private ArtJobDetails artJobDetails;

	@Column(name = "schedule_strategy")
	private String scheduleStrategy;

	public String getSchedulerId() {
		return schedulerId;
	}

	public void setSchedulerId(final String schedulerId) {
		this.schedulerId = schedulerId;
	}

	public String getScheduleName() {
		return scheduleName;
	}

	public void setScheduleName(final String scheduleName) {
		this.scheduleName = scheduleName;
	}

	public String getSchedulerExpression() {
		return schedulerExpression;
	}

	public void setSchedulerExpression(final String schedulerExpression) {
		this.schedulerExpression = schedulerExpression;
	}

	public ArtJobDetails getJobDetails() {
		return artJobDetails;
	}

	public void setJobDetails(final ArtJobDetails artJobDetails) {
		this.artJobDetails = artJobDetails;
	}

	public String getScheduleStrategy() {
		return scheduleStrategy;
	}

	public void setScheduleStrategy(final String scheduleStrategy) {
		this.scheduleStrategy = scheduleStrategy;
	}

	public JSONObject toTreeNode() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("id", this.schedulerId);
		json.put("name", this.scheduleName);
		json.put("text", this.scheduleName);
		json.put("leaf", true);
		json.put("icon", "images/table_edit_icon.png");
		return json;
	}

}
