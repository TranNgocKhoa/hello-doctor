package com.hellodoctor.location.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Khoa
 * @created 4/14/2019
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AddressAC extends Address {
    @ApiModelProperty(dataType = "String")
    private String mainText;
    public AddressAC(String address, String mainText) {
        this.setResultAddress(address);
        this.mainText = mainText;
    }
}
