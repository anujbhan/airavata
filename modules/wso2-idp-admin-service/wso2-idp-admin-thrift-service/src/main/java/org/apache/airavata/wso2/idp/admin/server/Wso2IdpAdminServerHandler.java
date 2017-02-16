/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
*/

package org.apache.airavata.wso2.idp.admin.server;

import org.apache.airavata.common.exception.ApplicationSettingsException;
import org.apache.airavata.common.utils.ApplicationSettings;
import org.apache.airavata.model.wso2.adminservice.TenantInfoBean;
import org.apache.airavata.wso2.idp.admin.core.soapclient.Wso2IdpServerSoapClient;
import org.apache.airavata.wso2.idp.admin.core.tenantmgmt.AddTenant;
import org.apache.airavata.wso2.idp.admin.cpi.Wso2IdpAdminService;
import org.apache.airavata.wso2.idp.admin.cpi.exceptions.Wso2IdpAdminServiceException;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.soap.SOAPMessage;

public class Wso2IdpAdminServerHandler implements Wso2IdpAdminService.Iface {
    private final static Logger logger = LoggerFactory.getLogger(Wso2IdpAdminServerHandler.class);


    @Override
    public boolean addTenant(TenantInfoBean tenantInfoBean, String superAdminPWDCredentialAccessToken, String gatewayID) throws Wso2IdpAdminServiceException, TException {
        try{
            if(tenantInfoBean.isSetActive() && tenantInfoBean.isSetFirstName() && tenantInfoBean.isSetLastName() &&
                    tenantInfoBean.isSetEmail() && tenantInfoBean.isSetTenantDomain() && tenantInfoBean.isSetTenantId()){
                SOAPMessage addTenantRequest = AddTenant.getSOAPRequestMessage(tenantInfoBean,gatewayID,superAdminPWDCredentialAccessToken);
                String url = ApplicationSettings.getWso2IdpAdminTenantMgmtSOAPUrl();
                SOAPMessage addTenantResponse = Wso2IdpServerSoapClient.sendSOAPRequest(addTenantRequest,url);
                return AddTenant.isSOAPRequestSuccessful(addTenantResponse);
            }
        } catch (ApplicationSettingsException ex){
            logger.error("Error while fetching SOAP URl for wso2 admin tenant Mgmt API", ex);
            Wso2IdpAdminServiceException exception = new Wso2IdpAdminServiceException();
            exception.setMessage("Error while fetching SOAP URl for wso2 admin tenant Mgmt API. More info : " + ex.getMessage());
            throw exception;
        }
    }
}
