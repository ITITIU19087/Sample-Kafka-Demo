package com.antnest.kafka.quartz.detail;

import com.antnest.kafka.quartz.job.QuartzJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobDetailConfig {

    @Bean(name = "jobDetail")
    public JobDetail jobDetail(){
        return JobBuilder.newJob().ofType(QuartzJob.class)
                .withIdentity("RUN_QUARTZ","JOB_GROUP")
                .withDescription("description")
                .storeDurably(true)
                .build();
    }
}
