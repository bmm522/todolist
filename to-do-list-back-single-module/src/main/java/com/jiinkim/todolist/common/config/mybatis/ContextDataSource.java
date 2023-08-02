package com.jiinkim.todolist.common.config.mybatis;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@MapperScan(basePackages = {"com.jiinkim.todolist.user.dao", "com.jiinkim.todolist.todo.dao"})
@RequiredArgsConstructor
public class ContextDataSource {


        private final ApplicationContext applicationContext;

        @Bean
        public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException {

                SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();

                factoryBean.setDataSource(dataSource);

                factoryBean.setTypeAliasesPackage("com.jiinkim.todolist.**.dao.**");
                factoryBean.setMapperLocations(
                    applicationContext.getResources("classpath:/mybatis/mappers/**/*.xml"));
                return factoryBean;
        }

        @Bean
        public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {

                return new SqlSessionTemplate(sqlSessionFactory);
        }

}
