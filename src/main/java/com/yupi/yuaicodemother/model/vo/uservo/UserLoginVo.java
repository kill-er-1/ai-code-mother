package com.yupi.yuaicodemother.model.vo.uservo;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.core.keygen.KeyGenerators;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserLoginVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    //选择了 KeyType.Generator 就意味着主键的生成工作从数据库转移到了应用程序本身。
    // 这在分布式系统、分库分表等场景下非常有用，因为数据库自增ID无法保证全局唯一。
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long id;


    /**
     * 账号
     */
    @Column("userAccount")
    private String userAccount;

    /**
     * 用户昵称
     */
    @Column("userName")
    private String userName;

    /**
     * 用户头像
     */
    @Column("userAvatar")
    private String userAvatar;

    /**
     * 用户简介
     */
    @Column("userProfile")
    private String userProfile;

    /**
     * 用户角色：user/admin
     */
    @Column("userRole")
    private String userRole;

    /**
     * 创建时间
     */
    @Column("createTime")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Column("updateTime")
    private LocalDateTime updateTime;



}
