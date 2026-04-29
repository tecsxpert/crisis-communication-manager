package com.internship.tool.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.internship.tool.entity.Crisis;
import com.internship.tool.service.CrisisService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CrisisController.class)
public class CrisisControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CrisisService service;

    @Autowired
    private ObjectMapper objectMapper;

    // ✅ TEST CREATE
    @Test
    void testCreateCrisis() throws Exception {
        Crisis crisis = new Crisis("Test", "Desc", "ongoing", "1st");

        when(service.create(Mockito.any())).thenReturn(crisis);

        mockMvc.perform(post("/crisis")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(crisis)))
                .andExpect(status().isCreated());
    }

    // ✅ TEST GET ALL
    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/crisis"))
                .andExpect(status().isOk());
    }

    // ✅ TEST GET BY ID
    @Test
    void testGetById() throws Exception {
        Crisis crisis = new Crisis("Test", "Desc", "ongoing", "1st");

        when(service.getById(1L)).thenReturn(crisis);

        mockMvc.perform(get("/crisis/1"))
                .andExpect(status().isOk());
    }

    // ✅ TEST UPDATE
    @Test
    void testUpdate() throws Exception {
        Crisis crisis = new Crisis("Updated", "Desc", "closed", "2nd");

        when(service.update(Mockito.eq(1L), Mockito.any())).thenReturn(crisis);

        mockMvc.perform(put("/crisis/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(crisis)))
                .andExpect(status().isOk());
    }

    // ✅ TEST DELETE
    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/crisis/1"))
                .andExpect(status().isOk());
    }

    // ✅ TEST SEARCH
    @Test
    void testSearch() throws Exception {
        when(service.search("test")).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/crisis/search?q=test"))
                .andExpect(status().isOk());
    }

    // ✅ TEST STATS
    @Test
    void testStats() throws Exception {
        mockMvc.perform(get("/crisis/stats"))
                .andExpect(status().isOk());
    }

    // ✅ TEST FILTER
    @Test
    void testFilter() throws Exception {
        mockMvc.perform(get("/crisis/filter?status=ongoing"))
                .andExpect(status().isOk());
    }
}