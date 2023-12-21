package com.amperus.prospection.businesslogic.models;

public record Region(String code, String nom) {
	public static class Builder {
		private String code;
		private String nom;

		public Builder code(String codeOfficiel) {
			this.code = codeOfficiel;
			return this;
		}

		public Builder nom(String nomOfficiel) {
			this.nom = nomOfficiel;
			return this;
		}

		public Region build() {
			return new Region(code, nom);
		}
	}

}