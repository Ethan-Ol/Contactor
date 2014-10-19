package com.contactor.dao;

import com.contactor.model.Adresse;

public interface DAOAdresses extends DAOObject{
	public boolean insertAdresse(Adresse adresse);
	public Adresse getAdresse (long id);
	public boolean updateAdresse (Adresse adresse);
	public boolean removeAdresse (long id);
}
