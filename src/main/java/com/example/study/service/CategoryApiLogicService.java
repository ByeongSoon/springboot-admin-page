package com.example.study.service;

import com.example.study.model.entity.Category;
import com.example.study.model.network.Header;
import com.example.study.model.network.Pagination;
import com.example.study.model.network.request.CategoryApiRequest;
import com.example.study.model.network.response.CategoryApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryApiLogicService extends BaseService<CategoryApiRequest, CategoryApiResponse,Category> {

  @Override
  public Header<CategoryApiResponse> create(Header<CategoryApiRequest> request) {

    CategoryApiRequest body = request.getData();

    Category category = Category.builder()
        .type(body.getType())
        .title(body.getTitle())
        .build();

    return Header.OK(response(baseRepository.save(category)));

  }

  @Override
  public Header<CategoryApiResponse> read(Long id) {

    return baseRepository.findById(id)
        .map(this::response)
        .map(Header::OK)
        .orElseGet(() -> Header.ERROR("데이터 없음"));

  }

  @Override
  public Header<CategoryApiResponse> update(Header<CategoryApiRequest> request) {

    CategoryApiRequest body = request.getData();

    return baseRepository.findById(body.getId())
        .map( category -> {
          category
              .setType(body.getType())
              .setTitle(body.getTitle());

          return category;
        })
        .map( category -> response(baseRepository.save(category)))
//        .map( category -> categoryRepository.save(category))
//        .map(this::response)
        .map(Header::OK)
        .orElseGet(() -> Header.ERROR("데이터 없음"));

  }

  @Override
  public Header delete(Long id) {

    return baseRepository.findById(id)
        .map( category -> {
          baseRepository.delete(category);
          return Header.OK();
        })
        .orElseGet(() -> Header.ERROR("데이터 없음"));

  }

  private CategoryApiResponse response(Category category) {

    CategoryApiResponse categoryApiResponse = CategoryApiResponse.builder()
        .id(category.getId())
        .type(category.getType())
        .title(category.getTitle())
        .build();

    return categoryApiResponse;

  }

  @Override
  public Header<List<CategoryApiResponse>> search(Pageable pageable) {
    Page<Category> categories = baseRepository.findAll(pageable);

    List<CategoryApiResponse> categoryApiResponseList = categories.stream()
        .map(this::response)
        .collect(Collectors.toList());

    Pagination pagination = Pagination.builder()
        .totalPage(categories.getTotalPages())
        .totalElements(categories.getTotalElements())
        .currentPage(categories.getNumber())
        .currentElements(categories.getNumberOfElements())
        .build();

    return Header.OK(categoryApiResponseList, pagination);
  }
}
