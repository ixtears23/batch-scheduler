package junseok.snr.batchscheduler.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class BatchClientControllerRestTest {
    
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    void startSampleTest() {
        final String LOCAL_BASE_URL = "http://localhost";
        final String TEST_URL = "/batch-client/start-sample";
        final String RESULT = this.restTemplate.getForObject(String.format("%s:%s%s", LOCAL_BASE_URL, port, TEST_URL), String.class);

        assertThat(RESULT).isEqualTo("BATCH 성공하신듯?");
    }
}
