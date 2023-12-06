package com.amperus.prospection.businesslogic.models;

import java.time.LocalDate;

public record Caracteristique(LocalDate dateReglement, boolean residenceService, boolean numeroImmatriculationPrincipal, String periodeConstruction, boolean dansActionCoeurDeVille, boolean dansPetiteVilleDeDemain, boolean aidee) {
	public static class Builder {
		private LocalDate dateReglement;
		private boolean residenceService;
		private boolean numeroImmatriculationPrincipal;
		private String periodeConstruction;
		private boolean dansActionCoeurDeVille;
		private boolean dansPetiteVilleDeDemain;
		private boolean aidee;

		public Builder dateReglement(LocalDate dateReglement) {
			this.dateReglement = dateReglement;
			return this;
		}

		public Builder residenceService(boolean residenceService) {
			this.residenceService = residenceService;
			return this;
		}

		public Builder numeroImmatriculationPrincipal(boolean numeroImmatriculationPrincipal) {
			this.numeroImmatriculationPrincipal = numeroImmatriculationPrincipal;
			return this;
		}

		public Builder periodeConstruction(String periodeConstruction) {
			this.periodeConstruction = periodeConstruction;
			return this;
		}

		public Builder dansActionCoeurDeVille(boolean dansActionCoeurDeVille) {
			this.dansActionCoeurDeVille = dansActionCoeurDeVille;
			return this;
		}

		public Builder dansPetiteVilleDeDemain(boolean dansPetiteVilleDeDemain) {
			this.dansPetiteVilleDeDemain = dansPetiteVilleDeDemain;
			return this;
		}

		public Builder aidee(boolean aidee) {
			this.aidee = aidee;
			return this;
		}

		public Caracteristique build() {
			return new Caracteristique(dateReglement, residenceService, numeroImmatriculationPrincipal, periodeConstruction, dansActionCoeurDeVille, dansPetiteVilleDeDemain, aidee);
		}
	}
}