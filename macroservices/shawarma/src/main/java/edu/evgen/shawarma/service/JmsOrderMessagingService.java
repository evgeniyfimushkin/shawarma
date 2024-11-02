package edu.evgen.shawarma.service;

import edu.evgen.shawarma.entities.ShawarmaOrder;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JmsOrderMessagingService {
    private final JmsTemplate jms;

    public void sendOrder(ShawarmaOrder order) {
        jms.convertAndSend("shawarma.order.queue", order, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws JMSException {
                message.setStringProperty("X_ORDER_SOURCE", "WEB");
                return message;
            }
        });
//        jms.send(session -> session.createObjectMessage(order));
    }

}
