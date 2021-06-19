package com.example.event;

import java.util.Date;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

@Role(Role.Type.EVENT)
@Timestamp("executionTime")
@Expires("2h30m")
public class WarningEvent {
	private static final long serialVersionUID = 1L;
    private Date executionTime;
    private long clientId;
    private String reason;
    
	public WarningEvent() {
		super();
	}
	
	public WarningEvent(Date executionTime, long clientId, String reason) {
		super();
		this.executionTime = executionTime;
		this.clientId = clientId;
		this.reason = reason;
	}

	public Date getExecutionTime() {
		return executionTime;
	}
	public void setExecutionTime(Date executionTime) {
		this.executionTime = executionTime;
	}
	public long getClientId() {
		return clientId;
	}
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.executionTime.getTime() == ((WarningEvent) obj).executionTime.getTime();
	}

}
