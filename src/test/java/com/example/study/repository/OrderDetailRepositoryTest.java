package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderDetail;
import com.example.study.model.enumclass.OrderDetailStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class OrderDetailRepositoryTest extends StudyApplicationTests {

  @Autowired
  private OrderDetailRepository orderDetailRepository;

  @Test
  public void create() {
    OrderDetail orderDetail = new OrderDetail();

    orderDetail.setStatus(OrderDetailStatus.ARRIVE);
    orderDetail.setArrivalDate(LocalDateTime.now().plusDays(2));
    orderDetail.setQuantity(1);
    orderDetail.setTotalPrice(BigDecimal.valueOf(900000));
//    orderDetail.setOrderGroupId(1L); // 어떠한 장바구니에
//    orderDetail.setItemId(1L); // 어떠한 상품
    orderDetail.setCreatedAt(LocalDateTime.now());
    orderDetail.setCreatedBy("AdminServer");

//    OrderDetail newOrderDetail = new OrderDetail();
//    newOrderDetail = orderDetailRepository.save(orderDetail);
//
//    Assertions.assertNotNull(newOrderDetail);


  }

  @Test
  public void read() {

    Optional<OrderDetail> newOrderDetail = orderDetailRepository.findById(2L);

    Assertions.assertNotNull(newOrderDetail);

  }

}
