package net.maraichage.maraichagebackend.controller;

import lombok.RequiredArgsConstructor;
import net.maraichage.maraichagebackend.service.AccountsReceivableService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.format.annotation.DateTimeFormat;


import java.io.IOException;
import java.util.Date;

import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@CrossOrigin
@RestController
@RequestMapping("/AccountsReceivable")
@RequiredArgsConstructor

public class AccountsReceivableController {

    private final AccountsReceivableService accountsReceivableService;

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam ("glreceiptchequeno") String glreceiptchequeno,
                                         @RequestParam ("gldate") @DateTimeFormat(pattern = "yyyy-MM-dd")    Date gldate,
                                         @RequestParam ("glreceipttype") String glreceipttype,
                                         @RequestParam ("glreceivedfrom") String glreceivedfrom,
                                         @RequestParam ("glreceivedamount") double glreceivedamount,
                                         @RequestParam("glimage") MultipartFile glimage) throws IOException {
        String uploadImage = accountsReceivableService.uploadImage(glreceiptchequeno, gldate, glreceipttype,
                glreceivedfrom, glreceivedamount, glimage);
        return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
    }}
