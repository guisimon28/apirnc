package com.amperus.prospection.businesslogic.models;

public record QuartierPrioritaire(String nom, String code) {
	public static class Builder {
		private String nom;
		private String code;

		public Builder nom(String nom) {
			this.nom = nom;
			return this;
		}

		public Builder code(String code) {
			this.code = code;
			return this;
		}

		public QuartierPrioritaire build() {
			return new QuartierPrioritaire(nom, code);
		}
	}

}