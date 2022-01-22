package com.start.quick.repository;

import com.start.quick.entity.Carousel;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarouselRepository extends CrudRepository<Carousel, String> {

    List<Carousel> findAllByIsShow(Integer isShow, Sort sort);
}
