package com.amperus.apirnc.businesslogic.models;

public record EtablissementPublicCooperationIntercommunale(String siret, String codeOfficiel, String nomOfficiel) {
	public static class Builder {
		private String siret;
		private String codeOfficiel;
		private String nomOfficiel;

		public Builder siret(String siret) {
			this.siret = siret;
			return this;
		}

		public Builder codeOfficiel(String codeOfficiel) {
			this.codeOfficiel = codeOfficiel;
			return this;
		}

		public Builder nomOfficiel(String nomOfficiel) {
			this.nomOfficiel = nomOfficiel;
			return this;
		}

		public EtablissementPublicCooperationIntercommunale build() {
			return new EtablissementPublicCooperationIntercommunale(siret, codeOfficiel, nomOfficiel);
		}
	}

	public static Builder builder() {
		return new Builder();
	}
}