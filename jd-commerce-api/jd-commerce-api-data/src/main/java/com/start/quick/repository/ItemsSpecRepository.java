package com.start.quick.repository;

import com.start.quick.entity.ItemsSpec;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemsSpecRepository extends CrudRepository<ItemsSpec, String> {

    List<ItemsSpec> findAllByItemId(String itemId);
}
