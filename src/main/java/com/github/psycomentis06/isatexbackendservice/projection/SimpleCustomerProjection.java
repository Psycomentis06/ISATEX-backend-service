package com.github.psycomentis06.isatexbackendservice.projection;

public interface SimpleCustomerProjection extends SimpleUserProjection {
    String getCity();
    String getAddress();
    String getCountry();
    String getEnterpriseName();
    String getEnterpriseAddress();
    String getEnterpriseTelephone();
}