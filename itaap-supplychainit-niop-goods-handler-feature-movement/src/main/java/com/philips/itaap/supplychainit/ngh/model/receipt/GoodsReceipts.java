package com.philips.itaap.supplychainit.ngh.model.receipt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class GoodsReceipts {

    @JsonProperty("DeliveryNote")
    private String deliveryNote;

    @JsonProperty("docDate")
    private String docDate;

    @JsonProperty("grDate")
    private String grDate;

    @JsonProperty("billOfLading")
    private String billOfLading;

    @JsonProperty("headerText")
    private String headerText;

    @JsonProperty("grIRSlip")
    private String grIRSlip;

    @JsonProperty("goodsReceiptItems")
    private List<GoodsReceiptItem> goodsReceiptItems;


}
