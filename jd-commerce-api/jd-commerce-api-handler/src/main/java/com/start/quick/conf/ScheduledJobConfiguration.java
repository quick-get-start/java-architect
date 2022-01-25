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

    /**
     * 定时任务
     * 弊端：
     * 1. 有时间差
     *    10:39 下单 11:00 检查不足1小时 12:00 检查超过1小时
     * 2. 不支持集群
     *    单机没问题 使用集群后 会有多个定时任务 可以只使用一台计算机节点
     * 3. 会对数据库全表查询 影响数据库性能
     *
     * 定时任务 仅仅只适用于小型轻量级项目/传统项目
     *
     * 使用消息队列 MQ (RabbitMQ、RocketMQ、Kafka)
     * 使用延时任务（队列）
     * 10:12下单 未付款状态 11:12分检查 如果当前状态还是10 则直接关闭订单
     */
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void autoCloseJob() {
        logger.info("====== Auto Close Order Job Started ======");
        this.orderService.closeOrders();
        logger.info("====== Auto Close Order Job Finished ======");
    }
}
