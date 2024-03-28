package de.qeinz.doorguard.doorguard.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import de.qeinz.doorguard.doorguard.entitys.CodeEntity;
import de.qeinz.doorguard.doorguard.repositorys.CodeRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * JavaDoc this file!
 * Created: 28.03.2024
 *
 * @author Nikk (dominik@minesort.de)
 */

@Component
public class CronJob {

    @Autowired
    private CodeRepository codeRepository;

    @Scheduled(fixedDelay = 60000)
    public void checkExpiredCodes() {
        LocalDateTime now = LocalDateTime.now();
        List<CodeEntity> expiredCodes = codeRepository.findByExpirationDateBefore(now);
        for (CodeEntity code : expiredCodes) {
            codeRepository.delete(code);
            System.out.println("Expired code deleted: " + code.getPassword());
        }
    }

}
