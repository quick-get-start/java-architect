package com.start.quick.service;

import com.start.quick.entity.Carousel;

import java.util.List;

public interface CarouselService {

    List<Carousel> findAll(Integer isShow);
}
