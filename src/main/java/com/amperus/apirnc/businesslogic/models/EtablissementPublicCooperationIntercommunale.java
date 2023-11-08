package com.amperus.apirnc.businesslogic.models;

public record EtablissementPublicCooperationIntercommunale(String siret, String codeOfficiel, String nomOfficiel) {
	public static class Builder {
		private String siren;
		private String codeOfficiel;
		private String nomOfficiel;

		public Builder siren(String siren) {
			this.siren = siren;
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
			return new EtablissementPublicCooperationIntercommunale(siren, codeOfficiel, nomOfficiel);
		}
	}

}