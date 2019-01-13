package by.shalukho.service.image_services;

import by.shalukho.converter.ImageConverter;
import by.shalukho.dto.ImageDto;
import by.shalukho.entity.ImageEntity;
import by.shalukho.entity.ImageTypeEnum;
import by.shalukho.property.StorageProperty;
import by.shalukho.repository.ImageRepository;
import by.shalukho.service.AbstractService;
import by.shalukho.specification.SpecificationFilter;
import lombok.NonNull;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public abstract class AbstractImageService extends AbstractService<ImageDto, ImageEntity, ImageRepository> {

    private final StorageProperty storageProperty;

    public AbstractImageService(@NonNull final ImageRepository imageRepository,
                                @NonNull final ImageConverter imageConverter,
                                @NonNull final StorageProperty storageProperty) {
        super(imageRepository, imageConverter);
        this.storageProperty = storageProperty;
    }

    protected Path getLocationPath() {
        return Paths.get(getLocation());
    }

    protected abstract String getLocation();

    public List<ImageDto> findAllByActiveIsTrue(@NonNull final Pageable pageable,
                                                @NonNull final ImageTypeEnum imageType) {
        final List<ImageEntity> content =
                getRepository().findAllByActiveIsTrueAndType(pageable, imageType).getContent();
        final List<ImageDto> images = getConverter().convertAllToDto(content);
        return images;
    }

    public Optional<ImageDto> store(@NonNull final MultipartFile file, @NonNull final ImageTypeEnum type) {
        final String originalFilename = file.getOriginalFilename();
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file " + originalFilename);
            }
            init();
            final Path path = this.getLocationPath().resolve(originalFilename);
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setName(originalFilename);

            final Optional<ImageEntity> existedEntity =
                    getRepository().findOne(SpecificationFilter.equal(ImageEntity::getName, originalFilename));
            if (existedEntity.isPresent()) {
                imageEntity = existedEntity.get();
                imageEntity.setActive(true);
            }
            imageEntity.setPath(path.toString());
            imageEntity.setType(type);

            ImageDto dto = getConverter().convertToDto(imageEntity);

            save(dto);
            Files.deleteIfExists(path);
            Files.copy(file.getInputStream(), path);

            return Optional.of(dto);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + originalFilename, e);
        }
    }

    public Resource loadAsResource(@NonNull final String filename) {
        try {
            final Path file = load(filename);
            final Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Could not read file: " + filename, e);
        }
    }

    public Path load(@NonNull final String filename) {
        return this.getLocationPath().resolve(filename);
    }

    public void init() {
        try {
            if (Files.notExists(this.getLocationPath())) {
                Files.createDirectory(this.getLocationPath());
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage", e);
        }
    }

    public StorageProperty getStorageProperty() {
        return storageProperty;
    }
}
