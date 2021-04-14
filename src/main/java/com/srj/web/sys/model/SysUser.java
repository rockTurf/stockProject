package com.srj.web.sys.model;

import com.srj.common.constant.Constant;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class SysUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String name;//姓名

    private String email;//邮箱

    private String phone;//手机号

    private String delFlag;

    @Transient
    private String roleId;//角色id



    public boolean isAdmin() {
        return Constant.SUPER_ADMIN==(this.getId())?true:false;
    }


}