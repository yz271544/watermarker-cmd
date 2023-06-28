package com.bonc.watermark.cmd;

import com.bonc.watermark.cmd.handle.HandlerAdapter;
import com.bonc.watermark.cmd.handle.TaskDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@SpringBootApplication
public class CmdWatermarkApplication implements ApplicationRunner {

    @Resource
    private TaskDispatcher taskDispatcher;

    public static void main(String[] args) {
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
                "--outputFileFullPath={outputFileFullPath}" + "\t" + "specify the full path of output file." + "\n";
    }

}
