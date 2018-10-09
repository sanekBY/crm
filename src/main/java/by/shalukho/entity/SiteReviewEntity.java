package by.shalukho.entity;

import by.shalukho.dto.ConnectedDto;
import by.shalukho.dto.SiteReviewDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "SITE_REVIEW")
@EqualsAndHashCode(callSuper = true)
@ConnectedDto(value = SiteReviewDto.class)
public class SiteReviewEntity extends AbstractNamedEntity {

    @Column(name = "POSITION")
    private String position;

    @Column(name = "TEXT")
    private String text;

    @Column(name = "CREATED_ON", nullable = false)
    private LocalDateTime createdOn;

    @Column(name = "ACCEPTED")
    private boolean accepted;

}
