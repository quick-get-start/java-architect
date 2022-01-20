package com.start.quick.service.impl;

import com.start.quick.domain.ItemCommentsViewModel;
import com.start.quick.domain.StatisticsViewModel;
import com.start.quick.entity.Items;
import com.start.quick.entity.ItemsImg;
import com.start.quick.entity.ItemsParam;
import com.start.quick.entity.ItemsSpec;
import com.start.quick.enums.CommentLevel;
import com.start.quick.model.CommentLevelCountModel;
import com.start.quick.model.ItemCommentsModel;
import com.start.quick.repository.*;
import com.start.quick.service.ItemService;
import com.start.quick.utils.DesensitizationUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemsRepository itemsRepository;
    private final ItemsImgRepository itemsImgRepository;
    private final ItemsSpecRepository itemsSpecRepository;
    private final ItemsParamRepository itemsParamRepository;
    private final ItemsCommentsRepository itemsCommentsRepository;

    public ItemServiceImpl(ItemsRepository itemsRepository, ItemsImgRepository itemsImgRepository, ItemsSpecRepository itemsSpecRepository, ItemsParamRepository itemsParamRepository, ItemsCommentsRepository itemsCommentsRepository) {
        this.itemsRepository = itemsRepository;
        this.itemsImgRepository = itemsImgRepository;
        this.itemsSpecRepository = itemsSpecRepository;
        this.itemsParamRepository = itemsParamRepository;
        this.itemsCommentsRepository = itemsCommentsRepository;
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

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public CommentLevelCountModel findCommentLevelCount(String itemId) {
        CommentLevelCountModel result = new CommentLevelCountModel();

        List<StatisticsViewModel> statisticsViewModels = this.itemsCommentsRepository.statisticsByItemId(itemId);
        for (StatisticsViewModel statisticsViewModel : statisticsViewModels) {
            Integer level = statisticsViewModel.getLevel();
            Integer statistics = statisticsViewModel.getStatistics();
            if (CommentLevel.GOOD.equals(level)) {
                result.setGoodCounts(statistics);
            } else if (CommentLevel.NORMAL.equals(level)) {
                result.setNormalCounts(statistics);
            } else if (CommentLevel.BAD.equals(level)) {
                result.setBadCounts(statistics);
            }
        }

        result.setTotalCounts(result.computeTotalCounts());
        return result;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Page<ItemCommentsModel> pageAll(String itemId, Integer level, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<ItemCommentsViewModel> viewModels;
        if (level != null) {
            viewModels = this.itemsCommentsRepository.pageAll(itemId, level, pageable);
        } else {
            viewModels = this.itemsCommentsRepository.pageAll(itemId, pageable);
        }

        List<ItemCommentsModel> content = new ArrayList<>();
        for (ItemCommentsViewModel viewModel : viewModels) {
            content.add(
                    new ItemCommentsModel(
                            viewModel.getCommentLevel(),
                            viewModel.getContent(),
                            viewModel.getSpecName(),
                            viewModel.getCreateTime(),
                            viewModel.getUserAvatar(),
                            DesensitizationUtils.commonDisplay(viewModel.getNickName())
                    )
            );
        }
        return new PageImpl<>(content, pageable, viewModels.getTotalPages());
    }
}
