package br.com.herois.service;

import br.com.herois.model.entities.Universo;
import br.com.herois.repository.UniversoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Configuration
public class UniversoService {

    @Autowired
    UniversoRepository universoRepository;

    public List<Universo> findAll() {
        return universoRepository.findAll();
    }

}
