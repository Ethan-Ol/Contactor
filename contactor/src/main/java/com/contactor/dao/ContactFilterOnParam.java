package com.contactor.dao;


public abstract class ContactFilterOnParam implements ContactFilter{
	protected String m_param;
	public ContactFilterOnParam(String param) {
		m_param = param;
	}
}
