package net.qiqb.usr.config;

import lombok.extern.slf4j.Slf4j;
import net.qiqb.usr.customer.infrastructure.persistence.dao.mpimpl.CustomMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ComponentScan(basePackages = {"net.qiqb.usr.point"})
public class AutoUsrPointConfiguration {
}
