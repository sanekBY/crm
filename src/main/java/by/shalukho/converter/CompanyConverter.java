package by.shalukho.converter;

import by.shalukho.dbo.PersonEntity;
import by.shalukho.dto.PersonDto;
import org.springframework.stereotype.Service;

@Service
public class CompanyConverter extends GenericConverter<PersonDto, PersonEntity> {

}
