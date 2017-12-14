package gr.ntua.ece.softeng;

import org.springframework.data.jpa.repository.JpaRepository;
import gr.ntua.ece.softeng.Providers;

public interface ProvidersRepository extends JpaRepository<Providers, Long> {
	Providers findByCompanyName(String companyName);
}
