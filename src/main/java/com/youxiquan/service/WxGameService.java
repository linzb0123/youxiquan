package com.youxiquan.service;

import com.youxiquan.pojo.YxqGame;

import java.util.List;

/**
 * @author lzb
 * 2018/12/20
 */
public interface WxGameService {
    List<YxqGame> getFocusGame(long userId);
}
