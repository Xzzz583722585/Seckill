package com.xqz.seckill.common.prefix;

public abstract class BasePrefix implements KeyPrefix{
    private int expireSec;
    private String prefix;

    protected BasePrefix(String prefix){
        this.expireSec = 0;
        this.prefix = prefix;
    }

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
