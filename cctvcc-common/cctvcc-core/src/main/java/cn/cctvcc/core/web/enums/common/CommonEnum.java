package cn.cctvcc.core.web.enums.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 系统公用枚举
 * @author: Jiang
 * @create: 2021-09-22 17:49
 */
public enum CommonEnum {
    /** 生效类状态 */
    OK("0", "正常"),
    DISABLE("1", "停用"),
    DELETED("2", "删除"),

    /** 是否状态 */
    YES("0", "是"),
    NO("1", "否"),

    ;

    private final String code;
    private final String info;

    CommonEnum(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public static String getInfoByCode(String code) {
        CommonEnum status = codeMap.get(code);
        if (status != null) {
            return status.getInfo();
        }
        return "未知状态";
    }

    public static String getCodeByInfo(String info) {
        CommonEnum status = infoMap.get(info);
        if (status != null) {
            return status.getCode();
        }
        return "-";
    }

    private static Map<String, CommonEnum> codeMap;

    private static Map<String, CommonEnum> infoMap;
    static {
        codeMap = new HashMap<String, CommonEnum>();
        infoMap = new HashMap<String, CommonEnum>();
        for (CommonEnum item : CommonEnum.values()) {
            codeMap.put(item.getCode(), item);
            infoMap.put(item.getInfo(), item);
        }
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
