package junseok.snr.batchscheduler.batch.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.math.BigDecimal;

public class CustomerCreditItemProcessor implements ItemProcessor<CustomerCredit, CustomerCredit> {

    private static final Logger log = LoggerFactory.getLogger(CustomerCreditItemProcessor.class);

    @Override
    public CustomerCredit process(CustomerCredit customerCredit) throws Exception {
        final int id = customerCredit.getId();
        final String name = customerCredit.getName();
        final BigDecimal credit = customerCredit.getCredit();

        final CustomerCredit transformedCustomCredit = new CustomerCredit(id, name, credit);

        log.info("Converting ({}) into({})", customerCredit, transformedCustomCredit);

        return transformedCustomCredit;
    }
}
