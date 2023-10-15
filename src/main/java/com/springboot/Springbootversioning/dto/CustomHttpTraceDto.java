package com.springboot.Springbootversioning.dto;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.TimeZone;

public class CustomHttpTraceDto {
    private String method;
    private String uri;
    private String timestamp; // Formatted timestamp
    // Include other fields as needed

    public CustomHttpTraceDto(String method, String uri, Instant timestamp) {
        this.method = method;
        this.uri = uri;

        // Customize the timestamp formatting
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        sdf.setTimeZone(TimeZone.getTimeZone("America/New_York")); // Adjust the time zone as needed
        this.timestamp = sdf.format(timestamp);
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
