package com.start.quick.service.impl;

import com.start.quick.entity.Items;
import com.start.quick.entity.ItemsImg;
import com.start.quick.entity.ItemsParam;
import com.start.quick.entity.ItemsSpec;
import com.start.quick.repository.ItemsImgRepository;
import com.start.quick.repository.ItemsParamRepository;
import com.start.quick.repository.ItemsRepository;
import com.start.quick.repository.ItemsSpecRepository;
import com.start.quick.service.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemsRepository itemsRepository;
    private final ItemsImgRepository itemsImgRepository;
    private final ItemsSpecRepository itemsSpecRepository;
    private final ItemsParamRepository itemsParamRepository;

    public ItemServiceImpl(ItemsRepository itemsRepository, ItemsImgRepository itemsImgRepository, ItemsSpecRepository itemsSpecRepository, ItemsParamRepository itemsParamRepository) {
        this.itemsRepository = itemsRepository;
        this.itemsImgRepository = itemsImgRepository;
        this.itemsSpecRepository = itemsSpecRepository;
        this.itemsParamRepository = itemsParamRepository;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Items findById(String id) {
        return this.itemsRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsImg> findItemImage(String id) {
        return this.itemsImgRepository.findAllByItemId(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsSpec> findItemSpec(String id) {
        return this.itemsSpecRepository.findAllByItemId(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsParam findItemParam(String id) {
        return this.itemsParamRepository.findByItemId(id);
    }
}
