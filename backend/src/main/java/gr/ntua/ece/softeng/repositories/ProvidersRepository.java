package gr.ntua.ece.softeng.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import gr.ntua.ece.softeng.entities.Providers;

public interface ProvidersRepository extends JpaRepository<Providers, Long> {
	Providers findByCompanyName(String companyName);
	Providers findByUserName(String userName);
}
