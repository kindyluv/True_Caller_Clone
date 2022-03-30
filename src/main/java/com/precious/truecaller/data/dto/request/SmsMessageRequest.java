package com.precious.truecaller.data.dto.request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;;import javax.validation.constraints.NotNull;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SmsMessageRequest {
    @NotNull(message = "Field can not be empty")
    private String messageBody;
    @NotNull(message = "Field can not be empty")
    private String smsSender;
    @NotNull(message = "Field can not be empty")
    private String smsReceiver;
}
