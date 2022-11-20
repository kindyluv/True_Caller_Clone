package com.precious.truecaller.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.precious.truecaller.data.repositories.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DataJdbcTest
@Sql(scripts = "/db/insert.sql")
@AutoConfigureMockMvc
class ContactControllerTest {

   private final MockMvc mvc;
   private final ContactRepository contactRepository;

   @Autowired
   ContactControllerTest(MockMvc mvc, ContactRepository contactRepository) {
       this.mvc = mvc;
       this.contactRepository = contactRepository;
   }

   ObjectMapper objectMapper;

   @BeforeEach
   void setUp() {
       objectMapper = new ObjectMapper();
   }

   @Test
   @DisplayName("Add new contact to contact list")
   void addContact() throws Exception {
       MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("")
               .param("name", "Lois Mama")
               .param("companyName", "Semicolon")
               .param("email", "loismama@gmail.com")
               .param("mobileNumber", "");
//               .param();
       mvc.perform(request
               .contentType("application.json"))
               .andExpect(status().is(200))
               .andDo(print());
   }

   @Test
   void findByMobileNumber() {
   }

   @Test
   void findByContactName() {
   }

   @Test
   void findAllContact() {
   }

   @Test
   void blockContactByMobileNumber() {
   }

   @Test
   void editContact() {
   }

   @Test
   void unBlockContactByMobileNumber() {
   }

   @Test
   void deleteContactByContactName() {
   }

   @Test
   void deleteContactById() {
   }

   @Test
   void deleteAllContact() {
   }
}
