package com.bonc.watermark.cmd;

import com.bonc.watermark.cmd.handle.HandlerAdapter;
import com.bonc.watermark.cmd.handle.TaskDispatcher;
import com.bonc.watermark.cmd.util.StringsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;


@Slf4j
@SpringBootApplication
public class CmdWatermarkApplication implements ApplicationRunner {
    private static final String libName = "opencv_java452.dll";
    public static final String libPath = "lib/opencv_java452.dll";

    @Resource
    private TaskDispatcher taskDispatcher;

    public static void main(String[] args) {

        if (!StringsUtil.containSkipLoadLib(args)) {
            try {
                // 1、IDEA load方法
                URL url = ClassLoader.getSystemResource(libPath);
                System.load(url.getPath());
            } catch (Exception e) {
                // 2、打包部署时使用下面方法
                loadLib();
            }
        }

        SpringApplication.run(CmdWatermarkApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (args.containsOption("help")) {
            System.out.println(usage());
            System.exit(0);
        }
        /*HandlerAdapter factory = HandlerAdapter.factory(args);
        factory.adapter();*/
        List<HandlerAdapter> handlerAdapters = taskDispatcher.expose(args);
        taskDispatcher.dispatch(handlerAdapters);
    }

    private String usage() {
        return "--watermark={watermark}" + "\t" + "specify the watermark words of add to file." + "\n" +
                "--inputFileFullPath={inputFileFullPath}" + "\t" + "specify the full path of input file for want to add watermark." + "\n" +
                "--outputFileFullPath={outputFileFullPath}" + "\t" + "specify the full path of output file." + "\n" +
                "--darkType={dark/light/solid}" + "\n";
    }

    public static void loadLib() {
        try {
            // 读取Resource下的动态库
            ClassPathResource classPathResource = new ClassPathResource(libPath);
            InputStream in = classPathResource.getInputStream();
            // 将动态库提取到临时文件目录 C:/Users/user/AppData/Local/Temp/opencv_java452.dll
            String nativeTempDir = System.getProperty("java.io.tmpdir");
            File libFile = new File(nativeTempDir+ File.separator + libName);
            // 如果临时文件目录不存在动态库，则进行拷贝
            if (!libFile.exists()){
                FileOutputStream fos = new FileOutputStream(libFile);
                byte[] buffer = new byte[in.available()];
                int readLength = in.read(buffer);
                fos.write(buffer);
                fos.flush();
                fos.close();
            }
            in.close();
            System.load(libFile.getAbsolutePath());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
