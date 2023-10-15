package com.springboot.Springbootversioning.configurations;
import com.springboot.Springbootversioning.dto.CustomHttpTraceDto;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CustomHttpTraceRepository extends InMemoryHttpTraceRepository {
    // This class is nbot being used anywhere as of now
    public List<CustomHttpTraceDto> getCustomHttpTraces() {
        List<HttpTrace> traces = super.findAll();

        return traces.stream()
                .map(httpTrace -> new CustomHttpTraceDto(
                        httpTrace.getRequest().getMethod(),
                        httpTrace.getRequest().getUri().toString(),
                        httpTrace.getTimestamp()))
                .collect(Collectors.toList());
    }
}

