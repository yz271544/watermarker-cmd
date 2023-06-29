package com.bonc.watermark.cmd.config;

import com.bonc.watermark.cmd.handle.HandlerAdapter;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Component
@Data
@Slf4j
public class FixTaskPool {

    private ExecutorService executorService;

    List<String> taskReturns;

    ConcurrentHashMap<String, Future<String>> tasks;

    public FixTaskPool(FixTaskPoolSize fixTaskPoolSize) {
        this.taskReturns = new ArrayList<>();
        this.tasks = new ConcurrentHashMap<>();
        this.executorService = Executors.newFixedThreadPool(fixTaskPoolSize.getSize());
    }

    public void submit(HandlerAdapter handlerTask) {
        Future<String> taskFuture = executorService.submit(handlerTask);
        this.tasks.put(handlerTask.getInputFileFullPath(), taskFuture);
    }

    public void detectTask() {
        for (;;) {
            if (tasks.size() == 0) {
                break;
            }
            tasks.forEach((taskName, taskFuture) -> {
                if (taskFuture.isDone()) {
                    try {
                        taskReturns.add((String) taskFuture.get());
                    } catch (InterruptedException e) {
                        log.error("task: {}, interrupt: {}", taskName, e.getMessage());
                    } catch (ExecutionException e) {
                        log.error("task: {}, execute: {}", taskName, e.getMessage());
                    }
                    tasks.remove(taskName);
                }
            });
        }
        for (String taskRetStatus : taskReturns) {
            log.info("task status:" + taskRetStatus);
        }
    }

    public void shutdown() {
        executorService.shutdown();
    }

}
