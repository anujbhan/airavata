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

package org.apache.aiaravata.application.catalog.data.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "GRID_FTP_DATAMOVEMENT")
public class GridFTPDataMovement implements Serializable {
    @Id
    @Column(name = "DATA_MOVE_ID")
    private String dataMoveID;
    @Column(name = "SECURITY_PROTOCOL")
    private String securityProtocol;
    @Column(name = "GRID_FTP_EP")
    private String gridFTPEP;

    public String getDataMoveID() {
        return dataMoveID;
    }

    public void setDataMoveID(String dataMoveID) {
        this.dataMoveID = dataMoveID;
    }

    public String getSecurityProtocol() {
        return securityProtocol;
    }

    public void setSecurityProtocol(String securityProtocol) {
        this.securityProtocol = securityProtocol;
    }

    public String getGridFTPEP() {
        return gridFTPEP;
    }

    public void setGridFTPEP(String gridFTPEP) {
        this.gridFTPEP = gridFTPEP;
    }
}