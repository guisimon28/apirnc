package com.amperus.prospection.adapters.primary;

import com.amperus.prospection.businesslogic.usecases.ImportRegistreNationalCopropriete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coproprietes")
public class BookingController {
    private final ImportRegistreNationalCopropriete importRegistreNationalCopropriete;

    public BookingController(ImportRegistreNationalCopropriete importRegistreNationalCopropriete) {
        this.importRegistreNationalCopropriete = importRegistreNationalCopropriete;
    }

    @PostMapping(path = "/import")
    public ResponseEntity<Void> importerRegistre() {
        importRegistreNationalCopropriete.handle();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}