package com.amperus.prospection.businesslogic.models;

public record InformationCadastrale(String reference, String codeINSEECommune, String prefixe, String section, String numeroParcelle) {
	public static class Builder {
		private String reference;
		private String codeInseeCommune;
		private String prefixe;
		private String section;
		private String numeroParcelle;

		public Builder reference(String reference) {
			this.reference = reference;
			return this;
		}

		public Builder codeINSEECommune(String codeINSEECommune) {
			this.codeInseeCommune = codeINSEECommune;
			return this;
		}

		public Builder prefixe(String prefixe) {
			this.prefixe = prefixe;
			return this;
		}

		public Builder section(String section) {
			this.section = section;
			return this;
		}

		public Builder numeroParcelle(String numeroParcelle) {
			this.numeroParcelle = numeroParcelle;
			return this;
		}

		public InformationCadastrale build() {
			return new InformationCadastrale(reference, codeInseeCommune, prefixe, section, numeroParcelle);
		}
	}
}