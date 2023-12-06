package com.amperus.prospection.businesslogic.models;

public record RepresentantLegal(String nom, String siret, String codeAPE, String commune) {
	public static class Builder {
		private String nom;
		private String siret;
		private String codeAPE;
		private String commune;

		public Builder nom(String nom) {
			this.nom = nom;
			return this;
		}

		public Builder siret(String siret) {
			this.siret = siret;
			return this;
		}

		public Builder codeAPE(String codeAPE) {
			this.codeAPE = codeAPE;
			return this;
		}

		public Builder commune(String commune) {
			this.commune = commune;
			return this;
		}

		public RepresentantLegal build() {
			return new RepresentantLegal(nom, siret, codeAPE, commune);
		}
	}

}