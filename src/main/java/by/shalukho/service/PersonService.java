package by.shalukho.service;

import by.shalukho.converter.PersonConverter;
import by.shalukho.dto.PersonDto;
import by.shalukho.entity.PersonEntity;
import by.shalukho.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService extends AbstractService<PersonDto, PersonEntity> {

    public PersonService(PersonRepository personRepository, PersonConverter personConverter) {
        super(personRepository, personConverter, PersonDto.class, PersonEntity.class);
    }

    @Override
    public PersonEntity findByActiveAndId(boolean active, Long id) {
        return ((PersonRepository) getRepository()).findByActiveAndId(active, id);
    }

    @Override
    public List<PersonEntity> findAllByActive(boolean active) {
        return ((PersonRepository) getRepository()).findAllByActive(active);
    }
}
