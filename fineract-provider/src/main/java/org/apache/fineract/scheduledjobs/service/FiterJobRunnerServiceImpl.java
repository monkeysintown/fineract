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
package org.apache.fineract.scheduledjobs.service;

import org.apache.fineract.infrastructure.jobs.annotation.CronTarget;
import org.apache.fineract.infrastructure.jobs.service.JobName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FiterJobRunnerServiceImpl implements FiterJobRunnerService {
    private static final Logger logger = LoggerFactory.getLogger(FiterJobRunnerServiceImpl.class);

    @Transactional
    @Override
    @CronTarget(jobName = JobName.FITER_SAMPLE1)
    public void sample1() {
        logger.warn("Execute Sample Job 1");
    }

    @Transactional
    @Override
    @CronTarget(jobName = JobName.FITER_SAMPLE2)
    public void sample2() {
        logger.warn("Execute Sample Job 2");
    }
}
