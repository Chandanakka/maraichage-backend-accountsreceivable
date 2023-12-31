package net.maraichage.maraichagebackend.service;

import net.maraichage.maraichagebackend.entity.AccountsReceivable;
import net.maraichage.maraichagebackend.repository.AccountsReceivableRepository;
import net.maraichage.maraichagebackend.com.example.util.ImageUtils;
import lombok.RequiredArgsConstructor;
// import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.apache.commons.lang3.exception.ContextedRuntimeException;
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
    public byte[] downloadImage(String glvoucherno) {
        Optional<AccountsReceivable> dbImage = accountsReceivableRepository.findById(glvoucherno);
        return dbImage.map(accountspayable -> {

            try {
/*
                System.out.println("from service id:" + accountspayable.getGlvoucherno());
                System.out.println("==============================");
                System.out.println("Date:" + accountspayable.getGldate());
                System.out.println("Name:" + accountspayable.getName());
                System.out.println("Amount:" + accountspayable.getGlpaidamount());
                System.out.println("Type:" + accountspayable.getType());

                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                System.out.println("Time"+ dateFormat.format(date));

                System.out.println("Imagedata:" + accountspayable.getGlimage());
*/              return ImageUtils.decompressImage(accountspayable.getGlimage());
            } catch (DataFormatException | IOException exception) {
                throw new ContextedRuntimeException("Error downloading an image", exception);
            }
        }).orElse(null);
    }
}
