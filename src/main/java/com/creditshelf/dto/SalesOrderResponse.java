package com.creditshelf.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalesOrderResponse {

    private List<SalesOrderDTO> response;
}
