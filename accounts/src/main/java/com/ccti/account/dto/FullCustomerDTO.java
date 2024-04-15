package com.ccti.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
        name = "FullCustomerDto",
        description = "Schema to hold All customer data including cards and loans"
)
@Data
public class FullCustomerDTO {

    @Schema(
            description = "Details of the customer"
    )
    CustomerDTO customer;

    @Schema(
            description = "Details of the Account"
    )
    AccountDTO account;

    @Schema(
            description = "Details of the Account"
    )
    CardDTO cards;

    @Schema(
            description = "Details of the Loan"
    )
    LoansDto loan;

}