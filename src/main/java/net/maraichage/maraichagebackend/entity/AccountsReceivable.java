package net.maraichage.maraichagebackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.Date;

//import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tblGeneralLedgerReceivables")

public class AccountsReceivable {
    @Id
    private String glreceiptchequeno;

    private Date gldate;
    private String glreceipttype;
    private String glreceivedfrom;
    private double glreceivedamount;


    @Lob
    @Column(nullable = true)
    private byte[] glimage;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private String type;
}