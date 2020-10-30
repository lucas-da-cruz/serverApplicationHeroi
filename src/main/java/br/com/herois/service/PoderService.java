package br.com.herois.service;

import br.com.herois.model.entities.Poder;
import br.com.herois.repository.PoderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Configuration
public class PoderService {

    @Autowired
    PoderRepository poderRepository;

    public List<Poder> findAll() {
        return poderRepository.findAll();
    }

}
