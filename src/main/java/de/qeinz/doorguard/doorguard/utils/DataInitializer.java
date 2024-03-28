package de.qeinz.doorguard.doorguard.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import de.qeinz.doorguard.doorguard.entitys.AccountEntity;
import de.qeinz.doorguard.doorguard.repositorys.AccountRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void run(String... args) {
        if (accountRepository.count() == 0) {
            AccountEntity admin = new AccountEntity();
            admin.setAccountName("admin");
            admin.setAccountCode("admin");
            admin.setAccountPassword("admin");
            accountRepository.save(admin);
            System.out.println("Standardbenutzer 'admin' erstellt.");
        }
    }
}