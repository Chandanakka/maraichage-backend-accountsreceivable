package net.maraichage.maraichagebackend.controller;

import lombok.RequiredArgsConstructor;
import net.maraichage.maraichagebackend.entity.AccountsReceivable;
import net.maraichage.maraichagebackend.service.AccountsReceivableService;
import net.maraichage.maraichagebackend.repository.AccountsReceivableRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.format.annotation.DateTimeFormat;


import java.io.IOException;
import java.util.Date;
import java.util.List;


import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("AccountsReceivable")
@RequiredArgsConstructor

public class AccountsReceivableController {

    private final AccountsReceivableService accountsReceivableService;


    //    @CrossOrigin(origins = "http://localhost")
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
    }

    @Autowired
    private AccountsReceivableRepository accountsReceivableRepository;

    @GetMapping("/data")
    public List<AccountsReceivable> getDataByDateRange(
            @RequestParam (name = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam (name = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
//        System.out.println("startDate: " + startDate);
//        System.out.println("endDate: " + endDate);
        return accountsReceivableRepository.findBygldateBetween(startDate, endDate);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> downloadImage(@PathVariable String id, String name, String type, String imageData ) {
        byte[] image_Data = accountsReceivableService.downloadImage(id);
/*        System.out.println("From Controller");
        System.out.println("===============");
        System.out.println("id:" + id);
        System.out.println("Name:" + name);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println("Time"+ dateFormat.format(date));
*/
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(IMAGE_JPEG_VALUE))
                .body(image_Data);
    }
}