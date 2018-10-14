package by.shalukho.converter;

import by.shalukho.dto.SiteStockDto;
import by.shalukho.entity.SiteStockEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class SiteStockConverter extends GenericConverter<SiteStockDto, SiteStockEntity> {

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public SiteStockConverter() {
        super(SiteStockEntity.class);
    }


    @Override
    protected SiteStockEntity extraConvertToEntity(final SiteStockDto siteStockDto,
                                                   final SiteStockEntity siteStockEntity) {
        final SiteStockEntity stockEntity = super.extraConvertToEntity(siteStockDto, siteStockEntity);
        stockEntity.setDateFrom(parseDate(siteStockDto.getDateFrom()));
        stockEntity.setDateTo(parseDate(siteStockDto.getDateTo()));
        return stockEntity;
    }

    @Override
    protected SiteStockDto extraConvertToDto(final SiteStockEntity siteStockEntity,
                                             final SiteStockDto siteStockDto) {
        final SiteStockDto stockDto = super.extraConvertToDto(siteStockEntity, siteStockDto);
        stockDto.setDateFrom(getDate(siteStockEntity.getDateFrom()));
        stockDto.setDateTo(getDate(siteStockEntity.getDateTo()));
        return stockDto;
    }

    private LocalDate parseDate(final String date) {
        return LocalDate.parse(date, getFormatter());
    }

    private String getDate(final LocalDate date) {
        return getFormatter().format(date);
    }

    private DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern(DATE_FORMAT);
    }
}
