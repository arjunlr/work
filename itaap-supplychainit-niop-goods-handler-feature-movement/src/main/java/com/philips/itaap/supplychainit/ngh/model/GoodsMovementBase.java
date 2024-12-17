package com.philips.itaap.supplychainit.ngh.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.philips.itaap.supplychainit.ngh.model.movement.inputpayload.GoodsMovementItems;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsMovementBase {

    @JsonProperty("refDocNumber")
    private String refDocNumber;

    @JsonProperty("docDate")
    private String docDate;

    @JsonProperty("postingDate")
    private String postingDate;

    @JsonProperty("headerText")
    private String headerText;

    @JsonProperty("goodsMovementItems")
    private List<GoodsMovementItems> goodsMovementItems;

}
