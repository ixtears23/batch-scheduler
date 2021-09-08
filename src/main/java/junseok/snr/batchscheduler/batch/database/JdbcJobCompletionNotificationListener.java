package junseok.snr.batchscheduler.batch.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcJobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JdbcJobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    public JdbcJobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            jdbcTemplate.query("SELECT ID, NAME, CREDIT FROM CUSTOMER",
                    (rs, rowNum) -> new CustomerCredit(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getBigDecimal(3)
                    )).forEach(customerCredit -> log.info("FOUND <{}> in the database.", customerCredit));
        }
    }
}
