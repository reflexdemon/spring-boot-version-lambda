package io.vpv.version.springbootversionlambda.service;

import io.vpv.version.springbootversionlambda.SpringBootVersionLambdaApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
@RunWith(SpringRunner.class)
@SpringBootTest (classes = SpringBootVersionLambdaApplication.class)
class BootVersionServiceV2ImplTest {

    @Autowired
    BootVersionService bootVersionService;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getVersionList() {
    }

    @Test
    void getMileStoneVersionList() {
        List<String> mileStoneVersionList = bootVersionService.getMileStoneVersionList();
        assertNotNull(mileStoneVersionList, "Should have something returned");
        assertFalse(mileStoneVersionList.isEmpty(), "Should have something returned");
    }

    @Test
    void getSnapshotVersionList() {
    }

    @Test
    void getAllVersionInfo() {
    }

    @Test
    void getDocumentedVersionList() {
    }

    @Test
    void getDependencies() {
    }
}