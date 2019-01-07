package by.shalukho.entity;


import by.shalukho.dto.ConnectedDto;
import by.shalukho.dto.ImageDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "IMAGE")
@EqualsAndHashCode(callSuper = true)
@ConnectedDto(value = ImageDto.class)
public class ImageEntity extends AbstractNamedEntity {

    @Column(name = "PATH")
    private String path;

}
