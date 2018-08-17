package by.shalukho.service;

import by.shalukho.converter.PersonConverter;
import by.shalukho.dbo.PersonEntity;
import by.shalukho.dto.PersonDto;
import by.shalukho.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService extends AbstractService<PersonDto, PersonEntity> {

    public PersonService(PersonRepository personRepository, PersonConverter personConverter) {
        super(personRepository, personConverter, PersonDto.class, PersonEntity.class);
    }

}
