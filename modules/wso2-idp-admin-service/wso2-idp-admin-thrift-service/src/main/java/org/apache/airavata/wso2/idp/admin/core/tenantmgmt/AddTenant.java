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

package org.apache.airavata.wso2.idp.admin.core.tenantmgmt;

import org.apache.airavata.common.exception.ApplicationSettingsException;
import org.apache.airavata.credential.store.cpi.CredentialStoreService;
import org.apache.airavata.model.wso2.adminservice.TenantInfoBean;
import org.apache.airavata.wso2.idp.admin.core.util.CredentialStoreThriftClient;
import org.apache.airavata.wso2.idp.admin.cpi.exceptions.Wso2IdpAdminServiceException;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.airavata.model.credential.store.PasswordCredential;

import javax.xml.soap.*;

public class AddTenant {

    private final static Logger logger = LoggerFactory.getLogger(AddTenant.class);

    public static SOAPMessage getSOAPRequestMessage(TenantInfoBean tenantInfoBeanObj, String gatewayID, String superAdminToken) throws Wso2IdpAdminServiceException {

        try{
            CredentialStoreThriftClient clientFactory = new CredentialStoreThriftClient();
            CredentialStoreService.Client credentialStoreClient = clientFactory.getCredentialStoreServiceClient();
            MessageFactory messageFactory = MessageFactory.newInstance();
            SOAPMessage soapMessage = messageFactory.createMessage();
            SOAPPart soapPart = soapMessage.getSOAPPart();
            PasswordCredential superAdminCredentials = credentialStoreClient.getPasswordCredential(superAdminToken,gatewayID);
            String authorization = new sun.misc.BASE64Encoder().encode((superAdminCredentials.getLoginUserName()+":"+superAdminCredentials.getPassword()).getBytes());

            String serverURI = "http://services.mgt.tenant.carbon.wso2.org";

            // SOAP Envelope
            SOAPEnvelope envelope = soapPart.getEnvelope();
            envelope.addNamespaceDeclaration("ser", serverURI);

            /*
            Constructed SOAP Request Message:
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://services.mgt.tenant.carbon.wso2.org" xmlns:xsd="http://beans.common.stratos.carbon.wso2.org/xsd">
               <soapenv:Header/>
               <soapenv:Body>
                  <ser:addTenant>
                     <!--Optional:-->
                     <ser:tenantInfoBean>
                        <!--Optional:-->
                        <xsd:active>?</xsd:active>
                        <!--Optional:-->
                        <xsd:admin>?</xsd:admin>
                        <!--Optional:-->
                        <xsd:adminPassword>?</xsd:adminPassword>
                        <!--Optional:-->
                        <xsd:createdDate>?</xsd:createdDate>
                        <!--Optional:-->
                        <xsd:email>?</xsd:email>
                        <!--Optional:-->
                        <xsd:firstname>?</xsd:firstname>
                        <!--Optional:-->
                        <xsd:lastname>?</xsd:lastname>
                        <!--Optional:-->
                        <xsd:originatedService>?</xsd:originatedService>
                        <!--Optional:-->
                        <xsd:successKey>?</xsd:successKey>
                        <!--Optional:-->
                        <xsd:tenantDomain>?</xsd:tenantDomain>
                        <!--Optional:-->
                        <xsd:tenantId>?</xsd:tenantId>
                        <!--Optional:-->
                        <xsd:usagePlan>?</xsd:usagePlan>
                     </ser:tenantInfoBean>
                  </ser:addTenant>
               </soapenv:Body>
             </soapenv:Envelope>
             */

            // SOAP Body
            SOAPBody soapBody = envelope.getBody();
            SOAPElement rootElement = soapBody.addChildElement("addTenant", "ser");
            SOAPElement tenantInfoBean = rootElement.addChildElement("tenantInfoBean","ser");
            SOAPElement active = tenantInfoBean.addChildElement("active","ser");
            active.addTextNode(""+tenantInfoBeanObj.isActive());
            PasswordCredential tenantAdminCredentials = credentialStoreClient.getPasswordCredential(tenantInfoBeanObj.getPasswordCredentialToken(),gatewayID);
            SOAPElement admin = tenantInfoBean.addChildElement("admin","ser");
            admin.addTextNode(tenantAdminCredentials.getLoginUserName());
            SOAPElement adminPassword = tenantInfoBean.addChildElement("adminPassword","ser");
            adminPassword.addTextNode(tenantAdminCredentials.getPassword());
            if(tenantInfoBeanObj.isSetCreatedDate()){
                SOAPElement createdDate = tenantInfoBean.addChildElement("createdDate","ser");
                createdDate.addTextNode(tenantInfoBeanObj.getCreatedDate());
            }
            if(tenantInfoBeanObj.isSetEmail()){
                SOAPElement email = tenantInfoBean.addChildElement("email","ser");
                email.addTextNode(tenantInfoBeanObj.getEmail());
            }
            SOAPElement firstname = tenantInfoBean.addChildElement("firstname","ser");
            firstname.addTextNode(tenantInfoBeanObj.getFirstName());
            SOAPElement lastname = tenantInfoBean.addChildElement("lastname","ser");
            lastname.addTextNode(tenantInfoBeanObj.getLastName());
            if(tenantInfoBeanObj.isSetOriginatedService()){
                SOAPElement originatedService = tenantInfoBean.addChildElement("originatedService","ser");
                originatedService.addTextNode(tenantInfoBeanObj.getOriginatedService());
            }
            if(tenantInfoBeanObj.isSetSuccessKey()){
                SOAPElement successKey = tenantInfoBean.addChildElement("successKey","ser");
                successKey.addTextNode(tenantInfoBeanObj.getSuccessKey());
            }
            SOAPElement tenantDomain = tenantInfoBean.addChildElement("tenantDomain","ser");
            tenantDomain.addTextNode(tenantInfoBeanObj.getTenantDomain());
            SOAPElement tenantId = tenantInfoBean.addChildElement("tenantId","ser");
            tenantId.addTextNode(""+tenantInfoBeanObj.getTenantId());
            if(tenantInfoBeanObj.isSetUsagePlan()){
                SOAPElement usagePlan = tenantInfoBean.addChildElement("usagePlan","ser");
                usagePlan.addTextNode(tenantInfoBeanObj.getUsagePlan().toString());
            }
            MimeHeaders headers = soapMessage.getMimeHeaders();
            headers.addHeader("Authorization", "Basic " + authorization);
            soapMessage.saveChanges();
            return soapMessage;
        } catch (SOAPException ex){
            logger.error("Error while creating SOAP request for Add tenant, SOAP Exception.", ex);
            Wso2IdpAdminServiceException exception = new Wso2IdpAdminServiceException();
            exception.setMessage("Error while creating SOAP request for Add tenant, SOAP Exception. More info : " + ex.getMessage());
            throw exception;
        } catch (TException | ApplicationSettingsException ex){
            logger.error("Error while creating SOAP request for Add tenant, problem retrieving credential store client.", ex);
            Wso2IdpAdminServiceException exception = new Wso2IdpAdminServiceException();
            exception.setMessage("Error while creating SOAP request for Add tenant, problem retrieving credential store client. More info : " + ex.getMessage());
            throw exception;
        }
    }

    public static boolean isSOAPRequestSuccessful(SOAPMessage xmlResponse) throws Wso2IdpAdminServiceException{
        try{
            SOAPPart sourceContent = xmlResponse.getSOAPPart();
            SOAPEnvelope envelope = sourceContent.getEnvelope();
            SOAPBody soapBody = envelope.getBody();
            if(soapBody.hasFault()){
                throw new Wso2IdpAdminServiceException("Check the SOAP request message for correctness (ex: TenantID not unique) : " +
                        soapBody.getFault().getFaultString());
            }else{
                return true;
            }
        }catch (SOAPException ex){
            logger.error("Error while parsing SOAP response for Add tenant, SOAP Exception.", ex);
            Wso2IdpAdminServiceException exception = new Wso2IdpAdminServiceException();
            exception.setMessage("Error while parsing SOAP response for Add tenant, SOAP Exception. More info : " + ex.getMessage());
            throw exception;
        }
    }

}
