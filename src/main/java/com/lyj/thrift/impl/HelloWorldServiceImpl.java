package com.lyj.thrift.impl;

import org.apache.thrift.TException;

import com.lyj.thrift.HelloWorldService;

public class HelloWorldServiceImpl implements HelloWorldService.Iface{

	@Override
	public String sayHello(String username) throws TException {
		return username + " Welcom to thrift world ! ";
	}

}
