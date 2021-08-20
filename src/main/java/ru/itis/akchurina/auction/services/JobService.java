package ru.itis.akchurina.auction.services;

import org.quartz.Job;

import java.util.Date;
import java.util.UUID;

public interface JobService {
    void createJob(Class<? extends Job> jobClass, Date scheduleDate, Long identify);

    void triggerJob(Long id);
}
