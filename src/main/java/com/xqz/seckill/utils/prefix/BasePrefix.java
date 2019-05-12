package com.xqz.seckill.utils.prefix;

public abstract class BasePrefix implements KeyPrefix{
    private int expireSec;
    private String prefix;

    protected BasePrefix(int expireSec, String prefix){
        this.expireSec = expireSec;
        this.prefix = prefix;
    }

    @Override
    public int getExpireSec() {
        return expireSec;
    }

    @Override
    public String getPrefix() {
        return getClass().getSimpleName() + ":" + prefix + ":";
    }
}
