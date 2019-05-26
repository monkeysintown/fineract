package org.apache.fineract.infrastructure.flexjobs.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Sample2Job implements FlexJob {
    private static final Logger logger = LoggerFactory.getLogger(Sample2Job.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public void execute() {
        logger.warn("Execute Sample Job 2");
    }
}
