package test.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan(value = {"test"})
@PropertySource("classpath:application.yml")
@EnableAspectJAutoProxy
@EnableScheduling
public class AnnotationConfiguration {
}
