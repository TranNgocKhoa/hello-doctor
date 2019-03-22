package com.hellodoctor.common.constants;

import org.springframework.util.StringUtils;

public enum ImageType {
    USER_AVATAR_NONE(0),
    USER_AVATAR_UPLOAD(1),
    USER_AVATAR_REMOTE(2);

    private int index;

    ImageType(int index) {
        this.index = index;
    }

    public int index() {
        return index;
    }

    public static final ImageType fromString(String type) {
        if (StringUtils.isEmpty(type)) {
            return USER_AVATAR_NONE;
        }

        ImageType[] values = ImageType.values();
        for (ImageType v : values) {
            if (String.valueOf(v.index).equals(type)) {
                return v;
            }
        }

        return USER_AVATAR_NONE;
    }
}