package com.amperus.prospection.businesslogic.models;

import java.util.List;

public record Cadastre(List<InformationCadastrale> informations, int nombreParcelles) {
	public static class Builder {
		private List<InformationCadastrale> informations;
		private int nombreParcelles;

		public Builder informations(List<InformationCadastrale> informations) {
			this.informations = informations;
			return this;
		}

		public Builder nombreParcelles(int nombreParcelles) {
			this.nombreParcelles = nombreParcelles;
			return this;
		}

		public Cadastre build() {
			return new Cadastre(informations, nombreParcelles);
		}
	}
}