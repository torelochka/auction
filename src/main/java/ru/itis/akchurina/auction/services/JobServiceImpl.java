package ru.itis.akchurina.auction.services;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

@Service
@Slf4j
public class JobServiceImpl implements JobService {

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private MailService mailService;

    @Override
    public void createJob(Class<? extends Job> jobClass, Date scheduleDate, Long identify) {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();

            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("auctionId", identify);
            jobDataMap.put("auctionService", auctionService);
            jobDataMap.put("mailService", mailService);

            JobDetail job = newJob(jobClass)
                    .withIdentity(identify.toString(), "auctionGroup")
                    .usingJobData(jobDataMap)
                    .build();

            log.info("start new job with identify " + identify + " until " + scheduleDate.toString());

            Trigger trigger = newTrigger()
                    .withIdentity(identify.toString(), "AuctionTriggerGroup")
                    .startAt(scheduleDate)
                    .build();

            scheduler.scheduleJob(job, trigger);
            scheduler.start();

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
