package org.apache.airavata.allocation.manager.service.handler;

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

import org.apache.airavata.allocation.manager.cpi.AllocationManagerService;
import org.apache.airavata.common.exception.ApplicationSettingsException;
import org.apache.airavata.common.utils.ApplicationSettings;
import org.apache.airavata.common.utils.ServerSettings;
import org.apache.airavata.model.allocation.ResourceDiscovery;
import org.apache.airavata.model.appcatalog.computeresource.ComputeResourceDescription;
import org.apache.airavata.registry.api.RegistryService;
import org.apache.airavata.registry.api.client.RegistryServiceClientFactory;
import org.apache.airavata.registry.api.exception.RegistryServiceException;
import org.apache.airavata.allocation.manager.cpi.exception.AllocationManagerServiceException;
import org.apache.log4j.lf5.util.Resource;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.List;


public class AllocationManagerServerHandler implements AllocationManagerService.Iface{


    @Override
    public List<ResourceDiscovery> getAllocableResourcesForUser(String userID, String gatewayID) throws AllocationManagerServiceException, TException {
        List<ResourceDiscovery> discoveredResources = new ArrayList<ResourceDiscovery>();
        try{
            if(userID == null || gatewayID == null){
                AllocationManagerServiceException ex = new AllocationManagerServiceException();
                ex.setMessage("User ID or Gateway Id cannot be null");
                throw ex;
            }
            RegistryService.Client regClient = getRegistryServiceClient();
            List<ComputeResourceDescription> computeResourceDescriptionList = regClient.getAllComputeResources();
            for(ComputeResourceDescription resourceDescription : computeResourceDescriptionList){
                ResourceDiscovery resource = new ResourceDiscovery();
                resource.setHostName(resourceDescription.getHostName());
                resource.setResourceDescription(resourceDescription.getResourceDescription());
                resource.setResourceID(resource.getResourceID());
            }
            return discoveredResources;
        }catch (AllocationManagerServiceException ex){
            throw new AllocationManagerServiceException("Resource discovery failed for user " + userID + " in gateway " + gatewayID);
        }catch (ApplicationSettingsException ex){
            throw new TException("Call to registry client failed ",ex);
        }
    }

    private RegistryService.Client getRegistryServiceClient() throws TException, ApplicationSettingsException {
        try{
        final int serverPort = Integer.parseInt(ServerSettings.getRegistryServerPort());
        final String serverHost = ServerSettings.getRegistryServerHost();
            return RegistryServiceClientFactory.createRegistryClient(serverHost, serverPort);
        } catch (RegistryServiceException e) {
            throw new TException("Unable to create registry client...", e);
        } catch (ApplicationSettingsException e){
            throw new ApplicationSettingsException("Cannot retrieve registry client, check settings ",e);
        }
    }
}
