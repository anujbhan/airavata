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

package org.apache.airavata.wso2.idp.admin.core.soapclient;

import org.apache.airavata.common.utils.WSConstants;
import org.apache.airavata.wso2.idp.admin.cpi.Wso2IdpAdminService;
import org.apache.airavata.wso2.idp.admin.cpi.exceptions.Wso2IdpAdminServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.soap.*;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

public class Wso2IdpServerSoapClient {

    private final static Logger logger = LoggerFactory.getLogger(Wso2IdpServerSoapClient.class);

    private static Wso2IdpAdminService clientObj = new Wso2IdpAdminService();

    public static Wso2IdpAdminService getClientObj(){
        return clientObj;
    }


    public static SOAPMessage sendSOAPRequest(SOAPMessage requestMessage, String url) throws Wso2IdpAdminServiceException {
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(requestMessage, url);

            //close connection
            soapConnection.close();

            return soapResponse;
        } catch (SOAPException ex) {
            logger.error("Error while sending SOAP request to Wso 2 API, SOAP Exception.", ex);
            Wso2IdpAdminServiceException exception = new Wso2IdpAdminServiceException();
            exception.setMessage("Error while sending SOAP request to Wso 2 API, SOAP Exception. More info : " + ex.getMessage());
            throw exception;
        }
    }
}
