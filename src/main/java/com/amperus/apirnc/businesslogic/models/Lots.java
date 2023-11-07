package com.amperus.apirnc.businesslogic.models;

public record Lots(int nombreTotal, int nombreUsageHabitationBureauxCommerces, int nombreUsageHabitation, int nombreStationnement) {
	public static class Builder {
		private int nombreTotal;
		private int nombreUsageHabitationBureauxCommerces;
		private int nombreUsageHabitation;
		private int nombreStationnement;

		public Builder nombreTotal(int nombreTotal) {
			this.nombreTotal = nombreTotal;
			return this;
		}

		public Builder nombreUsageHabitationBureauxCommerces(int nombreUsageHabitationBureauxCommerces) {
			this.nombreUsageHabitationBureauxCommerces = nombreUsageHabitationBureauxCommerces;
			return this;
		}

		public Builder nombreUsageHabitation(int nombreUsageHabitation) {
			this.nombreUsageHabitation = nombreUsageHabitation;
			return this;
		}

		public Builder nombreStationnement(int nombreStationnement) {
			this.nombreStationnement = nombreStationnement;
			return this;
		}

		public Lots build() {
			return new Lots(nombreTotal, nombreUsageHabitationBureauxCommerces, nombreUsageHabitation, nombreStationnement);
		}
	}

	public static Builder builder() {
		return new Builder();
	}
}