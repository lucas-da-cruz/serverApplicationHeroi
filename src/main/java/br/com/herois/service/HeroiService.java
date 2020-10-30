package br.com.herois.service;

import br.com.herois.model.entities.Heroi;
import br.com.herois.model.form.HeroiForm;
import br.com.herois.repository.HeroiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Configuration
public class HeroiService {

    @Autowired
    HeroiRepository heroiRepository;

    public List<Heroi> findAll() {
        return heroiRepository.findAll();
    }

    public Optional<Heroi> findById(Long id){
        return heroiRepository.findById(id);
    }

    public Heroi insert(HeroiForm form) {
        Heroi heroi = form.converterHeroi();
        return heroiRepository.save(heroi);
    }


}
