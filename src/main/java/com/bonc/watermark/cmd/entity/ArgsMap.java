package com.bonc.watermark.cmd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArgsMap {

    List<WatermarkTask> watermarkTasks;

}
