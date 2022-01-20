package com.start.quick.repository;

import com.start.quick.entity.ItemsImg;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemsImgRepository extends CrudRepository<ItemsImg, String> {

    List<ItemsImg> findAllByItemId(String itemId);
}
