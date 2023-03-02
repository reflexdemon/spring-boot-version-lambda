package io.vpv.version.springbootversionlambda.service;

import io.vpv.version.springbootversionlambda.modal.DependencyDetails;
import io.vpv.version.springbootversionlambda.modal.VersionInfo;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public interface BootVersionService {
    @Cacheable("versionlist")
    List<String> getVersionList();

    @Cacheable("milestonelist")
    List<String> getMileStoneVersionList();

    @Cacheable("snapshotlist")
    List<String> getSnapshotVersionList();

    @Cacheable("versioninfo")
    VersionInfo getAllVersionInfo();

    @Cacheable("docVersions")
    List<String> getDocumentedVersionList();
    @Cacheable(value = "dependency", key = "#bootVersion")
    DependencyDetails getDependencies(String bootVersion);
}
