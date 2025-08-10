package com.yupi.yuaicodemother.service;

import com.mybatisflex.core.service.IService;
import com.yupi.yuaicodemother.model.entity.User;

/**
 * 用户 服务层。
 *
 * @author <a href="http:caide.xin">cin</a>
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param userAccount 账号
     * @param userPassword 密码
     * @param checkPassword 确认密码
     * @return 用户ID
     */
    Long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 获取加密后的密码
     * @param userPassword
     * @return
     */
    String getEncryptedPassword(String userPassword);
}
