package com.bonc.watermark.cmd.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class WatermarkTask {
    String name;
    List<Map<String, String>> otherArgs;

}
