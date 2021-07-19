package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.AdminUser;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.AdminUserApiRequest;
import com.example.study.model.network.response.AdminUserApiResponse;
import com.example.study.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminUserApiLogicService extends BaseService<AdminUserApiRequest, AdminUserApiResponse,AdminUser> {

  @Override
  public Header<AdminUserApiResponse> create(Header<AdminUserApiRequest> request) {

    AdminUserApiRequest body = request.getData();

    AdminUser adminUser = AdminUser.builder()
        .account(body.getAccount())
        .password(body.getPassword())
        .status(body.getStatus())
        .role(body.getRole())
        .registeredAt(LocalDateTime.now())
        .build();

    return response(baseRepository.save(adminUser));

  }

  @Override
  public Header<AdminUserApiResponse> read(Long id) {

    return baseRepository.findById(id)
        .map(this::response)
        .orElseGet(() -> Header.ERROR("데이터 없음"));

  }

  @Override
  public Header<AdminUserApiResponse> update(Header<AdminUserApiRequest> request) {

    AdminUserApiRequest body = request.getData();

    return baseRepository.findById(body.getId())
        .map(adminUser -> {
          adminUser
              .setAccount(body.getAccount())
              .setPassword(body.getPassword())
              .setStatus(body.getStatus())
              .setRole(body.getRole())
              .setLastLoginAt(body.getLastLoginAt())
              .setPasswordUpdatedAt(body.getPasswordUpdateAt())
              .setLoginFailCount(body.getLoginFailCount())
              .setRegisteredAt(body.getRegisteredAt())
              .setUnregisteredAt(body.getUnregisteredAt());

          return adminUser;
        })
        .map( adminUser -> baseRepository.save(adminUser))
        .map(this::response)
        .orElseGet(() -> Header.ERROR("데이터 없음"));


  }

  @Override
  public Header delete(Long id) {

    return baseRepository.findById(id)
        .map( adminUser -> {
          baseRepository.delete(adminUser);
          return Header.OK();
        })
        .orElseGet(() -> Header.ERROR("데이터 없음"));


  }

  private Header<AdminUserApiResponse> response(AdminUser adminUser) {

    AdminUserApiResponse body = AdminUserApiResponse.builder()
        .account(adminUser.getAccount())
        .password(adminUser.getPassword())
        .status(adminUser.getStatus())
        .role(adminUser.getRole())
        .lastLoginAt(adminUser.getLastLoginAt())
        .passwordUpdateAt(adminUser.getPasswordUpdatedAt())
        .loginFailCount(adminUser.getLoginFailCount())
        .registeredAt(adminUser.getRegisteredAt())
        .unregisteredAt(adminUser.getUnregisteredAt())
        .build();

    return Header.OK(body);
  }

  @Override
  public Header<List<AdminUserApiResponse>> search(Pageable pageable) {
    return null;
  }
}
