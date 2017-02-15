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
include "../data-models/wso2-admin-service-models/wso2_idp_admin_service_data_models.thrift"

namespace java org.apache.airavata.wso2.idp.admin.cpi

const string WSO2_IDP_ADMIN_SERVICE_VERSION = "0.01"

service Wso2IdpAdminService {

      /* Method used to add tenant into WSO2 Identity Server */
      string addTenant(1: required wso2_idp_admin_service_data_models.TenantInfoBean tenantInfoBean)
            throws (1: wso2_idp_admin_cpi_errors.Wso2IdpAdminServiceException wso2IdpAdminServiceException)

}
