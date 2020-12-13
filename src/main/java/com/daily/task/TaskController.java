package com.daily.task;

import com.daily.msg.ResultBody;
import com.daily.msg.ResultCodeMsg;
import com.daily.service.SpringScheduledTaskService;
import com.daily.tool.NullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zwobble.mammoth.Result;

/**
 * @ClassName TaskController
 * @Description TODO
 * @Author metropolis-long
 * @Date 2020/11/3 0:48
 * @VERSION 1.0.0
 */
@Controller
@RequestMapping("/scheduled/task")
public class TaskController {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private SpringScheduledTaskService cronRepository;
    /**
     * 查看任务列表
     */
    @RequestMapping("/taskList")
    public String taskList(Model model) {
        model.addAttribute("cronList", cronRepository.findAll());
        return "task-list";
    }
    /**
     * 编辑任务cron表达式
     */
    @ResponseBody
    @RequestMapping("/editTaskCron")
    public ResultBody editTaskCron(String cronKey, String newCron) {
        if (!NullUtil.isValidExpression(newCron)) {
            throw new IllegalArgumentException("失败,非法表达式:" + newCron);
        }
        cronRepository.updateCronExpressionByCronKey(newCron, cronKey);
        return new ResultBody<>(ResultCodeMsg.OK.getCode(), ResultCodeMsg.OK.getMsg());
    }
    /**
     * 执行定时任务
     */
    @ResponseBody
    @RequestMapping("/runTaskCron")
    public ResultBody runTaskCron(String cronKey) throws Exception {
        ((ScheduledTask) context.getBean(Class.forName(cronKey))).execute();
        return new ResultBody(ResultCodeMsg.OK.getCode(), ResultCodeMsg.OK.getMsg());
    }
    /**
     * 启用或禁用定时任务
     */
    @ResponseBody
    @RequestMapping("/changeStatusTaskCron")
    public ResultBody<Void> changeStatusTaskCron(Integer status, String cronKey) {
        cronRepository.updateStatusByCronKey(status, cronKey);
        return new ResultBody<>(ResultCodeMsg.OK.getCode(), ResultCodeMsg.OK.getMsg());
    }
}