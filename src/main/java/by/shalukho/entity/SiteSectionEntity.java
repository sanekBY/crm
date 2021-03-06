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
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ConnectedDto(value = SiteSectionDto.class)
@ToString(callSuper = true, doNotUseGetters = true)
public class SiteSectionEntity extends AbstractNamedEntity {

    @Column(name = "URL")
    private String url;

    @Column(name = "HEADER")
    private String header;

    @Column(name = "TEXT")
    private String text;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "PARENT_SECTION_ID")
    @ToString.Exclude
    private SiteSectionEntity parentSection;

    @OneToMany(mappedBy = "parentSection", fetch = FetchType.EAGER, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    @ToString.Exclude
    private List<SiteSectionEntity> siteSections;

}
