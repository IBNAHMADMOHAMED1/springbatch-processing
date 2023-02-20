package com.batchprocessing.demo.config;

import com.batchprocessing.demo.entity.Customer;
import com.batchprocessing.demo.repository.CustomerRepo;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.awt.geom.FlatteningPathIterator;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfig {
    private  JobBuilder jobBuilder;
    private  StepBuilder stepBuilder;
    private  CustomerRepo  customerRepo;

    @Bean
    public FlatFileItemReader<Customer> reader() { // fl
        FlatFileItemReader<Customer> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("src/main/resources/customer.csv"));
        itemReader.setName("csvReader");
        itemReader.setLinesToSkip(1);
        // use lambda expression to map data to Customer object
        itemReader.setLineMapper(lineMapper());
    }

    private LineMapper<Customer> lineMapper() {
        DefaultLineMapper<Customer> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false); // if true, then it will throw exception if there is a missing field
        lineTokenizer.setNames("id","firstName","lastName", "email", "gender", "contact", "country", "dob");

        BeanWrapperFieldSetMapper<Customer> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Customer.class);


    }

}
