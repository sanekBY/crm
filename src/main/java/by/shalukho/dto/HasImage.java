package by.shalukho.dto;

import lombok.NonNull;

public interface HasImage {
    ImageDto getImage();

    void setImage(@NonNull final ImageDto image);
}
