package com.contactor.model;

public class Adresse {
	private long id;
	private String rue;
	private String ville;
	private String codePostal;
	private String numero;

	public Adresse() {
		ini();
	}
	
	public Adresse(Adresse other){
		id = other.id;
		rue = other.rue;
		ville = other.ville;
		codePostal = other.codePostal;
		numero = other.numero;
	}
	
	private void ini(){
		id = 0;
		rue = "";
		ville = "";
		codePostal = "";
		numero = "";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	@Override
	public int hashCode() {
		return rue.hashCode() + codePostal.hashCode() + ville.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Adresse){
			Adresse other = (Adresse) obj;
			return other.codePostal == this.codePostal && other.rue == this.rue && other.ville == this.ville;
		}
		return super.equals(obj);
	}
}
