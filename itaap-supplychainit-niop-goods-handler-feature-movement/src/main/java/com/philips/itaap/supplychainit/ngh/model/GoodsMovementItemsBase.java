package com.philips.itaap.supplychainit.ngh.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsMovementItemsBase {

    @JsonProperty("materialId")
    private String materialId;

    @JsonProperty("deliveryPlant")
    private String deliveryPlant;

    @JsonProperty("batch")
    private String batch;

    @JsonProperty("quantity")
    private String quantity;

    @JsonProperty("movementType")
    private String movementType;


}
