package com.riskiq.controller;

import com.riskiq.domain.MetricRows;
import com.riskiq.service.MetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/metrics")
public class MetricsController {

    @Autowired
    MetricsService metricsService;

    @GetMapping
    public ResponseEntity allMetrics() {
        List<MetricRows> metricRowsList = new ArrayList<>();
        final Map<String, Long> extractRowCounts = metricsService.extractRowCounts();
        for (Map.Entry<String, Long> entry : extractRowCounts.entrySet()) {
            MetricRows metricRows = new MetricRows();
            metricRows.setRepository(entry.getKey());
            metricRows.setRows(entry.getValue());
            metricRowsList.add(metricRows);
        }
        if (metricRowsList.isEmpty()) {
            return new ResponseEntity<>(metricRowsList, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(metricRowsList, HttpStatus.OK);
    }
}
