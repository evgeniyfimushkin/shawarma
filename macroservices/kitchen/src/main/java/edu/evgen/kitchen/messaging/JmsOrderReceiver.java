package edu.evgen.kitchen.messaging;

import edu.evgen.kitchen.entities.ShawarmaOrder;
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
    final private MessageConverter converter;

    @SneakyThrows
    @Override
    public ShawarmaOrder receiveOrder() {
        Message message = jms.receive("shawarma.order.queue");
        System.out.println(GREEN + "------------------------" + RESET);


        System.out.println(message);


        System.out.println(GREEN + "------------------------" + RESET);
        return (ShawarmaOrder) converter.fromMessage(message);
    }
}
