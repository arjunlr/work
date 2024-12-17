package com.philips.itaap.supplychainit.ngh.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.logstash.logback.encoder.org.apache.commons.lang3.StringUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class ApiHeader {

    @Builder.Default
    @JsonProperty("correlationID")
    private String correlationId = StringUtils.EMPTY;

    @Builder.Default
    @JsonProperty("recordName")
    private String recordName = StringUtils.EMPTY;

    @JsonProperty("recordId")
    private String recordId;

    @JsonProperty("consumer")
    private String consumer;

    @JsonProperty("provider")
    private String provider;

    @Builder.Default
    @JsonProperty("consumerTimestamp")
    private String consumerTimestamp = StringUtils.EMPTY;

    @Builder.Default
    @JsonProperty("testMode")
    private String testMode = StringUtils.EMPTY;

    @JsonProperty("info1")
    private String info1;

    @Builder.Default
    @JsonProperty("info2")
    private String info2 = StringUtils.EMPTY;

    @Builder.Default
    @JsonProperty("info3")
    private String info3 = StringUtils.EMPTY;


}
