package io.vpv.version.springbootversionlambda.service;

import io.vpv.version.springbootversionlambda.modal.Child;
import io.vpv.version.springbootversionlambda.modal.RepoArtifact;
import io.vpv.version.springbootversionlambda.util.DocumentParserUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
public class BootVersionServiceV2Impl implements BootVersionService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    RestTemplate restTemplate;
    DocumentParserUtility documentParserUtility;

    public BootVersionServiceV2Impl(RestTemplate restTemplate, DocumentParserUtility documentParserUtility) {
        this.restTemplate = restTemplate;
        this.documentParserUtility = documentParserUtility;
    }

    @Value("${io.vpv.version.api.endpoint.milestonelist}")
    private String milestonelist;
    @Value("${io.vpv.version.api.endpoint.snapshotlist}")
    private String snapshotlist;

    @Value("${io.vpv.version.api.endpoint.versionlist}")
    private String versionlist;


    @Value("${io.vpv.version.endpoint.docVersions}")
    private String docVersions;
    @Value("${io.vpv.version.endpoint.dependency.base}")
    private String basePath;
    @Value("${io.vpv.version.endpoint.dependency.dependencyPage}")
    private String dependencyPage;
    @Value("${io.vpv.version.endpoint.dependency.dependencyPageNew}")
    private String dependencyPageNew;
    public String getDocVersions() {
        return docVersions;
    }
    /**
     * @return
     */
    @Override
    public List<String> getVersionList() {
        List<String> repoArtifactFromURL = getRepoArtifactFromURL(versionlist);
        return repoArtifactFromURL;
    }

    /**
     * @return
     */
    @Override
    public List<String> getMileStoneVersionList() {
        List<String> repoArtifactFromURL = getRepoArtifactFromURL(milestonelist);
        return repoArtifactFromURL;
    }

    public List<String> getRepoArtifactFromURL(String url) {
        RepoArtifact repo = restTemplate.getForObject(url, RepoArtifact.class);
        return repo.getChildren().stream()
                .filter(child -> child.getFolder())
                .sorted(Comparator.comparing(Child::getLastModified))
                .map(c -> c.getName())
                .collect(Collectors.toList());
    }

    /**
     * @return
     */
    @Override
    public List<String> getSnapshotVersionList() {
        List<String> repoArtifactFromURL = getRepoArtifactFromURL(snapshotlist);
        return repoArtifactFromURL;
    }


    /**
     * @return
     */
    @Override
    public DocumentParserUtility getDocumentParserUtility() {
        return documentParserUtility;
    }
    /**
     * @return
     */
    @Override
    public String getDependencyPageNew() {
        return dependencyPageNew;
    }

    /**
     * @return
     */
    @Override
    public String getDependencyPage() {
        return dependencyPage;
    }

    /**
     * @return
     */
    @Override
    public String getBasePath() {
        return basePath;
    }
}
