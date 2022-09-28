package com.nart;

import com.nart.util.FakeDataGenerator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.nart.dao")
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
//        FakeDataGenerator fakeDataGenerator = new FakeDataGenerator();
//        fakeDataGenerator.generateTestData(5);
    }

}
