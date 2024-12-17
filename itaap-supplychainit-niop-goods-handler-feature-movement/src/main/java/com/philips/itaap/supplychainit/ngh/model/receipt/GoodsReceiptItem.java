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
public class GoodsReceiptItem {

    @JsonProperty("materialId")
    private String materialId;

    @JsonProperty("deliveryPlant")
    private String deliveryPlant;

    @JsonProperty("storageLocation")
    private String storageLocation;

    @JsonProperty("goodsReceiptQuantity")
    private String goodsReceiptQuantity;

    @JsonProperty("goodsReceiptUom")
    private String goodsReceiptUom;

    @JsonProperty("referenceItemDetail")
    private ReferenceItemDetail referenceItemDetail;

    @JsonProperty("batchItemDetails")
    private List<BatchItemDetail> batchItemDetails;

    @JsonProperty("serialNumberDetails")
    private List<SerialNumberDetail> serialNumberDetails;


}
