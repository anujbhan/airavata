/**
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
 */
/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.apache.airavata.model;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum ComponentState implements org.apache.thrift.TEnum {
  CREATED(0),
  WAITING(1),
  READY(2),
  RUNNING(3),
  COMPLETED(4),
  FAILED(5),
  CANCELED(6);

  private final int value;

  private ComponentState(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static ComponentState findByValue(int value) { 
    switch (value) {
      case 0:
        return CREATED;
      case 1:
        return WAITING;
      case 2:
        return READY;
      case 3:
        return RUNNING;
      case 4:
        return COMPLETED;
      case 5:
        return FAILED;
      case 6:
        return CANCELED;
      default:
        return null;
    }
  }
}
