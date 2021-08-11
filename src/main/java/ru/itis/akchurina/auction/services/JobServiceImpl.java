package ru.itis.akchurina.auction.services;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;
import ru.itis.akchurina.auction.jobs.SimpleJob;

import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@Service
@Slf4j
public class JobServiceImpl implements JobService {

    @Override
    public void createJob(Class<? extends Job> jobClass, Date scheduleDate, String identify) {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();

            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("email", identify);

            JobDetail job = newJob(jobClass)
                    .withIdentity(identify, "auctionGroup")
                    .usingJobData(jobDataMap)
                    .build();

            log.info("start new job with identify " + identify + " until " + scheduleDate.toString());

            Trigger trigger = newTrigger()
                    .withIdentity(identify, "AuctionTriggerGroup")
                    .startAt(scheduleDate)
                    .build();

            scheduler.scheduleJob(job, trigger);
            scheduler.start();

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
