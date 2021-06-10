package com.example.event;

import java.io.Serializable;
import java.util.Date;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import com.example.model.Klijent;
import com.example.model.TipTransakcije;

@Role(Role.Type.EVENT)
@Timestamp("executionTime")
@Expires("2h30m")
public class TransactionEvent implements Serializable {

    private static final long serialVersionUID = 1L;
    private Date executionTime;
    private Klijent client;
    private Double totalAmount;
    private TipTransakcije tip;

    public TransactionEvent() {
        super();
    }
    
    public TransactionEvent(Klijent client, Double totalAmount, TipTransakcije tip) {
        super();
        this.executionTime = new Date();
        this.client = client;
        this.totalAmount = totalAmount;
        this.tip = tip;
    }

    public Date getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Date executionTime) {
        this.executionTime = executionTime;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

	public Klijent getClient() {
		return client;
	}

	public void setClient(Klijent client) {
		this.client = client;
	}

	public TipTransakcije getTip() {
		return tip;
	}

	public void setTip(TipTransakcije tip) {
		this.tip = tip;
	}
    
    
}

