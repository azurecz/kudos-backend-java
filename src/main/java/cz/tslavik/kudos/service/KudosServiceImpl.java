package cz.tslavik.kudos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.tslavik.kudos.KudosApplication;
import cz.tslavik.kudos.entity.Kudos;
import cz.tslavik.kudos.repository.cosmos.KudosRepository;

/**
 * Created by cen80475
 */
@Service
public class KudosServiceImpl implements IKudosService {

    @Autowired
    KudosRepository KudosRepository;

    @Override
    @Transactional
    public Kudos getKudosById(String id) {
        Kudos Kudos = KudosRepository.getKudosById(id).stream().findFirst().orElse(null);
        return Kudos;
    }

}
