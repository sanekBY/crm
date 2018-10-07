package by.shalukho.sevice;

import by.shalukho.converter.ItemConverter;
import by.shalukho.converter.ItemTypeConverter;
import by.shalukho.dto.ItemDto;
import by.shalukho.entity.ItemEntity;
import by.shalukho.entity.ItemTypeEntity;
import by.shalukho.repository.ItemRepository;
import by.shalukho.service.ItemService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {

    @InjectMocks
    private ItemService itemService;

    @Mock
    private ItemRepository itemRepository;

    @Spy
    @InjectMocks
    private ItemConverter itemConverter = new ItemConverter();

    @Spy
    private ItemTypeConverter itemTypeConverter;

    private static final Long ITEM_ID = 1L;
    public static final String ITEM_NAME = "Item";

    private static final Long ITEM_TYPE_ID = 2L;
    public static final String ITEM_TYPE_NAME = "Item type";

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getItemTest() {
        final ItemEntity itemEntity = getItemEntity();
        final ItemTypeEntity itemTypeEntity = getItemTypeEntity();

        itemEntity.setItemType(itemTypeEntity);

        Mockito.when(itemRepository.findByActiveIsTrueAndId(ITEM_ID)).thenReturn(Optional.of(itemEntity));

        final ItemDto itemDto = itemService.findById(ITEM_ID);

        Assert.assertEquals(ITEM_ID, itemDto.getId());
        Assert.assertEquals(ITEM_NAME, itemDto.getName());
        Assert.assertEquals(ITEM_TYPE_ID, itemDto.getItemType().getId());
        Assert.assertEquals(ITEM_TYPE_NAME, itemDto.getItemType().getName());
    }

    @Test
    public void getAllItemsTest() {
        Mockito.when(itemRepository.findAllByActiveIsTrue())
                .thenReturn(Arrays.asList(new ItemEntity(), new ItemEntity()));
        final List<ItemDto> items = itemService.findAll();
    }

    @Test
    public void saveItem() {
        itemService.save(new ItemDto());
        Mockito.verify(itemRepository, Mockito.times(1)).save(Mockito.any());
    }

    private ItemTypeEntity getItemTypeEntity() {
        final ItemTypeEntity itemTypeEntity = new ItemTypeEntity();
        itemTypeEntity.setId(ITEM_TYPE_ID);
        itemTypeEntity.setName(ITEM_TYPE_NAME);
        return itemTypeEntity;
    }

    private ItemEntity getItemEntity() {
        final ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(ITEM_ID);
        itemEntity.setActive(true);
        itemEntity.setName(ITEM_NAME);
        return itemEntity;
    }
}
