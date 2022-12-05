package cn.violin.bookmark.utils;

public enum NumberEnum {

    T_BOOKMARK("t_bookmark"),
    T_BOOKMARK_TYPE("t_bookmark_type"),
    ;

    private String tableName;

    NumberEnum(String tableName) {
        this.tableName = tableName;
    }

    public String value() {
        return tableName;
    }
}
