package com.start.quick.service.impl;

import com.start.quick.entity.Carousel;
import com.start.quick.repository.CarouselRepository;
import com.start.quick.service.CarouselService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {

    private final CarouselRepository carouselRepository;

    public CarouselServiceImpl(CarouselRepository carouselRepository) {
        this.carouselRepository = carouselRepository;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Carousel> findAll(Integer isShow) {
        Sort sort = Sort.by(Sort.Direction.DESC, "sort");
        return this.carouselRepository.findAllByIsShow(isShow, sort);
    }
}
