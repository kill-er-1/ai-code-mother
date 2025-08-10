package com.yupi.yuaicodemother.service;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import com.yupi.yuaicodemother.model.dto.user.UserQueryRequest;
import com.yupi.yuaicodemother.model.entity.User;
import com.yupi.yuaicodemother.model.vo.uservo.UserLoginVo;
import com.yupi.yuaicodemother.model.vo.uservo.UserVO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

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

    /**
     * 获取登录用户的视图对象
     * @param user
     * @return
     */
    UserLoginVo getLoginUserVo(User user);

    /**
     * 用户登录
     * @param userAccount
     * @param userPassword
     * @param request
     * @return
     */
    UserLoginVo userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户登录状体
     * @param request
     * @return
     */
    UserLoginVo getUserLogin(HttpServletRequest request);

    /**
     * 用户注销
     * @param request
     * @return
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * 获取用户视图对象
     * @param user
     * @return
     */
    public UserVO getUserVO(User user);

    /**
     * 获取用户视图对象列表
     * @param userList
     * @return
     */
    public List<UserVO> getUserVOList(List<User> userList);

    /**
     * 获取查询条件包装器
     * @param userQueryRequest
     * @return
     */
    QueryWrapper getQueryWrapper(UserQueryRequest userQueryRequest);
}
