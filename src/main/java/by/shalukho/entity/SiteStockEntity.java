package by.shalukho.entity;

import by.shalukho.dto.ConnectedDto;
import by.shalukho.dto.SiteStockDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "SITE_STOCK")
@EqualsAndHashCode(callSuper = true)
@ConnectedDto(value = SiteStockDto.class)
public class SiteStockEntity extends AbstractNamedEntity {

    @Column(name = "TEXT", nullable = false)
    private String text;

    @Column(name = "DATE_FROM", nullable = false)
    private LocalDate dateFrom;

    @Column(name = "DATE_TO", nullable = false)
    private LocalDate dateTo;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "IMAGE_ID")
    @ToString.Exclude
    private ImageEntity imageEntity;

    @Transient
    private boolean opened;

    public boolean isOpened() {
        LocalDate now = LocalDate.now();
        return now.isAfter(dateFrom) && now.isBefore(dateTo);
    }

}
