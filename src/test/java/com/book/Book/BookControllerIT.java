package com.book.Book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerIT {
    @Autowired
    MockMvc mockMvc;

    @Test
    void listBooks_ShouldReturnBooksView_EmptyList() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(view().name("books"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attributeExists("newBook"))
                .andExpect(model().attribute("books", hasSize(0)));
    }

    @Test
    void addBook_ShouldRenderBookView_NotEmptyList() throws Exception {
        mockMvc.perform(post("/books")
                        .param("title", "Clean Code")
                        .param("author", "Robert Martin"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(view().name("books"))
                .andExpect(model().attribute("books", hasSize(1)))
                .andExpect(model().attribute("books", hasItem(
                        allOf(
                                hasProperty("title", is("Clean Code")),
                                hasProperty("author", is("Robert Martin"))
                        )
                )));
    }
}
