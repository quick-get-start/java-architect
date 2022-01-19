package com.start.quick.controller;

import com.start.quick.common.JSONResult;
import com.start.quick.entity.Carousel;
import com.start.quick.enums.YesOrNo;
import com.start.quick.service.CarouselService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("index")
public class IndexController {

    private final CarouselService carouselService;

    public IndexController(CarouselService carouselService) {
        this.carouselService = carouselService;
    }

    @GetMapping("carousel")
    public JSONResult<List<Carousel>> carousel() {
        List<Carousel> carousels = this.carouselService.findAll(YesOrNo.YES);
        return JSONResult.ok("查询轮播图成功", carousels);
    }
}
