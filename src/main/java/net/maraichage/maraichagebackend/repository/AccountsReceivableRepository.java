package net.maraichage.maraichagebackend.repository;

import net.maraichage.maraichagebackend.entity.AccountsReceivable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AccountsReceivableRepository extends JpaRepository<AccountsReceivable, String> {
    List<AccountsReceivable> findBygldateBetween(Date startDate, Date endDate);

}
