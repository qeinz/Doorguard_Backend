package de.qeinz.doorguard.doorguard.repositorys;

import de.qeinz.doorguard.doorguard.entitys.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JavaDoc this file!
 * Created: 28.03.2024
 *
 * @author Nikk (dominik@minesort.de)
 */
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    AccountEntity findByAccountNameAndAccountPassword(String accountName, String accountPassword);
}