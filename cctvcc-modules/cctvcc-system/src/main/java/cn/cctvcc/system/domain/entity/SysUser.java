package cn.cctvcc.system.domain.entity;

/**
 * @description:
 * @author: Jiang
 * @create: 2021-09-22 12:16
 */
public class SysUser {

    private Long id;

    private String userName;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
