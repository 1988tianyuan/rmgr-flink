package com.liugeng.flinkdemo;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * @author Liu Geng liu.geng@navercorp.com
 * @date 2020/4/2 14:44
 */
public class ResourceAggResult {
	
	public ResourceAggResult(String memberNo, Long timestamp, Map<String, Integer> countMap, Long waterMark) {
		this.memberNo = memberNo;
		this.timestamp = timestamp;
		this.countMap = countMap;
		this.waterMark = waterMark;
	}
	
	private String memberNo;
	
	private Long timestamp;
	
	private Long windowStart;
	
	private Long windowEnd;
	
	private Map<String, Integer> countMap;
	
	private Long waterMark;
	
	private Long processingTime;
	
	public String getMemberNo() {
		return memberNo;
	}
	
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	
	public Long getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
	public Map<String, Integer> getCountMap() {
		return countMap;
	}
	
	public void setCountMap(Map<String, Integer> countMap) {
		this.countMap = countMap;
	}
	
	public Long getWaterMark() {
		return waterMark;
	}
	
	public void setWaterMark(Long waterMark) {
		this.waterMark = waterMark;
	}
	
	public Long getWindowStart() {
		return windowStart;
	}
	
	public void setWindowStart(Long windowStart) {
		this.windowStart = windowStart;
	}
	
	public Long getWindowEnd() {
		return windowEnd;
	}
	
	public void setWindowEnd(Long windowEnd) {
		this.windowEnd = windowEnd;
	}
	
	public Long getProcessingTime() {
		return processingTime;
	}
	
	public void setProcessingTime(Long processingTime) {
		this.processingTime = processingTime;
	}
	
	@Override
	public String toString() {
		return "ResourceAggResult{" +
			"memberNo='" + memberNo + '\'' +
			", timestamp=" + toFormattedDate(timestamp) +
			", processingTime=" + toFormattedDate(processingTime) +
			", countMap=" + countMap +
//			", waterMark=" + toFormattedDate(waterMark) +
			", windowStart=" + toFormattedDate(windowStart) +
			", windowEnd=" + toFormattedDate(windowEnd) +
			'}';
	}
	
	private String toFormattedDate(long epochMilli) {
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneId.systemDefault()).format(
			DateTimeFormatter.ISO_TIME
		);
	}
}
