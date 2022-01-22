package com.start.quick.repository;

import com.start.quick.entity.ItemsParam;
import org.springframework.data.repository.CrudRepository;

public interface ItemsParamRepository extends CrudRepository<ItemsParam, String> {

    ItemsParam findByItemId(String itemId);
}
