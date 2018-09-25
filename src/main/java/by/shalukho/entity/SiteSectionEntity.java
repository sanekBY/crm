package by.shalukho.entity;

import by.shalukho.dto.ConnectedDto;
import by.shalukho.dto.SiteSectionDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "SITE_SECTION")
@EqualsAndHashCode(callSuper = true)
@ConnectedDto(value = SiteSectionDto.class)
public class SiteSectionEntity extends AbstractNamedEntity {

    @Column(name = "URL")
    private String url;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PARENT_SECTION_ID", nullable = false)
    @ToString.Exclude
    private SiteSectionEntity parentSection;

    @OneToMany(mappedBy = "parentSection", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<SiteSectionEntity> siteSections;

}
