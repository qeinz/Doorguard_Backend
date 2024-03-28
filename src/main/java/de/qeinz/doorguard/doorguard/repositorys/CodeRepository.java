package de.qeinz.doorguard.doorguard.repositorys;

import de.qeinz.doorguard.doorguard.entitys.CodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * JavaDoc this file!
 * Created: 27.03.2024
 *
 * @author Nikk (dominik@minesort.de)
 */
public interface CodeRepository extends JpaRepository<CodeEntity, Long> {

    CodeEntity findByPassword(String code);

    List<CodeEntity> findByExpirationDateBefore(LocalDateTime expirationDate);

}