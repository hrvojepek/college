package tvz.naprednaJava.rozi.AutoServis.Controller;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tvz.naprednaJava.rozi.AutoServis.controller.ItemController;
import tvz.naprednaJava.rozi.AutoServis.model.Item;
import tvz.naprednaJava.rozi.AutoServis.model.Manufacturer;
import tvz.naprednaJava.rozi.AutoServis.service.ItemService;
import tvz.naprednaJava.rozi.AutoServis.service.ManufacturerService;

import java.util.Arrays;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ItemController itemController;

    @Mock
    private ItemService itemServiceMock;

    @Mock
    private ManufacturerService manufacturerServiceMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();
    }

    @Test
    public void testIndex() throws Exception {

        Item item = new Item();
        when(itemServiceMock.getAllActive()).thenReturn(Arrays.asList(item,item));

        this.mockMvc
                .perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("items"))
                .andExpect(model().attribute("items", Matchers.hasSize(2)))
                .andExpect(view().name("items/index"));
    }

    @Test
    public void testPrivateProductTable() throws Exception {

        Item item = new Item();
        when(itemServiceMock.getAllActive()).thenReturn(Arrays.asList(item,item));

        this.mockMvc
                .perform(get("/private/items"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("items"))
                .andExpect(model().attribute("items", Matchers.hasSize(2)))
                .andExpect(view().name("authorized_pages/items/index"));
    }
    @Test
    public void testPrivateProductTableOfManufacturer() throws Exception {

        Item item = new Item();
        when(itemServiceMock.getAllActiveByManufacturer(any(Manufacturer.class))).thenReturn(Arrays.asList(item,item));

        this.mockMvc
                .perform(get("/private/items/manufacturer/{manufacturer}",1))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("items"))
                .andExpect(model().attribute("items", Matchers.hasSize(2)))
                .andExpect(view().name("authorized_pages/items/index"));
    }

    @Test
    public void testAdd() throws Exception {

        Manufacturer manufacturer = new Manufacturer();
        when(manufacturerServiceMock.getAllActive()).thenReturn(Arrays.asList(manufacturer,manufacturer));

        this.mockMvc
                .perform(get("/private/item/new"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("authorized_pages/items/add-item"))
                .andExpect(model().attributeExists("manufacturers"))
                .andExpect(model().attribute("manufacturers", Matchers.hasSize(2)))
                .andExpect(view().name("authorized_pages/items/add-item"));
    }

//    @Test
//    public void testView() throws Exception{
//
//        this.mockMvc
//                .perform(get("/private/item/view/{item}")
//                        .param("name", "test")
//                        .param("pricePerUnit", "1")
//
//                )
//                .andExpect(status().isOk())
//                .andExpect(forwardedUrl("authorized_pages/items/view-item"))
//                .andExpect(model().attributeExists("item"))
//                .andExpect(view().name("authorized_pages/items/view-item"));
//    }
}