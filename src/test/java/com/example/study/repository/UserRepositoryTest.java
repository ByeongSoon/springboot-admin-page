package com.example.study.repository;


import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {

  // Dependency Injection (DI)
  // JpaRepository 를 상속받으면 기본적인 CRUD를 제공한다.
  @Autowired
  private UserRepository userRepository;

  @Test
  public void create() {
    String account = "Test01";
    String password = "Test01";
    String status = "REGISTERED";
    String email = "Test01@gmail.com";
    String phoneNumber = "010-1111-2222";
    LocalDateTime registeredAt = LocalDateTime.now();
    LocalDateTime createdAt = LocalDateTime.now();
    String createdBy = "AdminServer";

    User user = new User();
    user.setAccount(account);
    user.setPassword(password);
    user.setStatus(status);
    user.setEmail(email);
    user.setPhoneNumber(phoneNumber);
    user.setRegisteredAt(registeredAt);
    user.setCreatedAt(createdAt);
    user.setCreatedBy(createdBy);

    User newUser = userRepository.save(user);
    Assertions.assertNotNull(newUser);

  }

  @Test
  @Transactional
  public void read() {
    User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");
    Assertions.assertNotNull(user);
  }

  @Test
  // Test 후에 RollBack을 해서 테이블에 영향을 주지않
  @Transactional
  public void update() {
    Optional<User> user = userRepository.findById(7L);

    user.ifPresent(selectUser -> {
      selectUser.setAccount("jang");
      selectUser.setUpdatedAt(LocalDateTime.now());
      selectUser.setUpdatedBy("update method()");

      // save()에 들어가는 ID 값이 있으면 update, 없으면 insert로 jpa에서 동작한다.
      userRepository.save(selectUser);
    });
  }

  @Test
  @Transactional
  public void delete() {
    Optional<User> user = userRepository.findById(7L);

    Assertions.assertTrue(user.isPresent());

    user.ifPresent(selectUser -> {
      userRepository.delete(selectUser);
    });

    // 정상적으로 삭제가 되었는지 확인
    Optional<User> deleteUser = userRepository.findById(3L);

    Assertions.assertFalse(deleteUser.isPresent());

    if (deleteUser.isPresent()) {
      System.out.println("데이터 존재" + deleteUser.get());
    } else {
      System.out.println("데이터 삭제, 데이터 없음");
    }
  }

}
