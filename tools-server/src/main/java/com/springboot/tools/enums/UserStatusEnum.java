package com.springboot.tools.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户状态码
 * @author swen
 */
@Getter
public enum UserStatusEnum {

    CANUSE(0,"有效"),
    BLOCK(1,"锁定");

    private final Integer value;
    private final String name;

    UserStatusEnum(Integer value, String name){
        this.value = value;
        this.name = name;
    }

    private static List<Map<String, String>> list;

    private static Map<Integer, String> map;

    public static List<Map<String, String>> toList() {
        if (list == null) {
            UserStatusEnum[] ary = UserStatusEnum.values();
            List<Map<String, String>> listTmp = new ArrayList<>();
            for (UserStatusEnum userStatusEnum : ary) {
                Map<String, String> map = new HashMap<>();
                map.put("value", String.valueOf(userStatusEnum.getValue()));
                map.put("name", userStatusEnum.getName());
                listTmp.add(map);
            }
            list = listTmp;
        }
        return list;
    }

    public static Map<Integer, String> toMap() {
        if (map == null) {
            UserStatusEnum[] ary = UserStatusEnum.values();
            Map<Integer, String> enumMap = new HashMap<>();
            for (UserStatusEnum userStatusEnum : ary) {
                enumMap.put(userStatusEnum.getValue(), userStatusEnum.getName());
            }
            map = enumMap;
        }
        return map;
    }

    public static String getNameByValue(Integer value) {
        return toMap().get(value);
    }
}
