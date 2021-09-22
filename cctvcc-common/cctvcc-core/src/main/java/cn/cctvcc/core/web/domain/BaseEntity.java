package cn.cctvcc.core.web.domain;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 公用基类
 * @author: Jiang
 * @create: 2021-09-22 15:25
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    private LocalTime createTime;

    /** 编辑者 */
    private String updateBy;

    /** 编辑时间 */
    private LocalTime updateTime;

    /** 备注 */
    private String remark;

    /** 请求参数 */
    private transient Map<String, Object> params;

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalTime createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public LocalTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Map<String, Object> getParams() {
        if (params == null)
        {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

}
