package com.amperus.prospection.businesslogic.models;

public record CoordonneesGeographiques(
		double longitude,
		double latitude) {
	
	public static class Builder {
		private double longitude;
		private double latitude;

		public Builder longitude(double longitude) {
			this.longitude = longitude;
			return this;
		}

		public Builder latitude(double latitude) {
			this.latitude = latitude;
			return this;
		}

		public CoordonneesGeographiques build() {
			return new CoordonneesGeographiques(longitude, latitude);
		}
	}
}