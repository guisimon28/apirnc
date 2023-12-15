package com.amperus.prospection.adapters.primary;

import com.amperus.prospection.businesslogic.usecases.CompleterNumeroEtVoie;
import com.amperus.prospection.businesslogic.usecases.ImportRegistreNationalCopropriete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coproprietes")
public class CoproprieteController {
    private final ImportRegistreNationalCopropriete importRegistreNationalCopropriete;

    private final CompleterNumeroEtVoie completerNumeroEtVoie;

    public CoproprieteController(ImportRegistreNationalCopropriete importRegistreNationalCopropriete, CompleterNumeroEtVoie completerNumeroEtVoie) {
        this.importRegistreNationalCopropriete = importRegistreNationalCopropriete;
        this.completerNumeroEtVoie = completerNumeroEtVoie;
    }

    @PostMapping(path = "/import")
    public ResponseEntity<Void> importerRegistre() {
        importRegistreNationalCopropriete.handle();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(path = "/completeMissingStreet")
    public ResponseEntity<Void> completeMissingStreet() {
        completerNumeroEtVoie.handle();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}