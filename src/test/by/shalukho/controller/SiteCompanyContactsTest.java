package by.shalukho.controller;

import by.shalukho.dto.SiteCompanyContactsDto;
import org.junit.Test;

public class SiteCompanyContactsTest extends AbstractControllerTest {

    public static final String API_SITE_CONTACT_WITHOUT_ID = "/site/contact";

    @Test
    public void checkCustomerCreation() {
        SiteCompanyContactsDto contactsDto = createContactsDto();

        checkEntityCreation(API_SITE_CONTACT_WITHOUT_ID,
                            SiteCompanyContactsController.SITE_COMPANY_CONTACTS_DTO_ATTRIBUTE, contactsDto);
    }

    private SiteCompanyContactsDto createContactsDto() {
        SiteCompanyContactsDto dto = new SiteCompanyContactsDto();
        dto.setId(ID);
        dto.setBuildingType("Tower");
        dto.setCity("Minsk");
        dto.setEmail("print@twinspsprint.by");
        dto.setHouse("12");
        dto.setPhone("+3751111235");
        dto.setRoom("1");
        dto.setStreet("Nezalezhnasti");
        dto.setUndergroundStation("Urucca");
        dto.setWorkTime("mon-fri");
        return dto;
    }

}
