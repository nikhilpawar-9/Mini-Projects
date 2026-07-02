package com.sit.example.layer4;

public class TeaAlreadyExistsException extends RuntimeException
{
	public TeaAlreadyExistsException(String str) {
		super(str);
	}
}
