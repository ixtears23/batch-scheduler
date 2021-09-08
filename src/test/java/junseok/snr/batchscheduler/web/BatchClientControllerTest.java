package junseok.snr.batchscheduler.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import junseok.snr.batchscheduler.batch.BatchTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

//@SpringBootTest
//@Import(BatchTestConfiguration.class)
//@AutoConfigureMockMvc
class BatchClientControllerTest {

    @MockBean
    private final MockMvc mockMvc;

    @Autowired
    public BatchClientControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void startSampleTest() throws Exception {
        this.mockMvc.perform(get("/batch-client/start-sample"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
