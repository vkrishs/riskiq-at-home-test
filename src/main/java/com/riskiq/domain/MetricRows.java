package com.riskiq.domain;

public class MetricRows {

    private String repository;
    private Long rows;

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public Long getRows() {
        return rows;
    }

    public void setRows(Long rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "MetricRows{" +
                "repository='" + repository + '\'' +
                ", rows=" + rows +
                '}';
    }
}
