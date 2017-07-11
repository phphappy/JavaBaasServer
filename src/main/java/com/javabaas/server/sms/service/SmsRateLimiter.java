package com.javabaas.server.sms.service;

import com.javabaas.server.common.entity.SimpleCode;
import com.javabaas.server.common.entity.SimpleError;
import com.javabaas.server.config.SmsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 短信请求频度限制
 * Created by Codi on 2017/6/29.
 */
@Component
public class SmsRateLimiter {

    @Autowired
    private SmsConfig smsConfig;
    @Autowired
    private StringRedisTemplate redisTemplate;

    public void rate(String appId, String phoneNumber, String signName, String templateId, Map<String, String> params) {
        sendIntervalLimit(appId, phoneNumber);
    }

    /**
     * 两次短信请求间隔限制
     */
    private void sendIntervalLimit(String appId, String phoneNumber) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        Integer sendInterval = smsConfig.getSendIntervalLimit();
        String key = "App_" + appId + "_SMS_SEND_INTERVAL_LIMIT_" + phoneNumber;
        String exist = ops.get(key);
        if (StringUtils.isEmpty(exist)) {
            //创建记录
            ops.set(key, "1", sendInterval, TimeUnit.SECONDS);
        } else {
            //时间间隔内 禁止连续发送
            SimpleError.e(SimpleCode.SMS_SEND_INTERVAL_LIMIT);
        }
    }

}