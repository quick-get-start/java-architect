package com.start.quick.repository;

import com.start.quick.entity.ItemsImg;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ItemsImgRepository extends CrudRepository<ItemsImg, String> {

    List<ItemsImg> findAllByItemId(String itemId);

    Optional<ItemsImg> findTopByItemIdAndIsMain(String itemId, Integer isMain);
}
