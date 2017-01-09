package com.app.shared.scheduler;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class SchedulerGeneration {

	public String generateSchedulerExpression(ArtScheduleConfig awsScehduleConfig) throws JSONException {
		return parseJson(awsScehduleConfig.getScheduleStrategy());
	}

	public String parseJson(String json) throws JSONException {

		CronExpression expression = new CronExpression();
		JSONObject customJson = new JSONObject(json);
		Date startDate = new Date();
		Date endDate = new Date();

		if (customJson.has("custom")) {
			JSONArray customJsonArray = customJson.getJSONArray("custom");
			for (int i = 0; i < customJsonArray.length(); i++) {
				JSONObject customArray = customJsonArray.getJSONObject(i);
				if (customArray.has("month") && customArray.getString("month") != null) {
					expression.setMonthRange(customArray.getString("february"));
				}
				if (customArray.has("day") && customArray.getString("day") != null) {
					expression.setDayOfWeek(customArray.getString("day"));
				}
				if (customArray.has("hour") && customArray.getString("hour") != null) {
					expression.setHoursRange(customArray.getString("hour"));
				}
				if (customArray.has("minute") && customArray.getString("minute") != null) {
					expression.setMinutesRange(customArray.getString("minute"));
				}

			}
			String expr = expression.build();
			return expr;
		} else {
			if (customJson.has("startDate") && customJson.getString("startDate") != null) {
				startDate = new Date(customJson.getString("startDate"));
			}
			if (customJson.has("endDate") && customJson.getString("endDate") != null && !customJson.getString("endDate").isEmpty()) {
				endDate = new Date(customJson.getString("endDate"));
			}
			if (customJson.has("repeatable") && customJson.getBoolean("repeatable")) {
				if (endDate.after(startDate)) {

					if (endDate.getDate() > startDate.getDate()) {
						expression.setDayOfMonthRange(startDate.getDate() + "-" + endDate.getDate());
					} else if (endDate.getDate() < startDate.getDate()) {
						expression.setDayOfMonthRange(endDate.getDate() + "-" + startDate.getDate());
					} else {
						expression.setDayOfMonthRange(endDate.getDay() + "");
					}
					if (endDate.getMonth() > startDate.getMonth()) {
						expression.setMonthRange(endDate.getMonth() + 1 + "-" + startDate.getMonth() + 1);
					} else {
						expression.setMonthRange(endDate.getMonth() + 1 + "");
					}
					if (endDate.getYear() > startDate.getYear()) {
						expression.setYearRange(endDate.getYear() + 1900 + "-" + startDate.getYear() + 1900);
					} else {
						expression.setYearRange(endDate.getYear() + 1900 + "");
					}
				}
			}
			if (customJson.has("infinite") && customJson.getBoolean("infinite")) {
			}
			if (customJson.has("minute")) {
				expression.setMinutesRange(customJson.getString("minute"));
			}
			if (customJson.has("hour") && customJson.getString("hour") != null) {
				expression.setHoursRange(customJson.getString("hour"));
			}
			if (customJson.has("simple") && customJson.getString("simple") != null) {
				if (customJson.getString("simple").equalsIgnoreCase("hourly")) {
					expression.setHoursRange("0/1");
					expression.anyDayOfWeek();
					expression.setSeconds(0);
				}
				if (customJson.getString("simple").equalsIgnoreCase("weekly")) {
					expression.setDayOfWeek("0/7");
					expression.setSeconds(0);
				}
				if (customJson.getString("simple").equalsIgnoreCase("daily")) {
					expression.anyDayOfWeek();
					expression.setSeconds(0);
				}
				if (customJson.getString("simple").equalsIgnoreCase("monthly")) {
					expression.setMonthRange("0/1");
					expression.setSeconds(0);
				}
				if (customJson.getString("simple").equalsIgnoreCase("yearly")) {
					expression.setYearRange("0/1");
					expression.setSeconds(0);
				}
				if (customJson.getString("simple").equalsIgnoreCase("lastWeekdayOfMonth")) {
					expression.lastWeekDayOfMonth();
					expression.setSeconds(0);
				}
				if (customJson.getString("simple").equalsIgnoreCase("quaterly")) {
					expression.setMonthRange("1/3");
					expression.setSeconds(0);
				}
			}
			String expr = expression.build();
			return expr;
		}

	}
}
