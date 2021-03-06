package com.example.study.service;

import com.example.study.model.entity.OrderDetail;
import com.example.study.model.network.Header;
import com.example.study.model.network.Pagination;
import com.example.study.model.network.request.OrderDetailApiRequest;
import com.example.study.model.network.response.OrderDetailApiResponse;
import com.example.study.repository.ItemRepository;
import com.example.study.repository.OrderGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailApiLogicService extends BaseService<OrderDetailApiRequest, OrderDetailApiResponse,OrderDetail> {

  @Autowired
  private ItemRepository itemRepository;

  @Autowired
  private OrderGroupRepository orderGroupRepository;

  @Override
  public Header<OrderDetailApiResponse> create(Header<OrderDetailApiRequest> request) {

    OrderDetailApiRequest body = request.getData();

    OrderDetail orderDetail = OrderDetail.builder()
        .status(body.getStatus())
        .arrivalDate(LocalDateTime.now().plusDays(2))
        .quantity(body.getQuantity())
        .totalPrice(body.getTotalPrice())
        .item(itemRepository.getOne(body.getItemId()))
        .orderGroup(orderGroupRepository.getOne(body.getOrderGroupId()))
        .build();

    return Header.OK(response(baseRepository.save(orderDetail)));

  }

  @Override
  public Header<OrderDetailApiResponse> read(Long id) {

    return baseRepository.findById(id)
        .map(this::response)
        .map(Header::OK)
        .orElseGet(() -> Header.ERROR("데이터 없음"));

  }

  @Override
  public Header<OrderDetailApiResponse> update(Header<OrderDetailApiRequest> request) {

    OrderDetailApiRequest body = request.getData();

    return baseRepository.findById(body.getId())
        .map( orderDetail -> {
          orderDetail
              .setStatus(body.getStatus())
              .setArrivalDate(body.getArrivalDate())
              .setQuantity(body.getQuantity())
              .setTotalPrice(body.getTotalPrice())
              .setItem(itemRepository.getOne(body.getItemId()))
              .setOrderGroup(orderGroupRepository.getOne(body.getOrderGroupId()));
          return orderDetail;
        })
        .map( orderDetail -> baseRepository.save(orderDetail))
        .map(this::response)
        .map(Header::OK)
        .orElseGet(() -> Header.ERROR("데이터 없음"));

  }

  @Override
  public Header delete(Long id) {

    return baseRepository.findById(id)
        .map(orderDetail -> {
          baseRepository.delete(orderDetail);
          return Header.OK();
        })
        .orElseGet(() -> Header.ERROR("데이터 없음"));

  }

  private OrderDetailApiResponse response(OrderDetail orderDetail) {

    OrderDetailApiResponse orderDetailApiResponse = OrderDetailApiResponse.builder()
        .id(orderDetail.getId())
        .status(orderDetail.getStatus())
        .arrivalDate(orderDetail.getArrivalDate())
        .quantity(orderDetail.getQuantity())
        .totalPrice(orderDetail.getTotalPrice())
        .itemId(orderDetail.getItem().getId())
        .orderGroupId(orderDetail.getOrderGroup().getId())
        .build();

    return orderDetailApiResponse;

  }

  @Override
  public Header<List<OrderDetailApiResponse>> search(Pageable pageable) {
    Page<OrderDetail> orderDetails = baseRepository.findAll(pageable);

    List<OrderDetailApiResponse> orderDetailApiResponseList = orderDetails.stream()
        .map(this::response)
        .collect(Collectors.toList());

    Pagination pagination = Pagination.builder()
        .totalPage(orderDetails.getTotalPages())
        .totalElements(orderDetails.getTotalElements())
        .currentPage(orderDetails.getNumber())
        .currentElements(orderDetails.getNumberOfElements())
        .build();

    return Header.OK(orderDetailApiResponseList, pagination);
  }
}
