package com.optimagrowth.license.service;

import com.optimagrowth.license.model.License;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class LicenseService {
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
                                final @NonNull String organizationId) {
        String res = null;
        if (license != null) {
            license.setOrganizationId(organizationId);
            res = String.format("This is the post and the object is: %s", license.toString());
        }
        return res;
    }

    public String updateLicense(final License license, String organizationId) {
        String res = null;
        if (license != null) {
            license.setOrganizationId(organizationId);
            res = String.format("This is the put and the object is: %s", license.toString());
        }
        return res;
    }

    public String deleteLicense(final String licenseId, String organizationId) {
        String res = null;
        res = String.format("Deleting license with id %s for the organization %s", licenseId, organizationId);
        return res;
    }


}
