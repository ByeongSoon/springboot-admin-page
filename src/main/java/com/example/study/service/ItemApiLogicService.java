package com.example.study.service;

import com.example.study.model.entity.Item;
import com.example.study.model.network.Header;
import com.example.study.model.network.Pagination;
import com.example.study.model.network.request.ItemApiRequest;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemApiLogicService extends BaseService<ItemApiRequest, ItemApiResponse,Item> {

  @Autowired
  private PartnerRepository partnerRepository;

  @Override
  public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {

    ItemApiRequest body = request.getData();

    Item item = Item.builder()
        .status(body.getStatus())
        .name(body.getName())
        .title(body.getTitle())
        .content(body.getContent())
        .price(body.getPrice())
        .brandName(body.getBrandName())
        .registeredAt(LocalDateTime.now())
        .partner(partnerRepository.getOne(body.getPartnerId()))
        .build();

//    Item newItem = itemRepository.save(item);
//    return response(newItem);

    return Header.OK(response(baseRepository.save(item)));

  }

  @Override
  public Header<ItemApiResponse> read(Long id) {

    return baseRepository.findById(id)
        .map(this::response)
        .map(Header::OK)
        .orElseGet( () -> Header.ERROR("데이터 없음"));

  }

  @Override
  public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {

    ItemApiRequest itemApiRequest = request.getData();

    return baseRepository.findById(itemApiRequest.getId())
        .map( item -> {
          item
              .setStatus(itemApiRequest.getStatus())
              .setName(itemApiRequest.getName())
              .setTitle(itemApiRequest.getTitle())
              .setContent(itemApiRequest.getContent())
              .setPrice(itemApiRequest.getPrice())
              .setBrandName(itemApiRequest.getBrandName())
              .setRegisteredAt(itemApiRequest.getRegisteredAt())
              .setUnregisteredAt(itemApiRequest.getUnregisteredAt())
          ;
          return item;
        })
        .map( item -> baseRepository.save(item))
        .map(this::response)
        .map(Header::OK)
        .orElseGet( () -> Header.ERROR("데이터 없음"));

  }

  @Override
  public Header delete(Long id) {

    return baseRepository.findById(id)
        .map( item -> {
          baseRepository.delete(item);
          return Header.OK();
        }).orElseGet( () -> Header.ERROR("데이터 없음"));

  }

  public ItemApiResponse response(Item item) {

    String statusTitle = item.getStatus().getTitle(); // 한글로 된 title 불러오는 법

    ItemApiResponse itemApiResponse = ItemApiResponse.builder()
        .id(item.getId())
        .status(item.getStatus())
        .name(item.getName())
        .title(item.getTitle())
        .content(item.getContent())
        .price(item.getPrice())
        .brandName(item.getBrandName())
        .registeredAt(item.getRegisteredAt())
        .unregisteredAt(item.getUnregisteredAt())
        .partnerId(item.getPartner().getId())
        .build();

    return itemApiResponse;

  }

  @Override
  public Header<List<ItemApiResponse>> search(Pageable pageable) {

    Page<Item> items = baseRepository.findAll(pageable);

    List<ItemApiResponse> itemApiResponseList = items.stream()
        .map(this::response)
        .collect(Collectors.toList());

    Pagination pagination = Pagination.builder()
        .totalPage(items.getTotalPages())
        .totalElements(items.getTotalElements())
        .currentPage(items.getNumber())
        .currentElements(items.getNumberOfElements())
        .build();

    return Header.OK(itemApiResponseList, pagination);

  }
}
