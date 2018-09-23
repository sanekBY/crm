package by.shalukho.entity;

import by.shalukho.dto.ConnectedDto;
import by.shalukho.dto.SiteCompanyContactsDto;
import by.shalukho.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "SITE_COMPANY_CONTACTS")
@EqualsAndHashCode(callSuper = true)
@ConnectedDto(value = SiteCompanyContactsDto.class)
public class SiteCompanyContactsEntity extends AbstractEntity {

    @Column(name = "CITY")
    private String city;

    @Column(name = "STREET")
    private String street;

    @Column(name = "HOUSE")
    private String house;

    @Column(name = "BUILDING_TYPE")
    private String buildingType;

    @Column(name = "UNDERGROUND_STATION")
    private String undergroundStation;

    @Column(name = "ROOM")
    private String room;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "WORK_TIME")
    private String workTime;

}
