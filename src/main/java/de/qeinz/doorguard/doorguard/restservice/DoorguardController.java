package de.qeinz.doorguard.doorguard.restservice;

import de.qeinz.doorguard.doorguard.entitys.CodeEntity;
import de.qeinz.doorguard.doorguard.models.CodeRequest;
import de.qeinz.doorguard.doorguard.repositorys.CodeRepository;
import de.qeinz.doorguard.doorguard.utils.Methods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

        codeRepository.save(codeEntity);
        return password;
    }

    @GetMapping("/get-all-codes")
    public List<CodeEntity> getAllCodes() {
        return codeRepository.findAll();
    }
}