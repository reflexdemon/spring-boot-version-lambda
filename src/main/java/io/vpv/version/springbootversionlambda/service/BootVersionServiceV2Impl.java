package io.vpv.version.springbootversionlambda.service;

import io.vpv.version.springbootversionlambda.modal.DependencyDetails;
import io.vpv.version.springbootversionlambda.modal.RepoArtifact;
import io.vpv.version.springbootversionlambda.modal.VersionInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
public class BootVersionServiceV2Impl implements BootVersionService {

    RestTemplate restTemplate;

    @Value("${io.vpv.version.api.endpoint.milestonelist}")
    private String milestonelist;
    @Value("${io.vpv.version.api.endpoint.snapshotlist}")
    private String snapshotlist;

    public BootVersionServiceV2Impl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * @return
     */
    @Override
    public List<String> getVersionList() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public List<String> getMileStoneVersionList() {
        RepoArtifact repoArtifactFromURL = getRepoArtifactFromURL(milestonelist);
        return repoArtifactFromURL.getChildren().stream()
                .map(c -> c.getName())
                .collect(Collectors.toList());
    }

    public RepoArtifact getRepoArtifactFromURL(String url) {
        RepoArtifact repo = restTemplate.getForObject(url, RepoArtifact.class);
        return repo;
    }

    /**
     * @return
     */
    @Override
    public List<String> getSnapshotVersionList() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public VersionInfo getAllVersionInfo() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public List<String> getDocumentedVersionList() {
        return null;
    }

    /**
     * @param bootVersion
     * @return
     */
    @Override
    public DependencyDetails getDependencies(String bootVersion) {
        return null;
    }
}
