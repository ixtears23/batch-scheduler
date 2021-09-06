package junseok.snr.batchscheduler;

import junseok.snr.batchscheduler.schduler.JobSchedule;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BatchSchedulerApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(BatchSchedulerApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        JobSchedule.execute();
    }
}
