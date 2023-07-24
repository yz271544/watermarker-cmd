package com.bonc.watermark.xlsx.resolver;

import com.bonc.watermark.cmd.entity.WatermarkTask;
import com.bonc.watermark.cmd.exception.CmdException;
import com.bonc.watermark.cmd.handle.HandlerAdapter;
import com.bonc.watermark.cmd.entity.ArgsMap;
import com.bonc.watermark.cmd.util.FileUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetArgumentsTest {

    @Test
    public void testGetArguments() {

        Field[] fields = HandlerAdapter.class.getDeclaredFields();

        for (Field field : fields) {
            System.out.println(field.getName());
        }

    }

    @Test
    public void testParserToListMap() throws JsonProcessingException {
        List<Map<String, String>> otherArgs = new ArrayList<>();

        Map<String, String> arg1 = new HashMap<>();
        arg1.put("fontFamily", "华文行楷");
        arg1.put("fontStyle", "bold");
        arg1.put("fontSize", "80");
        arg1.put("fontColor", "black");
        arg1.put("startX", "700");
        arg1.put("startY", "1030");
        Map<String, String> arg2 = new HashMap<>();
        arg2.put("fontFamily", "宋体");
        arg2.put("fontStyle", "bold");
        arg2.put("fontSize", "60");
        arg2.put("fontColor", "grey");
        arg2.put("startX", "2400");
        arg2.put("startY", "300");

        otherArgs.add(arg1);
        otherArgs.add(arg2);

        ObjectMapper mapper = new ObjectMapper();

        String otherArgsJson = mapper.writeValueAsString(otherArgs);
        System.out.println(otherArgsJson);

        List<Map<String, String>> listObj = mapper.readValue(otherArgsJson, new TypeReference<List<Map<String, String>>>() {});

        System.out.println(listObj);
    }


    @Test
    public void testParseConfigFile() throws CmdException {
        ArgsMap argsMap = FileUtil.parseFromWatermarkJson("D:\\iProject\\javapath\\watermarker-cmd\\conf\\jpg.json");
        System.out.println(argsMap);
        for (WatermarkTask watermarkTask : argsMap.getWatermarkTasks()) {
            for (Map<String, String> task : watermarkTask.getOtherArgs()) {
                System.out.println(task);
            }
        }
    }

}
