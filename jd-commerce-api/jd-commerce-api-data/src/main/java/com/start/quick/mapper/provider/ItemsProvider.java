package com.start.quick.mapper.provider;

import org.apache.commons.lang3.StringUtils;

public class ItemsProvider {

    public String searchItems(String keyword, String sort) {
        StringBuilder orderBy = new StringBuilder("order by ");
        if (StringUtils.equals(sort, "c")) {
            orderBy.append("item.sell_counts desc");
        } else if (StringUtils.equals(sort, "p")) {
            orderBy.append("spec.price_discount asc");
        } else {
            orderBy.append("item.item_name asc");
        }
        return "select item.id as itemId, item.item_name as itemName, item.sell_counts as sellCounts, image.url as imageUrl, spec.price_discount as price " +
                "from items item " +
                "left join items_img image on item.id = image.item_id " +
                "left join (" +
                "   select item_id, min(price_discount) as price_discount " +
                "   from items_spec " +
                "   group by item_id" +
                ") spec on item.id = spec.item_id " +
                "where image.is_main = 1 and item.item_name like concat('%', #{keyword}, '%') " +
                orderBy;
    }

    public String searchItemsByCategory(Integer categoryId, String sort) {
        StringBuilder orderBy = new StringBuilder("order by ");
        if (StringUtils.equals(sort, "c")) {
            orderBy.append("item.sell_counts desc");
        } else if (StringUtils.equals(sort, "p")) {
            orderBy.append("spec.price_discount asc");
        } else {
            orderBy.append("item.item_name asc");
        }
        return "select item.id as itemId, item.item_name as itemName, item.sell_counts as sellCounts, image.url as imageUrl, spec.price_discount as price " +
                "from items item " +
                "left join items_img image on item.id = image.item_id " +
                "left join (" +
                "   select item_id, min(price_discount) as price_discount " +
                "   from items_spec " +
                "   group by item_id" +
                ") spec on item.id = spec.item_id " +
                "where image.is_main = 1 and item.category_id = #{categoryId} " +
                orderBy;
    }
}
