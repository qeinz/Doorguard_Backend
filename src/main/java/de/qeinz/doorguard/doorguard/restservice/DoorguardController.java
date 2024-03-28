package de.qeinz.doorguard.doorguard.restservice;

import de.qeinz.doorguard.doorguard.entitys.AccountEntity;
import de.qeinz.doorguard.doorguard.entitys.CodeEntity;
import de.qeinz.doorguard.doorguard.models.CodeRequest;
import de.qeinz.doorguard.doorguard.models.LoginRequest;
import de.qeinz.doorguard.doorguard.repositorys.AccountRepository;
import de.qeinz.doorguard.doorguard.repositorys.CodeRepository;

import de.qeinz.doorguard.doorguard.utils.Methods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DoorguardController {

    @Autowired
    private CodeRepository codeRepository;

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/generate-code")
    public String generateCode(@RequestBody CodeRequest request) {
        final String password = Methods.generatePassword();

        CodeEntity codeEntity = new CodeEntity();
        codeEntity.setOnetimePassword(request.isOnetimePassword());
        codeEntity.setOnedayPassword(request.isOnedayPassword());
        codeEntity.setPassword(password);

        if (request.isOnedayPassword()) {
            LocalDate expirationDate = LocalDate.now().plusDays(1);
            codeEntity.setExpirationDate(expirationDate.atStartOfDay());
        }

        codeRepository.save(codeEntity);
        return password;
    }

    @GetMapping("/get-all-codes")
    public List<CodeEntity> getAllCodes() {
        return codeRepository.findAll();
    }

    @DeleteMapping("/delete-code/{code}")
    public ResponseEntity<String> deleteCode(@PathVariable String code) {
        CodeEntity codeEntity = codeRepository.findByPassword(code);
        if (codeEntity != null) {
            codeRepository.delete(codeEntity);
            return ResponseEntity.ok("Code successfully deleted.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/activate-code/{code}")
    public ResponseEntity<String> activateCode(@PathVariable String code) {
        CodeEntity codeEntity = codeRepository.findByPassword(code);
        if (codeEntity != null) {
            codeEntity.setActivated(true);
            codeRepository.save(codeEntity);
            return ResponseEntity.ok("Code successfully activated.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/deactivate-code/{code}")
    public ResponseEntity<String> deactivateCode(@PathVariable String code) {
        CodeEntity codeEntity = codeRepository.findByPassword(code);
        if (codeEntity != null) {
            codeEntity.setActivated(false);
            codeRepository.save(codeEntity);
            return ResponseEntity.ok("Code successfully deactivated.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/unlock-door/{code}")
    public ResponseEntity<String> unlockLock(@PathVariable String code) {
        CodeEntity codeEntity = codeRepository.findByPassword(code);
        if (codeEntity != null && codeEntity.isActivated()) {
            if (codeEntity.isOnetimePassword()) {
                codeRepository.delete(codeEntity);
            }
            return ResponseEntity.ok("Lock successfully unlocked.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        AccountEntity accountEntity =
                accountRepository.findByAccountNameAndAccountPassword(username, password);
        if (accountEntity != null) {
            String accountCode = accountEntity.getAccountCode();

            return ResponseEntity.ok(accountCode);
        } else {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }
    }


}