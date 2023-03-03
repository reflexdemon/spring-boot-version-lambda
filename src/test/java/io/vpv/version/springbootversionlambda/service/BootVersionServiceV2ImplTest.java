package io.vpv.version.springbootversionlambda.service;

import io.vpv.version.springbootversionlambda.SpringBootVersionLambdaApplication;
import io.vpv.version.springbootversionlambda.modal.DependencyDetails;
import io.vpv.version.springbootversionlambda.modal.VersionInfo;
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
        List<String> versionList = bootVersionService.getVersionList();
        assertNotNull(versionList, "Should have something returned");
        assertFalse(versionList.isEmpty(), "Should have something returned");
    }

    @Test
    void getMileStoneVersionList() {
        List<String> mileStoneVersionList = bootVersionService.getMileStoneVersionList();
        assertNotNull(mileStoneVersionList, "Should have something returned");
        assertFalse(mileStoneVersionList.isEmpty(), "Should have something returned");
    }

    @Test
    void getSnapshotVersionList() {
        List<String> snapshotVersionList = bootVersionService.getSnapshotVersionList();
        assertNotNull(snapshotVersionList, "Should have something returned");
        assertFalse(snapshotVersionList.isEmpty(), "Should have something returned");
    }

    @Test
    void getAllVersionInfo() {
        VersionInfo versionList = bootVersionService.getAllVersionInfo();
        assertNotNull(versionList, "Should have something returned");
        assertFalse(versionList.getMilestones().isEmpty(), "Should have something returned");
        assertFalse(versionList.getSnapshots().isEmpty(), "Should have something returned");
    }

    @Test
    void getDocumentedVersionList() {
        List<String> documentedVersionList = bootVersionService.getDocumentedVersionList();
        assertNotNull(documentedVersionList, "Should have something returned");
        assertFalse(documentedVersionList.isEmpty(), "Should have something returned");
    }

    @Test
    void getDependencies() {
        DependencyDetails dependencies = bootVersionService.getDependencies(bootVersionService.getDocumentedVersionList().get(0));
        assertNotNull(dependencies, "Should have something returned");
        assertNotNull(dependencies.getDependencies(), "Should have something returned");
        assertNotNull(dependencies.getBootVersion(), "Should have something returned");
        assertNotNull(dependencies.getSource(), "Should have something returned");
        assertFalse(dependencies.getDependencies().isEmpty(), "Should have something returned");
    }
}