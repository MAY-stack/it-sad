package com.it.web.sad.itwebsad.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@Configuration
@EnableMongoAuditing
public class MongoConfiguration {
    @Bean
    public MappingMongoConverter setConvertingOptions(MongoDatabaseFactory factory, MongoMappingContext ctx,
                                                      BeanFactory beanFactory) {
        DbRefResolver resolver = new DefaultDbRefResolver(factory);
        MappingMongoConverter converter = new MappingMongoConverter(resolver, ctx);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));      //데이터 저장 시 _class field 사용X
        return converter;
    }
}
