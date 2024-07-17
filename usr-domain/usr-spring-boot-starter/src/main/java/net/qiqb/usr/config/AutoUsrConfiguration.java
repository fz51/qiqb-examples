package net.qiqb.usr.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@Import({AutoUsrCustomConfiguration.class})
public class AutoUsrConfiguration {

}
