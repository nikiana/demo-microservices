package com.sberbank.demoProject.adminMicroservice.models.requests;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class EnableUserRequest {
    boolean enabled;
}
