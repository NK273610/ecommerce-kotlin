package com.order.ecommerce.errorHandling;

import lombok.*;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
public class ErrorData {
    public String code;
    public String title;
    public String detail;
}
