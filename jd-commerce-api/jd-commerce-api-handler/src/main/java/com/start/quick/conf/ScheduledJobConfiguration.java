package com.start.quick.conf;

import com.start.quick.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledJobConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledJobConfiguration.class);

    private final OrderService orderService;

    public ScheduledJobConfiguration(OrderService orderService) {
        this.orderService = orderService;
    }

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void autoCloseJob() {
        logger.info("====== Auto Close Order Job Started ======");
        this.orderService.closeOrders();
        logger.info("====== Auto Close Order Job Finished ======");
    }
}
