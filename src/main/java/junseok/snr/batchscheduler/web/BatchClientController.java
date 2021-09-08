package junseok.snr.batchscheduler.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/batch-client")
public class BatchClientController {

    private static final Logger log = LoggerFactory.getLogger(BatchClientController.class);

    private final JobLauncher jobLauncher;
    private final Job importCreditJob;
    private final Job importUserJob;

    public BatchClientController(JobLauncher jobLauncher, @Qualifier("importCreditJob") Job importCreditJob, @Qualifier("importUserJob") Job importUserJob) {
        this.jobLauncher = jobLauncher;
        this.importCreditJob = importCreditJob;
        this.importUserJob = importUserJob;
    }

    @GetMapping("/start-credit-job")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> startCreditJob() throws Exception {
        jobLauncher.run(importCreditJob, new JobParameters());
        return new ResponseEntity<>("Start CreditJob Success", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/start-user-job")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> startUserJob() throws Exception {
        jobLauncher.run(importUserJob, new JobParameters());
        return new ResponseEntity<>("Start UserJob Success", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public void handleException(Exception exception) {
        log.error(exception.getMessage(), exception);
    }
}
