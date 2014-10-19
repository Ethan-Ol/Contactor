package com.contactor.dao;

import java.util.HashMap;

import com.contactor.model.Adresse;

public class DAOAdresseSimulate implements DAOAdresses{

	HashMap<Long, Adresse> m_adresses;
	private long nextId;
	private static DAOAdresseSimulate instance = null; 

	private DAOAdresseSimulate() {
		m_adresses = new HashMap<Long, Adresse>();
		nextId = 0;
	}

	public static DAOAdresseSimulate getInstance(){
		if(instance == null){
			instance = new DAOAdresseSimulate();
		}
		return instance;
	}

	@Override
	public boolean insertAdresse(Adresse adresse) {
		if(m_adresses.containsKey(adresse.getId()))
			return false;
		else
			m_adresses.put(adresse.getId(), adresse);
		return true;
	}

	@Override
	public Adresse getAdresse(long id) {
		return m_adresses.get(id);
	}

	@Override
	public boolean updateAdresse(Adresse adresse) {
		if(m_adresses.containsKey(adresse.getId())){
			Adresse a = m_adresses.get(adresse.getId());
			a.setCodePostal(adresse.getCodePostal());
			a.setRue(adresse.getRue());
			a.setVille(adresse.getVille());
			return true;
		}
		else
			return false;
	}

	@Override
	public boolean removeAdresse(long id) {
		return m_adresses.remove(id) != null;
	}

	@Override
	public long generateId() {
		return nextId++;
	}

}
