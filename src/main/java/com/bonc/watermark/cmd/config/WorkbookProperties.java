package com.bonc.watermark.cmd.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component("workbookProperties")
@Data
@ConfigurationProperties(prefix = "protect")
public class WorkbookProperties {
    private String password;
}
