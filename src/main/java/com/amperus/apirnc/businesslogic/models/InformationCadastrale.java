package com.amperus.apirnc.businesslogic.models;

public record InformationCadastrale(String reference, long codeINSEECommune, String prefixe, String section, String numeroParcelle) {
	public static class Builder {
		private String reference;
		private long codeINSEECommune;
		private String prefixe;
		private String section;
		private String numeroParcelle;

		public Builder reference(String reference) {
			this.reference = reference;
			return this;
		}

		public Builder codeINSEECommune(long codeINSEECommune) {
			this.codeINSEECommune = codeINSEECommune;
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
			return new InformationCadastrale(reference, codeINSEECommune, prefixe, section, numeroParcelle);
		}
	}
}