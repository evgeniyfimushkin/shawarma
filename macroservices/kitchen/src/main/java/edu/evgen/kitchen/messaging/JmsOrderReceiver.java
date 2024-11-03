package edu.evgen.kitchen.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.evgen.kitchen.entities.ShawarmaOrder;
import jakarta.jms.BytesMessage;
import jakarta.jms.Message;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import static edu.evgen.kitchen.util.ConsoleColors.GREEN;
import static edu.evgen.kitchen.util.ConsoleColors.RESET;

@Component
@RequiredArgsConstructor
public class JmsOrderReceiver implements OrderReciever {

    final private JmsTemplate jms;
//    final private MessageConverter converter;

    @SneakyThrows
    @Override
    public ShawarmaOrder receiveOrder() {
        BytesMessage message = (BytesMessage) jms.receive("shawarma.order.queue");
        if(message == null)
            return null;
        byte[] byteData = new byte[(int) message.getBodyLength()];
        message.readBytes(byteData);

        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .readValue(byteData, ShawarmaOrder.class);
    }
}
