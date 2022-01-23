package com.start.quick.repository;

import com.start.quick.entity.Items;
import com.start.quick.model.ItemsCartModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemsRepository extends CrudRepository<Items, String> {

    @Query("select new com.start.quick.model.ItemsCartModel(item.id, image.url, item.itemName, spec.id, spec.name, spec.priceDiscount, spec.priceNormal) " +
            "from ItemsSpec spec " +
            "left join Items item on spec.itemId = item.id " +
            "left join ItemsImg image on image.itemId = item.id " +
            "where image.isMain = 1 and spec.id in :specIds")
    List<ItemsCartModel> refreshItems(List<String> specIds);
}
