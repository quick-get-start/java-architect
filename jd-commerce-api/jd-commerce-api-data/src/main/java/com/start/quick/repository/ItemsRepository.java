package com.start.quick.repository;

import com.start.quick.entity.Items;
import org.springframework.data.repository.CrudRepository;

public interface ItemsRepository extends CrudRepository<Items, String> {
}
