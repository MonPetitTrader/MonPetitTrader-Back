package com.mpt.backend.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class _CompanyPerformanceHistoryId implements Serializable {
	private String cieId;
	private Date date;
	
    public _CompanyPerformanceHistoryId() {}
	
	public _CompanyPerformanceHistoryId(String cieId, Date ciePerformanceDate) {
		this.cieId = cieId;
		this.date = ciePerformanceDate;
	}

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

	@Override
	public int hashCode() {
		return Objects.hash(cieId, date);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		_CompanyPerformanceHistoryId other = (_CompanyPerformanceHistoryId) obj;
		return Objects.equals(cieId, other.cieId) && Objects.equals(date, other.date);
	}
	
	
}
