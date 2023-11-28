package com.smartpoke.api.common.external.OpenFoodFacts.response;

import com.smartpoke.api.common.external.OpenFoodFacts.dto.ProductOFFDto;
import lombok.Data;

import java.util.List;

@Data
public class OFFResponse {
    private int count;
    private int page;
    private int page_count;
    private int page_size;
    private List<ProductOFFDto> products;

}