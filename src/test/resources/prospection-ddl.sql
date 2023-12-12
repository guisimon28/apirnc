CREATE TABLE regions (
    id UUID PRIMARY KEY,
    code_officiel VARCHAR(255),
    nom_officiel VARCHAR(255)
);

CREATE TABLE departements (
    id UUID PRIMARY KEY,
    code_officiel VARCHAR(255),
    nom_officiel VARCHAR(255),
    region_id UUID,
    FOREIGN KEY (region_id) REFERENCES regions(id)
);

CREATE TABLE villes (
    id UUID PRIMARY KEY,
    code_officiel VARCHAR(255),
    nom_officiel VARCHAR(255),
    code_officiel_arrondissement VARCHAR(255),
    nom_officiel_arrondissement VARCHAR(255),
    departement_id UUID,
    FOREIGN KEY (departement_id) REFERENCES departements(id)
);

CREATE TABLE cadastres (
    id UUID PRIMARY KEY,
    nombre_parcelles INT
);

CREATE TABLE informations_cadastrales (
    id UUID PRIMARY KEY,
    reference VARCHAR(255),
    code_insee_commune VARCHAR(255),
    prefixe VARCHAR(255),
    "section" VARCHAR(255),
    numero_parcelle VARCHAR(255),
    cadastre_id UUID,
    FOREIGN KEY (cadastre_id) REFERENCES cadastres(id)
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
    latitude DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL,
    ville_id UUID,
    cadastre_id UUID,
    FOREIGN KEY (ville_id) REFERENCES villes(id),
    FOREIGN KEY (cadastre_id) REFERENCES cadastres(id)
);

CREATE TABLE syndicats (
    id UUID PRIMARY KEY,
    type VARCHAR(255),
    nom VARCHAR(255),
    siret VARCHAR(255),
    code_ape VARCHAR(255),
    cooperatif boolean default false,
    nombre_association_syndicale_libre INT,
    nombre_association_fonciere_urbaine_libre INT,
    nombre_unions_syndicats INT
);

CREATE TABLE mandats (
    copropriete_id UUID,
    syndicat_id UUID,
    statut VARCHAR(255),

    PRIMARY KEY (copropriete_id, syndicat_id),
    FOREIGN KEY (copropriete_id) REFERENCES coproprietes(id),
    FOREIGN KEY (syndicat_id) REFERENCES syndicats(id)
);