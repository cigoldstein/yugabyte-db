/*
 * Copyright 2021 YugaByte, Inc. and Contributors
 *
 * Licensed under the Polyform Free Trial License 1.0.0 (the "License"); you
 * may not use this file except in compliance with the License. You
 * may obtain a copy of the License at
 *
 * http://github.com/YugaByte/yugabyte-db/blob/master/licenses/POLYFORM-FREE-TRIAL-LICENSE-1.0.0.txt
 */
package com.yugabyte.yw.models.filters;

import com.yugabyte.yw.models.AlertDefinitionLabel;
import com.yugabyte.yw.models.helpers.KnownAlertLabels;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Value
@Builder
public class AlertDefinitionFilter {
  Set<UUID> uuids;
  UUID customerUuid;
  String name;
  AlertDefinitionLabel label;
  Boolean active;
  Boolean configWritten;

  // Can't use @Builder(toBuilder = true) as it sets null fields as well, which breaks non null
  // checks.
  public AlertDefinitionFilterBuilder toBuilder() {
    AlertDefinitionFilterBuilder result = AlertDefinitionFilter.builder();
    if (uuids != null) {
      result.uuids(uuids);
    }
    if (customerUuid != null) {
      result.customerUuid(customerUuid);
    }
    if (name != null) {
      result.name(name);
    }
    if (label != null) {
      result.label(label);
    }
    if (active != null) {
      result.active(active);
    }
    if (configWritten != null) {
      result.configWritten(configWritten);
    }
    return result;
  }

  public static class AlertDefinitionFilterBuilder {
    Set<UUID> uuids = new HashSet<>();
    UUID customerUuid;
    String name;
    AlertDefinitionLabel label;
    Boolean active;
    Boolean configWritten;

    public AlertDefinitionFilterBuilder uuids(@NonNull UUID uuid) {
      this.uuids.add(uuid);
      return this;
    }

    public AlertDefinitionFilterBuilder uuids(@NonNull Collection<UUID> uuids) {
      this.uuids.addAll(uuids);
      return this;
    }

    public AlertDefinitionFilterBuilder customerUuid(@NonNull UUID customerUuid) {
      this.customerUuid = customerUuid;
      return this;
    }

    public AlertDefinitionFilterBuilder name(@NonNull String name) {
      this.name = name;
      return this;
    }

    public AlertDefinitionFilterBuilder active(@NonNull Boolean active) {
      this.active = active;
      return this;
    }

    public AlertDefinitionFilterBuilder configWritten(@NonNull Boolean configWritten) {
      this.configWritten = configWritten;
      return this;
    }

    public AlertDefinitionFilterBuilder label(
        @NonNull KnownAlertLabels name, @NonNull String value) {
      label = new AlertDefinitionLabel(name, value);
      return this;
    }

    public AlertDefinitionFilterBuilder label(@NonNull String name, @NonNull String value) {
      label = new AlertDefinitionLabel(name, value);
      return this;
    }

    public AlertDefinitionFilterBuilder label(@NonNull AlertDefinitionLabel label) {
      this.label = label;
      return this;
    }
  }
}
