package cz.tslavik.kudos.repository.cosmos;

import com.microsoft.azure.spring.data.cosmosdb.repository.DocumentDbRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import cz.tslavik.kudos.entity.Kudos;

import java.util.List;

@Repository
public interface KudosRepository extends DocumentDbRepository<Kudos, String> {

    List<Kudos> getKudosById (String id);
    Page<Kudos> findAll(Pageable pageable);

}
