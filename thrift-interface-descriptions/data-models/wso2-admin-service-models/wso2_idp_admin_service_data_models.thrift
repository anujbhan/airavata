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

 namespace java org.apache.airavata.model.wso2.adminservice
 namespace php Airavata.Model.Wso2.AdminService

 /*
 * TenantInfoBean contains information specific to a particular tenant, this data model will used to add
 * Tenants and retrieve tenant information
 *
 * Note: The field in this data models are directly fetched from
 *       wso2 IS site : https://docs.wso2.com/display/IS510/Tenant+Management+with+APIs#TenantManagementwithAPIs-addTenant ,
 *       Some of the fields may not be useful in our scenario, but adding those will allow flexibility
 *
 * active : Boolean
 *   true - activate tenant
 *   false - deactivate tenant
 *
 * passwordCredentialToken : String
 *   Password credential token used to fetch tenant admin username and password
 *
 * createdDate : string
 *   Date and time of tenant creation
 *
 * email : String
 *   Email address of the tenant
 *
 * firstName : String
 *   First name of the tenant
 *
 * lastName : String
 *   last name of the tenant
 *
 * originatedService : String
 *
 * successKey : String
 *
 * tenantDomain : String
 *    Tenant domain name
 *
 * tenantId : integer
 *    Tenant ID
 *
 * usagePlan : String
 *    usage plan for tenant
 *
 * */


struct TenantInfoBean{
    1: required bool active;
    2: required string passwordCredentialToken;
    3: optional string createdDate;
    4: optional string email;
    5: required string firstName;
    6: required string lastName;
    7: optional string originatedService;
    8: optional string successKey;
    9: required string tenantDomain;
    10: required i32 tenantId;
    11: optional string usagePlan;
}