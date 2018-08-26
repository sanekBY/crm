package by.shalukho.service;

import by.shalukho.converter.customer.PersonConverter;
import by.shalukho.dto.customer.PersonDto;
import by.shalukho.entity.customer.PersonEntity;
import by.shalukho.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService extends AbstractService<PersonDto, PersonEntity> {

    public PersonService(PersonRepository personRepository, PersonConverter personConverter) {
        super(personRepository, personConverter, PersonDto.class, PersonEntity.class);
    }

    @Override
    public Optional<PersonEntity> findByActiveAndId(boolean active, Long id) {
        return ((PersonRepository) getRepository()).findByActiveAndId(active, id);
    }

    @Override
    public List<PersonEntity> findAllByActive(boolean active) {
        return ((PersonRepository) getRepository()).findAllByActive(active);
    }
}
