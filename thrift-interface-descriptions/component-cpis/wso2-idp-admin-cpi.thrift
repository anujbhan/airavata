/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */

/*
 * Component Programming Interface definition for Apache Airavata User profile registry Service.
 *
*/

include "wso2_idp_admin_cpi_errors.thrift"

namespace java org.apache.airavata.userprofile.cpi

const string WSO2_IDP_ADMIN_SERVICE_VERSION = "0.17"

service Wso2IdpAdminService {
      string addTenant(1: required string TenantName)
            throws (1: wso2_idp_admin_cpi_errors.Wso2IdpAdminServiceException wso2IdpAdminServiceException)
}