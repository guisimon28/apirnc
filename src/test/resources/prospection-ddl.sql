CREATE TABLE regions (
 id BIGSERIAL PRIMARY KEY,
 code_officiel VARCHAR(255),
 nom_officiel VARCHAR(255)
);

CREATE TABLE departements (
 id BIGSERIAL PRIMARY KEY,
 code_officiel VARCHAR(255),
 nom_officiel VARCHAR(255),
 region_id BIGINT NULL,
 FOREIGN KEY (region_id) REFERENCES regions(id)
);

CREATE TABLE villes (
 id BIGSERIAL PRIMARY KEY,
 code_officiel VARCHAR(255),
 nom_officiel VARCHAR(255),
 code_officiel_arrondissement VARCHAR(255),
 nom_officiel_arrondissement VARCHAR(255),
 departement_id BIGINT NULL,
 FOREIGN KEY (departement_id) REFERENCES departements(id)
);

CREATE TABLE coproprietes (
 id UUID PRIMARY KEY,
 numero_immatriculation VARCHAR(55),
 nom_usage VARCHAR(255),
 date_reglement DATE,
 residence_service boolean default false,
 periode_construction VARCHAR(255),
 dans_action_coeur_de_ville boolean default false,
 dans_petite_ville_de_demain boolean default false,
 aidee boolean default false,
 nombre_lot_total INT,
 nombre_lot_usage_habitation_bureaux_commerces INT,
 nombre_lot_usage_habitation INT,
 nombre_lot_stationnement INT,
 numero_et_voie VARCHAR(255),
 numero_et_voie_gpx VARCHAR(255),
 latitude DOUBLE PRECISION NOT NULL,
 longitude DOUBLE PRECISION NOT NULL,
 ville_id BIGINT NULL,
 FOREIGN KEY (ville_id) REFERENCES villes(id)
);

CREATE TABLE informations_cadastrales (
 id BIGSERIAL PRIMARY KEY,
 reference VARCHAR(255),
 code_insee_commune VARCHAR(255),
 prefixe VARCHAR(255),
 "section" VARCHAR(255),
 numero_parcelle VARCHAR(255),
 copropriete_id UUID,
 FOREIGN KEY (copropriete_id) REFERENCES coproprietes(id)
);

CREATE TABLE syndicats (
 id UUID PRIMARY KEY,
 raison_sociale VARCHAR(255),
 siret VARCHAR(255),
 type VARCHAR(255),
 code_ape VARCHAR(255),
 cooperatif boolean default false
);

CREATE TABLE mandats (
 copropriete_id UUID,
 syndicat_id UUID,
 statut VARCHAR(255),

 PRIMARY KEY (copropriete_id, syndicat_id),
 FOREIGN KEY (copropriete_id) REFERENCES coproprietes(id),
 FOREIGN KEY (syndicat_id) REFERENCES syndicats(id)
);

CREATE INDEX idx_numero_immatriculation ON coproprietes (numero_immatriculation);
CREATE INDEX idx_siret ON syndicats (siret);
CREATE INDEX idx_ville ON villes (code_officiel,nom_officiel,code_officiel_arrondissement);