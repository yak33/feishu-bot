package com.feishu.feishubot.controller;

import com.feishu.feishubot.domain.vo.Result;
import com.feishu.feishubot.service.IFeishuSendMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 飞书机器人发送消息控制器
 *
 * @author: ZHANGCHAO
 * @version: 1.0
 * @date: 2025/1/23 15:31
 */
@Slf4j
@RestController
@RequestMapping("/open-api")
public class FeishuSendMsgController {

    private final IFeishuSendMsgService feishuSendMsgService;

    public FeishuSendMsgController(IFeishuSendMsgService feishuSendMsgService) {
        this.feishuSendMsgService = feishuSendMsgService;
    }

    /**
     * 向飞书机器人发送消息
     *
     * @param msg
     * @return com.feishu.feishubot.domain.vo.Result<?>
     * @author ZHANGCHAO
     * @date 2025/1/23 14:55
     */
    @RequestMapping("/send-msg")
    public Result<?> sendMsg(@RequestBody String msg) {
        return feishuSendMsgService.sendMsg(msg);
    }
}
