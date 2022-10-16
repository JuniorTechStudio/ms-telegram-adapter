package com.jts.subscription.telegram.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SystemUser {

    private String id;
    private String firstName;
    private String lastName;
    private String userName;
    private boolean isBlocked;

}
