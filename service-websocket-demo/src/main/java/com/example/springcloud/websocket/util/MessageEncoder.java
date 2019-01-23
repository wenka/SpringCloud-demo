package com.example.springcloud.websocket.util;

import com.alibaba.fastjson.JSON;
import com.example.springcloud.websocket.model.MsgBody;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/01/23  下午 03:00
 * Description:
 */
public class MessageEncoder  implements Encoder.Text<MsgBody>{
    @Override
    public String encode(MsgBody msgBody) throws EncodeException {
        return JSON.toJSONString(msgBody);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
