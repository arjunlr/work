package com.philips.itaap.supplychainit.ngh.model.receipt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class ReferenceItemDetail {

    @JsonProperty("poNumber")
    private PoNumber poNumber;

    @JsonProperty("inboundDelivery")
    private InboundDelivery inboundDelivery;


}