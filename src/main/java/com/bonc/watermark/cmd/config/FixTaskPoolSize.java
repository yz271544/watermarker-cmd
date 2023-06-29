package com.bonc.watermark.cmd.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component("fixTaskPoolSize")
@Data
@ConfigurationProperties(prefix = "pool")
public class FixTaskPoolSize {
    private int size;
}
