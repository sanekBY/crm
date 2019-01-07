package by.shalukho.converter;

import by.shalukho.dto.ImageDto;
import by.shalukho.entity.ImageEntity;
import org.springframework.stereotype.Service;

@Service
public class ImageConverter extends GenericConverterWithEnums<ImageDto, ImageEntity> {

    public ImageConverter() {
        super(ImageEntity.class);
    }

}
