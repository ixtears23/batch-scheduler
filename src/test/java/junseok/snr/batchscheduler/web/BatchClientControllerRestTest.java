package junseok.snr.batchscheduler.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class BatchClientControllerRestTest {

    private static final String LOCAL_BASE_URL = "http://localhost";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    @DisplayName("FlatFileItemReader, JdbcItemWriter")
    void startUserJobTest() {
        final String TEST_URL = "/batch-client/start-user-job";

        final String RESULT = this.restTemplate.getForObject(getFullTestUrl(TEST_URL), String.class);

        assertThat(RESULT).isEqualTo("Start UserJob Success");
    }

    @Test
    @DisplayName("JdbcCursorItemReader, JdbcItemWriter")
    void startCreditJobTest() {
        final String TEST_URL = "/batch-client/start-credit-job";

        final String RESULT = this.restTemplate.getForObject(getFullTestUrl(TEST_URL), String.class);

        assertThat(RESULT).isEqualTo("Start CreditJob Success");
    }

    private String getFullTestUrl(String TEST_URL) {
        return String.format("%s:%s%s", LOCAL_BASE_URL, port, TEST_URL);
    }

}
