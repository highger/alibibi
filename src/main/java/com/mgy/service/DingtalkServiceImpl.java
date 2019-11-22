package com.mgy.service;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.chatbot.message.Message;
import com.dingtalk.chatbot.message.TextMessage;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mgy on 2019/11/22
 */
@Service
@Slf4j
public class DingtalkServiceImpl implements DingtalkService {

    @Override
    public void sendToChatbot(String msg) {
        TextMessage message = new TextMessage(msg);
        try {
            send("https://oapi.dingtalk.com/robot/send?access_token=75b7ee24a8991799ec270440bc2fad62c945ef9cf72c5f4ae4e24d48bb549b54",message);
        } catch (IOException e) {
            System.out.println("异常啦！"+e.getMessage());
        }
    }

    public SendResult send(String webhook, Message message) throws IOException {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(15000)
                .setMaxRedirects(10)
                .setSocketTimeout(15000)
                .build();

        List<Header> defaultHeaders = new ArrayList<>();
        defaultHeaders.add(new BasicHeader("content-type", "application/json"));
        CloseableHttpClient closeableHttpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .setMaxConnPerRoute(30)
                .setMaxConnTotal(30)
                .setDefaultHeaders(defaultHeaders)
                .build();
        HttpPost httppost = new HttpPost(webhook);
        StringEntity se = new StringEntity(message.toJsonString(), Charsets.UTF_8);
        httppost.setEntity(se);
        try (CloseableHttpResponse response = closeableHttpClient.execute(httppost)) {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.OK.value()) {
                String result = EntityUtils.toString(response.getEntity());
                JSONObject obj = JSONObject.parseObject(result);

                SendResult sendResult = new SendResult();
                Integer errcode = obj.getInteger("errcode");
                sendResult.setErrorCode(errcode);
                sendResult.setErrorMsg(obj.getString("errmsg"));
                sendResult.setSuccess(Integer.valueOf(0).equals(errcode));
                return sendResult;
            } else {
                throw new IOException(String.format("异常的HTTP状态!statusCode=%d, response=%s", statusCode, response));
            }
        } catch (IllegalStateException e) {
            // 如果IllegalStateException是因为Connection pool shut down,请在单元测试时sleep一下.因为可能是异步调用,而spring已经关闭了
            throw new IllegalStateException(e);
        } catch (IOException e) {
            throw new IOException(String.format("推送异常, message=%s", message), e);
        }
    }
}
