package org.apache.fineract.infrastructure.flexjobs.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.fineract.infrastructure.flexjobs.jobs.FlexJob;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class FlexJobBean extends QuartzJobBean {
    private static final Logger logger = LoggerFactory.getLogger(FlexJobBean.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info("Executing Flex Job with key {}", context.getJobDetail().getKey());

        JobDataMap jobDataMap = context.getMergedJobDataMap();

        String name = jobDataMap.getString("name");

        if(StringUtils.isEmpty(name)) {
            throw new RuntimeException("Missing bean name for Flex Job!");
        }

        FlexJob job = applicationContext.getBean(name, FlexJob.class);

        if(job==null) {
            throw new RuntimeException(String.format("No Flex Job bean with name %s found!", name));
        }

        job.execute();
    }
}
