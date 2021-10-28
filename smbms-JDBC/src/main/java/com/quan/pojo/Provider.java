package com.quan.pojo;

import java.util.Date;

/**
 * @ClassName: Provider
 * @Description: 供应商类
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2021/10/28 15:50
 */
public class Provider {

    private Integer id;         // id
    private String proCode;     // 供应商编码
    private String proName;     // 供应商名称
    private String proDesc;     // 供应商详细描述
    private String proContact;  // 供应商联系人
    private String proPhone;    // 联系电话
    private String proAdress;   // 地址
    private String proFax;      // 传真
    private Integer createBy;   // 创建者
    private Date creationDate;  // 创建时间
    private Date modifyDate;    // 更新时间
    private Integer modifyBy;   // 更新者


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProDesc() {
        return proDesc;
    }

    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }

    public String getProContact() {
        return proContact;
    }

    public void setProContact(String proContact) {
        this.proContact = proContact;
    }

    public String getProPhone() {
        return proPhone;
    }

    public void setProPhone(String proPhone) {
        this.proPhone = proPhone;
    }

    public String getProAdress() {
        return proAdress;
    }

    public void setProAdress(String proAdress) {
        this.proAdress = proAdress;
    }

    public String getProFax() {
        return proFax;
    }

    public void setProFax(String proFax) {
        this.proFax = proFax;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }
}
