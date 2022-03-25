package com.precious.truecaller.data.models.user;

public class Limitation {
    public static final Integer MAX_FREE_USER_CONTACTS = 10_000;
    public static final Integer MAX_FREE_USER_BLOCKED_CONTACTS = 100;
                       
    public static final Integer MAX_GOLD_USER_CONTACTS = 100_000;
    public static final Integer MAX_GOLD_USER_BLOCKED_CONTACTS = 1000;

    public static final Integer MAX_PLATINUM_USER_CONTACTS = Integer.MAX_VALUE;
    public static final Integer MAX_PLATINUM_USER_BLOCKED_CONTACTS = 100_000;
}
