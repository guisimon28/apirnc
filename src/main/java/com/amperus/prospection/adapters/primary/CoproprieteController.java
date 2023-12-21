package com.amperus.prospection.adapters.primary;

import com.amperus.prospection.businesslogic.models.Copropriete;
import com.amperus.prospection.businesslogic.usecases.GetCopropriete;
import com.amperus.prospection.businesslogic.usecases.ImportRegistreNationalCopropriete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coproprietes")
public class CoproprieteController {
    private final ImportRegistreNationalCopropriete importRegistreNationalCopropriete;

    private final GetCopropriete getCopropriete;

    public CoproprieteController(ImportRegistreNationalCopropriete importRegistreNationalCopropriete, GetCopropriete getCopropriete) {
        this.importRegistreNationalCopropriete = importRegistreNationalCopropriete;
        this.getCopropriete = getCopropriete;
    }

    @PostMapping(path = "/import")
    public ResponseEntity<Void> importerRegistre() {
        importRegistreNationalCopropriete.handle();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "/find/{numeroImmatriculation}")
    public ResponseEntity<Copropriete> findByNumeroImmatriculation(@PathVariable String numeroImmatriculation) {
        return getCopropriete.byNumeroImmatriculation(numeroImmatriculation)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}