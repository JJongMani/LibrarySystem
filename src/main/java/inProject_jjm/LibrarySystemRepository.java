package inProject_jjm;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface LibrarySystemRepository extends PagingAndSortingRepository<LibrarySystem, Long>{
    Optional<LibrarySystem> findById(Long id);

}