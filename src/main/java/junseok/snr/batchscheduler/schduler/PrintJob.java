package junseok.snr.batchscheduler.schduler;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrintJob implements Job {

    private final Logger logger = LoggerFactory.getLogger(PrintJob.class);

    public PrintJob() {
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        JobDataMap map = context.getMergedJobDataMap();

        logger.info("GOOD!!!");


    }
}
