package com.tang.testdemo.annotaion;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * 简介：
 *
 * @author 王强（249346528@qq.com） 2017/8/3.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerFragment {
}
