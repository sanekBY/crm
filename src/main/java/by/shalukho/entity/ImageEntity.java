package by.shalukho.entity;


import by.shalukho.dto.ConnectedDto;
import by.shalukho.dto.ImageDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "IMAGE")
@EqualsAndHashCode(callSuper = true)
@ConnectedDto(value = ImageDto.class)
public class ImageEntity extends AbstractEntity {

    @Column(name = "NAME")
    private String name;

    @Column(name = "PATH")
    private String path;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private ImageTypeEnum type;

}
