package com.start.quick.mapper;

import com.start.quick.model.ItemsSearchModel;
import com.start.quick.mapper.provider.ItemsProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ItemsMapper {

    @SuppressWarnings("UnusedReturnValue")
    @SelectProvider(type = ItemsProvider.class, method = "searchItems")
    List<ItemsSearchModel> searchItems(String keyword, String sort);

    @SelectProvider(type = ItemsProvider.class, method = "searchItemsByCategory")
    List<ItemsSearchModel> searchItemsByCategory(Integer categoryId, String sort);

    @Update("update items_spec " +
            "set stock = stock - #{counts} " +
            "where id = #{specId} " +
            "and stock >= #{counts}")
    int decreaseItemSpecStock(String specId, int counts);
}
