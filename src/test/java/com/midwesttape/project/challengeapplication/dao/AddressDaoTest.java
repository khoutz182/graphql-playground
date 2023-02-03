package com.midwesttape.project.challengeapplication.dao;

import com.midwesttape.project.challengeapplication.model.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//@DataJpaTest // If this were a jpa repository the test could be much lighter
@SpringBootTest
class AddressDaoTest {

    @Autowired
    private AddressDao addressDao;

    @Test
    public void getAddress() {
        Address address = addressDao.getAddress(1L);
        assertNotNull(address);
        assertEquals("1060 W Addison St", address.getAddress1());
    }
}
