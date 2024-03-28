package de.qeinz.doorguard.doorguard.repositorys;

import de.qeinz.doorguard.doorguard.entitys.CodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * JavaDoc this file!
 * Created: 27.03.2024
 *
 * @author Nikk (dominik@minesort.de)
 */
public interface CodeRepository extends JpaRepository<CodeEntity, Long> {

    CodeEntity findByPassword(String code);

}