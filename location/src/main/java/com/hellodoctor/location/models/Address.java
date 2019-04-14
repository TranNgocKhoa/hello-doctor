package com.hellodoctor.location.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Khoa
 * @created 3/31/2019
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @ApiModelProperty(dataType = "String")
    private String resultAddress;
    private String type;
}
