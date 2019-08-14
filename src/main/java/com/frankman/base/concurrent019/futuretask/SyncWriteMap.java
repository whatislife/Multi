package com.frankman.base.concurrent019.futuretask;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



public class SyncWriteMap {

    public static final Map<String, WriteFuture> syncKey = new ConcurrentHashMap<String, WriteFuture>();

}
