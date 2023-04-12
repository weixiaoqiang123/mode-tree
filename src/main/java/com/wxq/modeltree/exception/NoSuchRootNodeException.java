package com.wxq.modeltree.exception;

/**
 * @author weixiaoqiang
 * @date 2023/3/16
 **/
public class NoSuchRootNodeException extends RuntimeException {

    public NoSuchRootNodeException(String message){
        super(message);
    }
}
