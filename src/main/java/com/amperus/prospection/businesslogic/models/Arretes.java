package com.amperus.prospection.businesslogic.models;

public record Arretes(int nombreSantePubliqueEnCours, int nombrePerilPartiesCommunes, int nombreEquipementsCommunsEnCours) {
	public static class Builder {
		private int nombreSantePubliqueEnCours;
		private int nombrePerilPartiesCommunes;
		private int nombreEquipementsCommunsEnCours;

		public Builder nombreSantePubliqueEnCours(int nombreSantePubliqueEnCours) {
			this.nombreSantePubliqueEnCours = nombreSantePubliqueEnCours;
			return this;
		}

		public Builder nombrePerilPartiesCommunes(int nombrePerilPartiesCommunes) {
			this.nombrePerilPartiesCommunes = nombrePerilPartiesCommunes;
			return this;
		}

		public Builder nombreEquipementsCommunsEnCours(int nombreEquipementsCommunsEnCours) {
			this.nombreEquipementsCommunsEnCours = nombreEquipementsCommunsEnCours;
			return this;
		}

		public Arretes build() {
			return new Arretes(nombreSantePubliqueEnCours, nombrePerilPartiesCommunes, nombreEquipementsCommunsEnCours);
		}
	}
}
