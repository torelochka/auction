package ru.itis.akchurina.auction.services;

import org.quartz.Job;

import java.util.Date;

public interface JobService {
    void createJob(Class<? extends Job> jobClass, Date scheduleDate, String identify);
}
