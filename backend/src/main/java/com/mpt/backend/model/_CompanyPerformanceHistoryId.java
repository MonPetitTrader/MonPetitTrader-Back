package com.mpt.backend.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class _CompanyPerformanceHistoryId implements Serializable {
	private String cieId;
	private Date date;
	
	public _CompanyPerformanceHistoryId(String cieId, Date ciePerformanceDate) {
		this.cieId = cieId;
		this.date = ciePerformanceDate;
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
