package com.amperus.apirnc.businesslogic.models;

public record Adresse(
		String numeroEtVoie,
		String codePostal,
		Ville ville,
		CoordonneesGeographiques coordonneesGeographiques) {

	public static class Builder {
		private String numeroEtVoie;
		private String codePostal;
		private Ville ville;
		private CoordonneesGeographiques coordonneesGeographiques;

		public Builder numeroEtVoie(String numeroEtVoie) {
			this.numeroEtVoie = numeroEtVoie;
			return this;
		}

		public Builder codePostal(String codePostal) {
			this.codePostal = codePostal;
			return this;
		}

		public Builder ville(Ville ville) {
			this.ville = ville;
			return this;
		}

		public Builder coordonneesGeographiques(CoordonneesGeographiques coordonneesGeographiques) {
			this.coordonneesGeographiques = coordonneesGeographiques;
			return this;
		}

		public Adresse build() {
			return new Adresse(numeroEtVoie, codePostal, ville, coordonneesGeographiques);
		}
	}

}