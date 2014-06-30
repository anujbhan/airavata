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

package org.apache.aiaravata.application.catalog.data.resources;

import org.airavata.appcatalog.cpi.AppCatalogException;
import org.apache.aiaravata.application.catalog.data.model.ApplicationDeployment;
import org.apache.aiaravata.application.catalog.data.model.LibraryApendPath;
import org.apache.aiaravata.application.catalog.data.model.LibraryApendPath_PK;
import org.apache.aiaravata.application.catalog.data.util.AppCatalogJPAUtils;
import org.apache.aiaravata.application.catalog.data.util.AppCatalogQueryGenerator;
import org.apache.aiaravata.application.catalog.data.util.AppCatalogResourceType;
import org.apache.airavata.common.exception.ApplicationSettingsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryApendPathResource extends AbstractResource {
    private final static Logger logger = LoggerFactory.getLogger(LibraryApendPathResource.class);
    private String deploymentId;
    private String name;
    private String value;
    private AppDeploymentResource appDeploymentResource;

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public AppDeploymentResource getAppDeploymentResource() {
        return appDeploymentResource;
    }

    public void setAppDeploymentResource(AppDeploymentResource appDeploymentResource) {
        this.appDeploymentResource = appDeploymentResource;
    }

    @Override
    public void remove(Object identifier) throws AppCatalogException {
        HashMap<String, String> ids;
        if (identifier instanceof Map){
            ids = (HashMap)identifier;
        }else {
            logger.error("Identifier should be a map with the field name and it's value");
            throw new AppCatalogException("Identifier should be a map with the field name and it's value");
        }
        EntityManager em = null;
        try {
            em = AppCatalogJPAUtils.getEntityManager();
            em.getTransaction().begin();
            AppCatalogQueryGenerator generator= new AppCatalogQueryGenerator(LIBRARY_APEND_PATH);
            generator.setParameter(LibraryApendPathConstants.DEPLOYMENT_ID, ids.get(LibraryApendPathConstants.DEPLOYMENT_ID));
            generator.setParameter(LibraryApendPathConstants.NAME, ids.get(LibraryApendPathConstants.NAME));
            Query q = generator.deleteQuery(em);
            q.executeUpdate();
            em.getTransaction().commit();
            em.close();
        } catch (ApplicationSettingsException e) {
            logger.error(e.getMessage(), e);
            throw new AppCatalogException(e);
        } finally {
            if (em != null && em.isOpen()) {
                if (em.getTransaction().isActive()){
                    em.getTransaction().rollback();
                }
                em.close();
            }
        }
    }

    @Override
    public Resource get(Object identifier) throws AppCatalogException {
        HashMap<String, String> ids;
        if (identifier instanceof Map){
            ids = (HashMap)identifier;
        }else {
            logger.error("Identifier should be a map with the field name and it's value");
            throw new AppCatalogException("Identifier should be a map with the field name and it's value");
        }
        EntityManager em = null;
        try {
            em = AppCatalogJPAUtils.getEntityManager();
            em.getTransaction().begin();
            AppCatalogQueryGenerator generator = new AppCatalogQueryGenerator(LIBRARY_APEND_PATH);
            generator.setParameter(LibraryApendPathConstants.DEPLOYMENT_ID, ids.get(LibraryApendPathConstants.DEPLOYMENT_ID));
            generator.setParameter(LibraryApendPathConstants.NAME, ids.get(LibraryApendPathConstants.NAME));
            Query q = generator.selectQuery(em);
            LibraryApendPath libraryApendPath = (LibraryApendPath) q.getSingleResult();
            LibraryApendPathResource resource =
                    (LibraryApendPathResource) AppCatalogJPAUtils.getResource(AppCatalogResourceType.LIBRARY_APEND_PATH, libraryApendPath);
            em.getTransaction().commit();
            em.close();
            return resource;
        } catch (ApplicationSettingsException e) {
            logger.error(e.getMessage(), e);
            throw new AppCatalogException(e);
        } finally {
            if (em != null && em.isOpen()) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                em.close();
            }
        }
    }

    @Override
    public List<Resource> get(String fieldName, Object value) throws AppCatalogException {
        List<Resource> libApPathList = new ArrayList<Resource>();
        EntityManager em = null;
        try {
            em = AppCatalogJPAUtils.getEntityManager();
            em.getTransaction().begin();
            Query q;
            AppCatalogQueryGenerator generator = new AppCatalogQueryGenerator(LIBRARY_APEND_PATH);
            List results;
            if (fieldName.equals(LibraryApendPathConstants.DEPLOYMENT_ID)) {
                generator.setParameter(LibraryApendPathConstants.DEPLOYMENT_ID, value);
                q = generator.selectQuery(em);
                results = q.getResultList();
                if (results.size() != 0) {
                    for (Object result : results) {
                        LibraryApendPath prepandPath = (LibraryApendPath) result;
                        LibraryApendPathResource resource =
                                (LibraryApendPathResource) AppCatalogJPAUtils.getResource(AppCatalogResourceType.LIBRARY_APEND_PATH, prepandPath);
                        libApPathList.add(resource);
                    }
                }
            } else if (fieldName.equals(LibraryApendPathConstants.NAME)) {
                generator.setParameter(LibraryApendPathConstants.NAME, value);
                q = generator.selectQuery(em);
                results = q.getResultList();
                if (results.size() != 0) {
                    for (Object result : results) {
                        LibraryApendPath prepandPath = (LibraryApendPath) result;
                        LibraryApendPathResource resource =
                                (LibraryApendPathResource) AppCatalogJPAUtils.getResource(AppCatalogResourceType.LIBRARY_APEND_PATH, prepandPath);
                        libApPathList.add(resource);
                    }
                }
            }else {
                em.getTransaction().commit();
                em.close();
                logger.error("Unsupported field name for libraryApendPath resource.", new IllegalArgumentException());
                throw new IllegalArgumentException("Unsupported field name for libraryApendPath resource.");
            }
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new AppCatalogException(e);
        } finally {
            if (em != null && em.isOpen()) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                em.close();
            }
        }
        return libApPathList;
    }

    @Override
    public List<String> getIds(String fieldName, Object value) throws AppCatalogException {
        logger.error("Unsupported for objects with a composite identifier");
        throw new AppCatalogException("Unsupported for objects with a composite identifier");
    }

    @Override
    public void save() throws AppCatalogException {
        EntityManager em = null;
        try {
            em = AppCatalogJPAUtils.getEntityManager();
            LibraryApendPath existigApendPath = em.find(LibraryApendPath.class, new LibraryApendPath_PK(deploymentId, name));
            em.close();

            em = AppCatalogJPAUtils.getEntityManager();
            em.getTransaction().begin();

            ApplicationDeployment deployment = em.find(ApplicationDeployment.class, deploymentId);
            if (existigApendPath !=  null){
                existigApendPath.setValue(value);
                existigApendPath.setApplicationDeployment(deployment);
                em.merge(existigApendPath);
            }else {
                LibraryApendPath prepandPath = new LibraryApendPath();
                prepandPath.setDeploymentID(deploymentId);
                prepandPath.setName(name);
                prepandPath.setValue(value);
                prepandPath.setApplicationDeployment(deployment);
                em.persist(prepandPath);
            }
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new AppCatalogException(e);
        } finally {
            if (em != null && em.isOpen()) {
                if (em.getTransaction().isActive()){
                    em.getTransaction().rollback();
                }
                em.close();
            }
        }
    }

    @Override
    public boolean isExists(Object identifier) throws AppCatalogException {
        HashMap<String, String> ids;
        if (identifier instanceof Map){
            ids = (HashMap)identifier;
        }else {
            logger.error("Identifier should be a map with the field name and it's value");
            throw new AppCatalogException("Identifier should be a map with the field name and it's value");
        }
        EntityManager em = null;
        try {
            em = AppCatalogJPAUtils.getEntityManager();
            LibraryApendPath apendPath = em.find(LibraryApendPath.class,
                    new LibraryApendPath_PK(ids.get(LibraryApendPathConstants.DEPLOYMENT_ID),
                            ids.get(LibraryApendPathConstants.NAME)));
            em.close();
            return apendPath != null;
        } catch (ApplicationSettingsException e) {
            logger.error(e.getMessage(), e);
            throw new AppCatalogException(e);
        } finally {
            if (em != null && em.isOpen()) {
                if (em.getTransaction().isActive()){
                    em.getTransaction().rollback();
                }
                em.close();
            }
        }
    }
}