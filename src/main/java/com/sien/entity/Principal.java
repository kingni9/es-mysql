package com.sien.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

/**
 * Created by zhuangjt on 2018/3/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName="main",type="pt_principal",refreshInterval="-1")
public class Principal {
    /**
     * 负责人ID
     */
    @Id
    private Long principalId;

    /**
     * 负责人名称
     */
    private String principal;

    /**
     * 负责人类型
     */
    private String principalPhone;

    /**
     * 负责人类型，1：采购  2：服务商
     */
    private Integer principalType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 登陆账号
     * @return
     */
    private String loginName;

    /**
     * 密码
     * @return
     */
    private String loginPassword;

    /**
     * 账号状态：0，不存在。1，有效。-1，被冻结
     * @return
     */
    private Integer accountStatus;

    /**
     * 用户状态：0，有效，-1：删除
     * @return
     */
    private Integer userStatus;

    /**
     * 账户关联ID
     */
    private Long associationID;

    /**
     * 更新时间
     */
    private Date updateTime;
}
