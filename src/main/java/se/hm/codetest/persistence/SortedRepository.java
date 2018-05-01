package se.hm.codetest.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.hm.codetest.persistence.model.Sorted;

@Repository
public interface SortedRepository extends JpaRepository<Sorted, Long> {
}
