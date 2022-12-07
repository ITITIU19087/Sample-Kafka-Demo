

package com.antnest.kafka.quartz.trigger;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobTrigger {

    @Bean(name = "jobTriggerBoot")
    public Trigger jobTrigger(@Qualifier("jobDetail") JobDetail jobDetail){
        System.out.println("Initial");
        try{
            String time = "0/10 * * * * ?";
            return TriggerBuilder.newTrigger().forJob(jobDetail).withIdentity("RUN_QUARTZ","JOB_GROUP")
                    .startNow().withSchedule(CronScheduleBuilder.cronSchedule(time))
                    .build();
        }
        catch (Exception e){
            System.out.println("Error" + e);
            return null;
        }
    }
}
