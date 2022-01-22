package com.start.quick.mapper;

import com.start.quick.model.ItemsSearchModel;
import com.start.quick.mapper.provider.ItemsProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface ItemsMapper {

    @SuppressWarnings("UnusedReturnValue")
    @SelectProvider(type = ItemsProvider.class, method = "searchItems")
    List<ItemsSearchModel> searchItems(String keyword, String sort);
}
