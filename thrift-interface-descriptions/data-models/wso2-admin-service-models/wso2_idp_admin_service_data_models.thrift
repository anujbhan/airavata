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

/* Usage plan for tenant
* Currently only Demo is supported
* */

enum TenantUsagePlan{
    DEMO
}

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
 *   format : ISO 8601 formatted date ( 2017-02-16T16:34:08+00:00 )
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
 *    Tenant domain name, this should be unique in wso2
 *
 * tenantId : integer
 *    Tenant ID, this should be unique in wso2
 *
 * usagePlan : String
 *    usage plan for tenant
 *
 * */

struct TenantInfoBean{
    1: required bool active;
    2: required string passwordCredentialToken;
    3: required string createdDate;
    4: required string email;
    5: required string firstName;
    6: required string lastName;
    7: optional string originatedService;
    8: optional string successKey;
    9: required string tenantDomain;
    10: required i32 tenantId;
    11: optional TenantUsagePlan usagePlan;
}

/*
* Service provider data model :
*           The process of creating a service in WSo 2 IS is a three step process,
*                       1 : Create a service provider with applicationName & description
*                       2 : Create a OAuthApplication binding
*                       3 : Update the service provider with OAuthBinding*/

struct ServiceProvider{
    1: optional i32 applicationId;
    2: required string applicationName;
    3: required string description;
    4: optional OAuthApplicationData OAuthConfig;
}

struct OAuthApplicationData{
    1: required string OAuthVersion;
    2: required string applicationName;
    3: required list<string> grantTypes;
}

/* Below are the data types we will require when,
 * custom Oauth provider support is enabled, currently only default (WSo 2 IS) is supported
*/

//enum IdentityProviderUserStoreOptions{
//    PRIMARY
//}
//
//struct IdentityProviderProvisioningInfo{
//    1: required bool provisioningEnabled;
//    2: required IdentityProviderUserStoreOptions userStore;
//}
//
///* Claim configuration information :
//*           Not all features are supported yet, only Basic Claim Configuration can be set up.
//*/
//struct IdpClaims{
//    1: required string claimId;
//    2: required string claimUri;
//}
//
//struct ClaimConfiguration{
//    1: required bool localClaimDialect;
//    2: optional List<IdpClaims> claimList;
//    3: optional string roleClaimURI;
//    4: optional string userClaimURI;
//}
//
//
///* Role Configuration Information :
//*           Not all features are implemented, only simple mapping can be set up, which is suffiecient in most cases.
//*/
//struct RoleMapping{
//    1: required string localRoleName;
//    2: required string remoteRoleName;
//}
//
//struct RoleConfiguration{
//    1: required string IdpRole;
//    2: required RoleMapping mapping;
//}
//
///* Federated Auth Configuration :
//*       Only OAuth2/OpenID connect is supported.
//* */
//
//enum FederatedAuthPropertyKeyName{
//    ClientId,
//    OAuth2AuthzUrl,
//    OAUTH2TokenUrl,
//    ClientSecret,
//    IsUserIdInClaims,
//    commonAuthQueryParams
//}
//
//struct FederatedAuthProperties{
//    1: optional bool confidential; // only required when providing Client secret, value 'true'
//    2: required string name;
//    3: required string value;
//}
//
//struct FederatedAuthenticatorConfigs{
//    1: required string displayName;
//    2: required bool enabled;
//    3: required string name;
//    // The list should have, clientId, Oauth2 AuthzUrl, Token Url, secret etc.
//    4: optional List<FederatedAuthProperties> propertyList;
//}
//
//struct IdentityProvider{
//    1: required string alias;
//    2: optional string certificate; // Base64Encode of public certificate
//    3: required string displayName;
//    4: required bool enable;
//    5: required bool federationhub;
//    6: optional string homeRealmId;
//    7: required string identityProviderDescription;
//    8: required string identityProviderName; // unique name required
//    // Provisioning
//    9: optional IdentityProviderProvisioningInfo provisioningInfo;
//    // Claim Configuration
//    10: optional ClaimConfiguration claimConfig;
//    // Role Configuration
//    11: optional RoleConfiguration roleConfig;
//    // Federated Auth configuration
//    12: optional FederatedAuthenticatorConfigs authConfig;
//}
