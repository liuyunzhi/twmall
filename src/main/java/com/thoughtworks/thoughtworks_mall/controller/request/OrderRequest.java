package com.thoughtworks.thoughtworks_mall.controller.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {

    private List<OrderItemRequest> orderItemRequests;
}
