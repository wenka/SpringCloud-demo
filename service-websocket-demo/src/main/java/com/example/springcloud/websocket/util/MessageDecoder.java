package com.example.springcloud.websocket.util;

import com.alibaba.fastjson.JSON;
import com.example.springcloud.websocket.model.MsgBody;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/01/23  下午 03:07
 * Description:
 */
public class MessageDecoder implements Decoder.Text<MsgBody> {
    @Override
    public MsgBody decode(String s) throws DecodeException {
        return JSON.parseObject(s, MsgBody.class);
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
