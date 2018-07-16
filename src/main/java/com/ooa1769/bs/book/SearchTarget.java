package com.ooa1769.bs.book;

public enum SearchTarget {

    ALL("전체","all"),
    TITLE("책제목","title"),
    ISBN("isbn","isbn"),
    KEYWORD("주제어","keyword"),
    CONTENTS("목차","contents"),
    OVERVIEW("책소개","overview"),
    PUBLISHER("출판사","publisher");

    private String displayName;
    private String target;

    SearchTarget(String displayName, String target) {
        this.displayName = displayName;
        this.target = target;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getTarget() {
        return target;
    }

    public String getId() {
        return name();
    }

    @Override
    public String toString() {
        return getTarget();
    }
}
