package com.antnest.kafka.quartz.scheduler;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class SchedulerConfig {
    private JobDetail jobDetail;
    private Trigger trigger;

    public SchedulerConfig(@Qualifier("jobDetail") JobDetail jobDetail,@Qualifier("jobTriggerBoot") Trigger trigger) {
        this.jobDetail = jobDetail;
        this.trigger = trigger;
    }
    @Bean
    @DependsOn({"jobDetail","jobTriggerBoot"})
    public void initJob(){
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        try{
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.scheduleJob(jobDetail,trigger);
            scheduler.start();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
