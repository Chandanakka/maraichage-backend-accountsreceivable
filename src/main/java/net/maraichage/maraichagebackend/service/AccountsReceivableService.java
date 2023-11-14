package net.maraichage.maraichagebackend.service;

import net.maraichage.maraichagebackend.entity.AccountsReceivable;
import net.maraichage.maraichagebackend.repository.AccountsReceivableRepository;
import net.maraichage.maraichagebackend.com.example.util.ImageUtils;
import lombok.RequiredArgsConstructor;
// import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
@RequiredArgsConstructor

public class AccountsReceivableService {
    private final AccountsReceivableRepository accountsReceivableRepository;

    public String uploadImage(String glreceiptchequeno, Date gldate, String glreceipttype,
                              String glreceivedfrom, double glreceivedamount,
                              MultipartFile imageFile) throws IOException {
        var imageToSave = AccountsReceivable.builder()
                .glreceiptchequeno(glreceiptchequeno)
                .gldate(gldate)
                .glreceipttype(glreceipttype)
                .glreceivedfrom(glreceivedfrom)
                .glreceivedamount(glreceivedamount)
                .name(imageFile.getOriginalFilename())
                .type(imageFile.getContentType())
                .glimage(ImageUtils.compressImage(imageFile.getBytes()))
                .build();

        accountsReceivableRepository.save(imageToSave);
        return "file uploaded successfully : " + imageFile.getOriginalFilename() + "  " + imageToSave;
    }

}
