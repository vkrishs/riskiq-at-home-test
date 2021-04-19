package com.riskiq.domain;

/**
 * Model to represent service ownership.
 *
 * @author riskiq
 * @created 1/17/18
 */
public class ServiceOwner {

    private String serviceName;
    private String owner;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}