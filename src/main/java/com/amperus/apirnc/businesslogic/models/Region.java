package com.amperus.apirnc.businesslogic.models;

public record Region(String codeOfficiel, String nomOfficiel) {
	public static class Builder {
		private String codeOfficiel;
		private String nomOfficiel;

		public Builder codeOfficiel(String codeOfficiel) {
			this.codeOfficiel = codeOfficiel;
			return this;
		}

		public Builder nomOfficiel(String nomOfficiel) {
			this.nomOfficiel = nomOfficiel;
			return this;
		}

		public Region build() {
			return new Region(codeOfficiel, nomOfficiel);
		}
	}

}