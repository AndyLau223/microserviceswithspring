package com.optimagrowth.license.service;

import com.optimagrowth.license.config.ServiceConfig;
import com.optimagrowth.license.model.License;
import com.optimagrowth.license.model.Organization;
import com.optimagrowth.license.service.client.OrganizationDiscoveryClient;
import com.optimagrowth.license.service.client.OrganizationFeignClient;
import com.optimagrowth.license.service.client.OrganizationRestTemplateClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Locale;
import java.util.Random;

@Service
public class LicenseService {
    @Autowired
    MessageSource messageSource;

    @Autowired
    ServiceConfig config;

    @Resource
    OrganizationFeignClient organizationFeignClient;

    @Autowired
    OrganizationRestTemplateClient organizationRestClient;

    @Autowired
    OrganizationDiscoveryClient organizationDiscoveryClient;

    public License getLicense(String licenseId, String organizationId, String clientType) {
// just for illustrative purposes, in real project we should get from database
        return getLicense(licenseId, organizationId);
    }


    public License getLicense(String licenseId, String organizationId) {
        License license = new License();
        license.setId(new Random().nextInt(1000));
        license.setLicenseId(licenseId);
        license.setOrganizationId(organizationId);
        license.setDescription("Software product");
        license.setProductName("Ostock");
        license.setLicenseType("full");

        return license;
    }

    public String createLicense(final License license,
                                final @NonNull String organizationId,
                                Locale locale) {
        String res = null;
        if (license != null) {
            license.setOrganizationId(organizationId);
            res = String.format(
                    messageSource.getMessage("license.create.message", null, locale), license.toString());
        }
        return res;
    }

    public String updateLicense(final License license,
                                String organizationId
    ) {
        String res = null;
        if (license != null) {
            license.setOrganizationId(organizationId);
            res = String.format(messageSource.getMessage("license.update.message", null, null), license.toString());
        }
        return res;
    }

    public String deleteLicense(final String licenseId, String organizationId) {
        String res = null;
        res = String.format("Deleting license with id %s for the organization %s", licenseId, organizationId);
        return res;
    }

    private Organization retrieveOrganizationInfo(String organizationId, String clientType) {
        Organization organization = null;

        switch (clientType) {
            case "feign":
                System.out.println("I am using the feign client");
                organization = organizationFeignClient.getOrganization(organizationId);
                break;
            case "rest":
                System.out.println("I am using the rest client");
                organization = organizationRestClient.getOrganization(organizationId);
                break;
            case "discovery":
                System.out.println("I am using the discovery client");
                organization = organizationDiscoveryClient.getOrganization(organizationId);
                break;
            default:
                organization = organizationRestClient.getOrganization(organizationId);
                break;
        }

        return organization;
    }
}
