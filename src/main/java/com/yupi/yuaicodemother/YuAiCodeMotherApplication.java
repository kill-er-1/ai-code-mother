package com.yupi.yuaicodemother;

import dev.langchain4j.community.store.embedding.redis.spring.RedisEmbeddingStoreAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.yupi.yuaicodemother.mapper")
@SpringBootApplication(exclude = {RedisEmbeddingStoreAutoConfiguration.class})
public class YuAiCodeMotherApplication {

    public static void main(String[] args) {

        SpringApplication.run(YuAiCodeMotherApplication.class, args);
    }

}
