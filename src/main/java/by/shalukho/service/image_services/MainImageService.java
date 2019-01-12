package by.shalukho.service.image_services;

import by.shalukho.converter.ImageConverter;
import by.shalukho.property.StorageProperty;
import by.shalukho.repository.ImageRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class MainImageService extends AbstractImageService {

    public MainImageService(@NonNull final ImageRepository imageRepository,
                            @NonNull final ImageConverter imageConverter,
                            @NonNull final StorageProperty storageProperty) {
        super(imageRepository, imageConverter, storageProperty);
    }

    @Override
    protected String getLocation() {
        return getStorageProperty().getMainImageLocation();
    }
}
