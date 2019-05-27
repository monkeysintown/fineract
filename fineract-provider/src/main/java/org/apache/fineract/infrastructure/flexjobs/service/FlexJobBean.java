/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
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
