package io.vpv.version.springbootversionlambda.service;

import io.vpv.version.springbootversionlambda.modal.Dependency;
import io.vpv.version.springbootversionlambda.modal.DependencyDetails;
import io.vpv.version.springbootversionlambda.modal.VersionInfo;
import io.vpv.version.springbootversionlambda.util.DocumentParserUtility;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Collections.reverseOrder;
import static java.util.stream.Collectors.toList;

public interface BootVersionService {
    @Cacheable("versionlist")
    List<String> getVersionList();

    @Cacheable("milestonelist")
    List<String> getMileStoneVersionList();

    @Cacheable("snapshotlist")
    List<String> getSnapshotVersionList();

    DocumentParserUtility getDocumentParserUtility();
    String getDocVersions();

    @Cacheable("docVersions")
    default List<String> getDocumentedVersionList() {
        return getVersionsFromURL(getDocVersions())
                .stream()
                .sorted(reverseOrder(String::compareToIgnoreCase))
                .collect(Collectors.toList());

    }
    @Cacheable("versioninfo")
    default VersionInfo getAllVersionInfo() {
        return new VersionInfo(getMileStoneVersionList(), getSnapshotVersionList());
    }
    default List<String> getVersionsFromURL(String url) {
        return getDocumentParserUtility().getDocumentFromURL(url)
                .select("a").
                stream().
                filter(Objects::nonNull).
                map(Element::text).
                filter(text -> !text.contains("..")).
                filter(text -> !text.contains("maven")).
                filter(text -> (text.indexOf('.') > 0)).
                map(value -> value.substring(0, value.length() - 1)).
                collect(toList());
    }

    @Cacheable(value = "dependency", key = "#bootVersion")
    default DependencyDetails getDependencies(final String bootVersion) {
        DependencyDetails dependencyDetails = null;
        List<Dependency> dependencies = null;
        try {
            String url = getBasePath() + bootVersion;
            Document document = getDocumentParserUtility().getDocumentFromURL(url, getDependencyPage(), getDependencyPageNew());

            Elements allTables =
                    document.select("body > div.appendix > div.informaltable > table");
            if (allTables.size() == 0) {
                allTables =
                        document.select("div.sect1:nth-child(2) > div:nth-child(2) > table:nth-child(2)");
            }
            dependencies = allTables.select("tr").
                    stream().
                    map(element -> element.select("td").eachText()).
                    filter(Objects::nonNull).
                    filter(values -> values.size() >= 3).
                    map(value -> new Dependency(bootVersion, value.get(0), value.get(1), value.get(2))).
                    collect(toList());
            dependencyDetails = new DependencyDetails(dependencies, url, bootVersion);
        } catch (Exception  e) {
            throw new RuntimeException("Problem while getting the dependencies for " + bootVersion, e);
        }
        return dependencyDetails;
    }

    String getDependencyPageNew();

    String getDependencyPage();

    String getBasePath();
}
