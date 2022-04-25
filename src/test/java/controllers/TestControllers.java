package controllers;
import com.example.triangle.controllers.MainController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MainController.class)

public class TestControllers {
    @Autowired
    private MockMvc mvc;

    @Test
    public void test() throws Exception{
        mvc.perform(get("/volume/first=3&second=4&third=5"))
                .andExpect(status().isOk())
                .andExpect(content().string("12"));
    }

    @Test
    public void testWithoutParam() throws Exception{
        mvc.perform(get("/?first=&second=4&third=5"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testPerimeterWithBadParams() throws Exception{
        mvc.perform(get("/?&first=BAD&second=4&third=5"))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void testSquare() throws Exception{
        mvc.perform(get("/volume/square?first=3&second=4&third=5"))
                .andExpect(status().isOk())
                .andExpect(content().string("6.0"));
    }
}