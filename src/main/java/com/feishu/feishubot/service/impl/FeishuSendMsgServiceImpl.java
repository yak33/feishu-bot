package com.feishu.feishubot.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.feishu.feishubot.domain.vo.Result;
import com.feishu.feishubot.service.IFeishuSendMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.feishu.feishubot.util.SignUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static cn.hutool.core.text.CharSequenceUtil.isBlank;

/**
 * 飞书机器人发送消息 服务接口实现类
 *
 * @author: ZHANGCHAO
 * @version: 1.0
 * @date: 2025/1/23 15:31
 */
@Slf4j
@Service
public class FeishuSendMsgServiceImpl implements IFeishuSendMsgService {

    /**
     * 发送消息
     *
     * @param msg
     * @return java.lang.String
     * @author ZHANGCHAO
     * @date 2025/1/23 13:55
     */
    @Override
    public Result<?> sendMsg(String msg) {
        // 解析 msg 字符串
        JSONObject msgJson = JSONObject.parseObject(msg);
        String actualMsg = msgJson.getString("text"); // 提取 "text" 字段的值
        if (isBlank(actualMsg)) {
            return Result.fail("消息为空");
        }
        String secret = msgJson.getString("secret"); // 提取 "text" 字段的值
        if (isBlank(secret)) {
            return Result.fail("secret为空");
        }
        // 获取当前时间戳（秒级）
        long timestamp = System.currentTimeMillis() / 1000;

        try {
            // 生成签名
            String sign = SignUtil.GenSign(timestamp, secret);

            // 构建请求体
            JSONObject requestBody = new JSONObject();
            requestBody.put("timestamp", timestamp);
            requestBody.put("sign", sign);
            requestBody.put("msg_type", "text");
            requestBody.put("content", msg);

            // 发送 POST 请求
            HttpResponse response = HttpRequest.post("https://open.feishu.cn/open-apis/bot/v2/hook/2ae53860-7819-43ea-8d13-363630e78888")
                    .body(requestBody.toString())
                    .execute();

            // 处理响应
            if (response.isOk()) {
                String responseBody = response.body();
                log.info("飞书机器人响应: {}", responseBody);
                return Result.ok("消息发送成功");
            } else {
                log.error("飞书机器人请求失败，状态码: {}", response.getStatus());
                return Result.fail("消息发送失败");
            }
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            log.error("生成签名失败", e);
            return Result.fail("生成签名失败");
        }
    }
}
