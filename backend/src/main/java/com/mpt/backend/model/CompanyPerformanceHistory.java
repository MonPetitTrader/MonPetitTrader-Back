package com.mpt.backend.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@IdClass(_CompanyPerformanceHistoryId.class)
@Table(name = "cie_performance_hist")
public class CompanyPerformanceHistory {

	@Id
	private String cieId;

	@Id
	@Temporal(TemporalType.DATE)
	private Date date;

	private float open;

	private float close;

	private float high;

	private float low;

	private int volume;

	public String getCieId() {
		return cieId;
	}

	public void setCieId(String cieId) {
		this.cieId = cieId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getOpen() {
		return open;
	}

	public void setOpen(float open) {
		this.open = open;
	}

	public float getClose() {
		return close;
	}

	public void setClose(float close) {
		this.close = close;
	}

	public float getHigh() {
		return high;
	}

	public void setHigh(float high) {
		this.high = high;
	}

	public float getLow() {
		return low;
	}

	public void setLow(float low) {
		this.low = low;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

}
