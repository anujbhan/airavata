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
 * Component Programming Interface definition for Apache Airavata Resource Allocation Service.
 *
*/

namespace java org.apache.airavata.allocation.manager.cpi

include "../data-models/allocation-manager-models/allocation_resource_discovery_model.thrift"
include "allocation_manager_cpi_errors.thrift"


service AllocationManagerService {

    list<allocation_resource_discovery_model.ResourceDiscovery> getAllocableResourcesForUser(1: required string userID,
                                                                                    2: required string gatewayID) throws (1: allocation_manager_cpi_errors.AllocationManagerServiceException ex)
}