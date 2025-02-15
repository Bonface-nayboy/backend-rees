package com.springboot.springbootproject.miniApp;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("mainBranch")
public class MainBranch {
    @Id

    private String id;
    private String businessName;
    private String businessType;
    private String county;
    private String referralCode;
    private String termsAccepted;

    public MainBranch() {

    }

    public MainBranch(String id,
            String businessName,
            String businessType,
            String county,
            String referralCode,
            String termsAccepted) {
        this.id = id;
        this.businessName = businessName;
        this.businessType = businessType;
        this.county = county;
        this.referralCode = referralCode;
        this.termsAccepted = termsAccepted;
    }

    public String getId() {
        return id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public String getTermsAccepted() {
        return termsAccepted;
    }

    public void setTermsAccepted(String termsAccepted) {
        this.termsAccepted = termsAccepted;
    }

    @Override
    public String toString() {
        return "MainBranch{" +
                "id='" + id + '\'' +
                ", businessName='" + businessName + '\'' +
                ", businessType='" + businessType + '\'' +
                ", county='" + county + '\'' +
                ", referralCode='" + referralCode + '\'' +
                ", termsAccepted='" + termsAccepted + '\'' +
                '}';
    }
}
