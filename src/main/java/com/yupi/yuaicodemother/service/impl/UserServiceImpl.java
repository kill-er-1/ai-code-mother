package com.yupi.yuaicodemother.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.yupi.yuaicodemother.exception.BusinessException;
import com.yupi.yuaicodemother.exception.ErrorCode;
import com.yupi.yuaicodemother.model.entity.User;
import com.yupi.yuaicodemother.mapper.UserMapper;
import com.yupi.yuaicodemother.model.enums.UserRoleEnum;
import com.yupi.yuaicodemother.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * 用户 服务层实现。
 *
 * @author <a href="http:caide.xin">cin</a>
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>  implements UserService{

    @Override
    public Long userRegister(String userAccount, String userPassword, String checkPassword) {
        //1.校验
        if (StrUtil.hasBlank(userAccount,userPassword,checkPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数不能为空");
        }
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号长度不能小于4");
        }
        if (userPassword.length() < 8 || checkPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码长度不能小于8");
        }
        if (!checkPassword.equals(userPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次密码不一致");
        }
        //账号不能重复
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("userAccount", userAccount);
        long count = this.mapper.selectCountByQuery(queryWrapper);
        if (count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号已存在");
        }

        //2.加密
        String encryptedPassword = getEncryptedPassword(userPassword);

        //3.插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptedPassword);
        user.setUserName("cin");
        user.setUserRole(UserRoleEnum.USER.getValue());
        boolean result = this.save(user);
        if (!result) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "注册失败");
        }

        //4.返回用户ID
        return user.getId();
    }

    @Override
    public String  getEncryptedPassword(String userPassword) {
        final String SALT = "cin";
        return DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
    }
}
