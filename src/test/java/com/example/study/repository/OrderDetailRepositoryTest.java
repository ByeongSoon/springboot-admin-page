package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class OrderDetailRepositoryTest extends StudyApplicationTests {

  @Autowired
  private OrderDetailRepository orderDetailRepository;

  @Test
  public void create() {

    OrderDetail orderDetail = new OrderDetail();

    orderDetail.setOrderAt(LocalDateTime.now());
//    orderDetail.setItemId(1L); // 어떤 아이템??
//    orderDetail.setUserId(7L); // 어떤 사용자??

    OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
//    Assert.assertNotNull(newOrderDetail);

  }

}
