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

CREATE TABLE coproprietes (
    id UUID PRIMARY KEY,
    numero_immatriculation VARCHAR(55),
    nom_usage VARCHAR(255),
    numero_et_voie VARCHAR(255),
    latitude DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL,
    ville_id UUID,
    FOREIGN KEY (ville_id) REFERENCES villes(id)
);
