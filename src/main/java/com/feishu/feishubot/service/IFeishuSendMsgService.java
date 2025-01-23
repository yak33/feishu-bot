package com.feishu.feishubot.service;

import com.feishu.feishubot.domain.vo.Result;

/**
 * 飞书机器人发送消息 服务接口
 *
 * @author: ZHANGCHAO
 * @version: 1.0
 * @date: 2025/1/23 15:31
 */
public interface IFeishuSendMsgService {
    /**
     * 发送消息
     *
     * @param msg
     * @return java.lang.String
     * @author ZHANGCHAO
     * @date 2025/1/23 13:55
     */
    Result<?> sendMsg(String msg);
}
