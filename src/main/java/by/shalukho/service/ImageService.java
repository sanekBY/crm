package by.shalukho.service;

import by.shalukho.converter.ImageConverter;
import by.shalukho.dto.ImageDto;
import by.shalukho.entity.ImageEntity;
import by.shalukho.entity.ImageTypeEnum;
import by.shalukho.property.StorageProperty;
import by.shalukho.repository.ImageRepository;
import lombok.NonNull;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService extends AbstractService<ImageDto, ImageEntity, ImageRepository> {
    private final Path rootLocation;


    public ImageService(ImageRepository imageRepository,
                        ImageConverter imageConverter,
                        StorageProperty storageProperty) {
        super(imageRepository, imageConverter);
        this.rootLocation = Paths.get(storageProperty.getLocation());
    }

    @Override
    public Optional<ImageEntity> findByActiveIsTrueAndId(final Long id) {
        return getRepository().findByActiveIsTrueAndId(id);
    }

    @Override
    public List<ImageEntity> findAllByActiveIsTrue() {
        return getRepository().findAllByActiveIsTrue();
    }

    public List<ImageDto> findAllByActiveIsTrue(@NonNull final Pageable pageable,
                                                @NonNull final ImageTypeEnum imageType) {
        final List<ImageEntity> content =
                getRepository().findAllByActiveIsTrueAndType(pageable, imageType).getContent();
        final List<ImageDto> images = getConverter().convertAllToDto(content);
        return images;
    }

    @Override
    public Page<ImageEntity> findAllByActiveIsTrue(final Pageable pageable) {
        return getRepository().findAllByActiveIsTrue(pageable);
    }

    public void store(@NonNull final MultipartFile file, @NonNull final ImageTypeEnum type) {
        final String originalFilename = file.getOriginalFilename();
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file " + originalFilename);
            }
            init();
            final Path path = rootLocation.resolve(originalFilename);
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setName(originalFilename);

            final Optional<ImageEntity> existedEntity =
                    getRepository().findByName(originalFilename);
            if (existedEntity.isPresent()) {
                imageEntity = existedEntity.get();
                imageEntity.setActive(true);
            }
            imageEntity.setPath(path.toString());
            imageEntity.setType(type);

            save(getConverter().convertToDto(imageEntity));

            Files.deleteIfExists(path);
            Files.copy(file.getInputStream(), path);
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
        return rootLocation.resolve(filename);
    }

    public void init() {
        try {
            if (Files.notExists(rootLocation)) {
                Files.createDirectory(rootLocation);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage", e);
        }
    }
}
