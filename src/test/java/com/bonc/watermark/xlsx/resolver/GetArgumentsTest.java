package com.bonc.watermark.xlsx.resolver;

import com.bonc.watermark.cmd.handle.HandlerAdapter;
import org.junit.Test;

import java.lang.reflect.Field;

public class GetArgumentsTest {

    @Test
    public void testGetArguments() {

        Field[] fields = HandlerAdapter.class.getDeclaredFields();

        for (Field field : fields) {
            System.out.println(field.getName());
        }

    }

}
