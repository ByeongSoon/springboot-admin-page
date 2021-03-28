package com.example.study.repository;


import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
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
    // String sql = "insert into user (%s, %s, %d) value( account, email, age); 예전방식
    User user = new User();
    user.setAccount("TestUser01");
    user.setEmail("TestUser04@gmail.com");
    user.setPhoneNumber("010-1111-3333");
    user.setCreatedAt(LocalDateTime.now());
    user.setCreatedBy("TestUser4");

    User newUser = userRepository.save(user);
    System.out.println("newUser : " + newUser);
  }

  @Test
  public void read() {
    Optional<User> user = userRepository.findById(7L);

    user.ifPresent(selectUser -> {
      System.out.println("user : " + selectUser);
      System.out.println("email : " + selectUser.getEmail());
    });
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

    /*
    Assert.assertTrue(user.isPresent()); // true
     */

    user.ifPresent(selectUser -> {
      userRepository.delete(selectUser);
    });

    // 정상적으로 삭제가 되었는지 확인
    Optional<User> deleteUser = userRepository.findById(3L);

    /*
    Assert.assertFalse(deleteUser.isPresent()); // false
     */

    if (deleteUser.isPresent()) {
      System.out.println("데이터 존재" + deleteUser.get());
    } else {
      System.out.println("데이터 삭제, 데이터 없음");
    }
  }

}
