package com.tobi.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response information"
)
public class ErrorResponseDto {

    @Schema(
            description = "API path invoked by client", example = "api/user"
    )
    private  String apiPath;

    @Schema(
            description = "Error code representing the error type"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Error message representing the error type"
    )
    private  String errorMessage;

    @Schema(
            description = "Time representing when the error occurred", example = "2023-10-14T19:35:41.922Z"
    )
    private LocalDateTime errorTime;
}
