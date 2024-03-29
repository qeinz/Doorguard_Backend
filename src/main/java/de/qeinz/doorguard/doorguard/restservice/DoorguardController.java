package de.qeinz.doorguard.doorguard.restservice;

import de.qeinz.doorguard.doorguard.entitys.AccountEntity;
import de.qeinz.doorguard.doorguard.entitys.CodeEntity;
import de.qeinz.doorguard.doorguard.entitys.UsedCodeEntity;
import de.qeinz.doorguard.doorguard.models.AccountRequest;
import de.qeinz.doorguard.doorguard.models.CodeRequest;
import de.qeinz.doorguard.doorguard.models.LoginRequest;
import de.qeinz.doorguard.doorguard.repositorys.AccountRepository;
import de.qeinz.doorguard.doorguard.repositorys.CodeRepository;

import de.qeinz.doorguard.doorguard.repositorys.UsedCodeRepository;
import de.qeinz.doorguard.doorguard.utils.LockOpener;
import de.qeinz.doorguard.doorguard.utils.Methods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class DoorguardController {

    @Autowired
    private CodeRepository codeRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UsedCodeRepository usedCodeRepository;

    @PostMapping("/generate-code")
    public String generateCode(@RequestBody CodeRequest request) {
        Optional<AccountEntity> accountEntityOptional =
                accountRepository.findByAccountCode(request.getAccountCode());
        if (accountEntityOptional.isPresent()) {
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
        return null;
    }

    @GetMapping("/get-all-codes")
    public List<CodeEntity> getAllCodes() {
        return codeRepository.findAll();
    }


    @GetMapping("/get-codes-history/{code}")
    public List<UsedCodeEntity> getAllCodes(@PathVariable String code) {
        Optional<AccountEntity> accountEntityOptional = accountRepository.findByAccountCode(code);
        if (accountEntityOptional.isPresent()) {
            return usedCodeRepository.findAll();
        } else {
            return Collections.emptyList();
        }
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

            boolean isOneTimePassword = codeEntity.isOnetimePassword();
            boolean isOneDayPassword = codeEntity.isOnedayPassword();

            UsedCodeEntity codeUsedEntity = new UsedCodeEntity();
            codeUsedEntity.setUsedCode(code);
            codeUsedEntity.setUsageTime(LocalDateTime.now());
            codeUsedEntity.setOneDayPassword(isOneDayPassword);
            codeUsedEntity.setOneTimePassword(isOneTimePassword);
            usedCodeRepository.save(codeUsedEntity);

            if (isOneTimePassword) {
                codeRepository.delete(codeEntity);
            }

            //TODO: Einbauen des Lockopener.unlockLock

            return ResponseEntity.ok("Lock successfully unlocked.");
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/unlock-door-admin/{code}")
    public ResponseEntity<String> unlockAdmin(@PathVariable String code) {
        Optional<AccountEntity> accountEntityOptional = accountRepository.findByAccountCode(code);
        if (accountEntityOptional.isPresent()) {

            UsedCodeEntity codeUsedEntity = new UsedCodeEntity();
            codeUsedEntity.setUsedCode(code);
            codeUsedEntity.setUsageTime(LocalDateTime.now());
            codeUsedEntity.setOneDayPassword(false);
            codeUsedEntity.setOneTimePassword(false);
            usedCodeRepository.save(codeUsedEntity);

            //TODO: Einbauen des Lockopener.unlockLock

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

    @PutMapping("/update-password")
    public ResponseEntity<String> updatePassword(@RequestBody AccountRequest request) {
        String code = request.getAccountCode();
        String newPassword = request.getPassword();

        CodeEntity codeEntity = codeRepository.findByPassword(code);
        if (codeEntity != null && codeEntity.isActivated()) {
            codeEntity.setPassword(newPassword);
            codeRepository.save(codeEntity);
            return ResponseEntity.ok("Password successfully updated.");
        } else {
            Optional<AccountEntity> accountEntityOptional = accountRepository.findByAccountCode(code);
            if (accountEntityOptional.isPresent()) {
                AccountEntity accountEntity = accountEntityOptional.get();
                accountEntity.setAccountPassword(newPassword);
                accountRepository.save(accountEntity);
                return ResponseEntity.ok("Password successfully updated.");
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }

    @PutMapping("/update-name")
    public ResponseEntity<String> updateName(@RequestBody AccountRequest request) {
        String code = request.getAccountCode();
        String newName = request.getName();

        Optional<AccountEntity> accountEntityOptional = accountRepository.findByAccountCode(code);
        if (accountEntityOptional.isPresent()) {
            AccountEntity accountEntity = accountEntityOptional.get();
            accountEntity.setAccountName(newName);
            accountRepository.save(accountEntity);
            return ResponseEntity.ok("Name successfully updated.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update-code")
    public ResponseEntity<String> updateCode(@RequestBody AccountRequest request) {
        String code = request.getAccountCode();
        String newCode = request.getNewAccountCode();

        Optional<AccountEntity> accountEntityOptional = accountRepository.findByAccountCode(code);
        if (accountEntityOptional.isPresent()) {
            AccountEntity accountEntity = accountEntityOptional.get();
            accountEntity.setAccountCode(newCode);
            accountRepository.save(accountEntity);
            return ResponseEntity.ok("Code successfully updated.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}