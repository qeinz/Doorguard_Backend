package de.qeinz.doorguard.doorguard.restservice;

import de.qeinz.doorguard.doorguard.entitys.CodeEntity;
import de.qeinz.doorguard.doorguard.models.CodeRequest;
import de.qeinz.doorguard.doorguard.repositorys.CodeRepository;
import de.qeinz.doorguard.doorguard.utils.Methods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
public class DoorguardController {

    @Autowired
    private CodeRepository codeRepository;

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
}