package junseok.snr.batchscheduler.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/batch-client")
public class BatchClientController {

    private static final Logger log = LoggerFactory.getLogger(BatchClientController.class);

    private final JobLauncher jobLauncher;
    private final Job job;

    public BatchClientController(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @GetMapping("/start-sample")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> startSample() throws Exception {
        jobLauncher.run(job, new JobParameters());
        return new ResponseEntity<>("BATCH 성공하신듯?", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public void handleException(Exception exception) {
        log.error(exception.getMessage(), exception);
    }
}
