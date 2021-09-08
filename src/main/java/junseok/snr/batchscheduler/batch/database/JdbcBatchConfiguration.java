package junseok.snr.batchscheduler.batch.database;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.validator.SpringValidator;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class JdbcBatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

    public JdbcBatchConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, DataSource dataSource) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.dataSource = dataSource;
    }

    @Bean
    public JdbcCursorItemReader<CustomerCredit> creditReader() {

        return new JdbcCursorItemReaderBuilder<CustomerCredit>()
                .dataSource(dataSource)
                .name("creditReader")
                .sql("select ID, NAME, CREDIT from CUSTOMER")
                .rowMapper(new CustomerCreditRowMapper())
                .queryTimeout(1)
                .driverSupportsAbsolute(true)
                .build();
    }

    @Bean
    public CustomerCreditItemProcessor creditProcessor() {
        return new CustomerCreditItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<CustomerCredit> creditWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<CustomerCredit>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO CUSTOMER (NAME, CREDIT) VALUES (:name, :credit)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job importCreditJob(JdbcJobCompletionNotificationListener listener, Step creditStep1, Step creditStep2) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(creditStep1)
                .next(creditStep2)
                .end()
                .build();
    }

    @Bean
    public Step creditStep1(JdbcBatchItemWriter<CustomerCredit> creditWriter) {
        return stepBuilderFactory.get("step1")
                .<CustomerCredit, CustomerCredit>chunk(10)
                .reader(creditReader())
                .processor(creditProcessor())
                .writer(creditWriter)
                .build();
    }

    @Bean
    public Step creditStep2(JdbcBatchItemWriter<CustomerCredit> creditWriter) {
        return stepBuilderFactory.get("step2")
                .<CustomerCredit, CustomerCredit>chunk(50)
                .reader(creditReader())
                .processor(creditProcessor())
                .writer(creditWriter)
                .build();
    }

    @Bean
    public ValidatingItemProcessor<CustomerCredit> itemProcessor() {
        ValidatingItemProcessor<CustomerCredit> processor = new ValidatingItemProcessor<>();
        processor.setValidator(validator());
        return processor;
    }

    @Bean
    public SpringValidator<CustomerCredit> validator() {
        SpringValidator<CustomerCredit> validator = new SpringValidator<>();
        validator.setValidator(new CustomCreditValidator());
        return  validator;
    }

}
