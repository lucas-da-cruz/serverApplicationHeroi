package br.com.herois.service;

import br.com.herois.model.dto.HeroiTabelaDto;
import br.com.herois.model.dto.UsuarioAdminDto;
import br.com.herois.model.entities.Heroi;
import br.com.herois.model.entities.UsuarioAdmin;
import br.com.herois.model.form.HeroiForm;
import br.com.herois.model.form.UsuarioAdminFormUpdate;
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
    @Autowired
    UsuarioAdminService usuarioAdminService;

    public List<HeroiTabelaDto> findAll() {
        List<Heroi> heroiList = heroiRepository.findAll();
        List<HeroiTabelaDto> heroiTabelaDto = new ArrayList<>();
        heroiList.forEach(a -> {
            heroiTabelaDto.add(new HeroiTabelaDto(a));
        });
        return heroiTabelaDto;
    }

    public List<HeroiTabelaDto> findByStatus() {
        List<Heroi> heroiList = heroiRepository.findByStatus(true);
        List<HeroiTabelaDto> heroiTabelaDto = new ArrayList<>();
        heroiList.forEach(a -> {
            heroiTabelaDto.add(new HeroiTabelaDto(a));
        });
        return heroiTabelaDto;
    }

    public Optional<Heroi> findById(Long id){
        return heroiRepository.findById(id);
    }

    public Heroi insert(HeroiForm form, Long id) {
        Optional<UsuarioAdmin> usuarioAdmin = usuarioAdminService.findById(id);
        form.setUsuarioAdmin(usuarioAdmin.get());
        Heroi heroi = form.converterHeroi();
        return heroiRepository.save(heroi);
    }

    public Heroi update(HeroiForm heroiNew, Heroi heroi){
        heroi.setNome(heroiNew.getNome());
        heroi.setPoder(heroiNew.getPoder());
        heroi.setUniverso(heroiNew.getUniverso());
        heroi.setStatus(heroiNew.getStatus());
        return heroiRepository.save(heroi);
    }


    public void desative(Heroi heroi) {
        heroi.setStatus(false);
        heroiRepository.save(heroi);
    }
}
