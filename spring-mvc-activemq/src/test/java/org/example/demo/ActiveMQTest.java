package org.example.demo;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class ActiveMQTest {

    private static final String ACTIVEMQ_RUL = "tcp://192.168.114.128:61616";

    private static final String QUEUE_NAME = "queue1";

    @Test
    public void createQueueProducer() throws JMSException {
        //1、创建连接工厂类，给定URL地址，采用默认的用户名和密码
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_RUL);

        //2、通过工厂bean对象获得连接，并启动访问
        Connection connection = factory.createConnection();
        connection.start();

        //3、创建会话session 参数1表示事务，参数2签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //4、创建目的地,队列
        Queue queue = session.createQueue(QUEUE_NAME);

        //5、创建消息生产者
        MessageProducer producer = session.createProducer(queue);

        //6、通过MessageProducer 发送三条消息到MQ队列里
        for (int i = 1; i <= 3; i++) {
            //7、创建消息
            TextMessage textMessage = session.createTextMessage("msg---" + i);//理解为字符串

            //8、发送消息
            producer.send(textMessage);
        }

        //9、关闭资源
        producer.close();
        session.close();
        connection.close();
        log.info("********消息发送到MQ完成");
    }

    @Test
    public void createQueueCustomer() throws JMSException, IOException {
        //1、创建连接工厂类，给定URL地址，采用默认的用户名和密码
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_RUL);

        //2、通过工厂bean对象获得连接，并启动访问
        Connection connection = factory.createConnection();
        connection.start();

        //3、创建会话session 参数1表示事务，参数2签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //4、创建目的地,队列
        Queue queue = session.createQueue(QUEUE_NAME);

        //5、创建消费者
        MessageConsumer consumer = session.createConsumer(queue);

//        //6、通过MessageConsumer接收队列消息
//        while (true) {
////           TextMessage message = (TextMessage) consumer.receive();
//            //设置多长时间之后，自动关闭
//           TextMessage message = (TextMessage) consumer.receive(4000L);
//           if (null!=message){
//               log.info("*****消费者接收到消息："+message.getText());
//           }else {
//               break;
//           }
//        }
        //6、监听器方法获取消息
        consumer.setMessageListener(new MessageListener() {
            @SneakyThrows
            @Override
            public void onMessage(Message message) {
                if (Objects.nonNull(message) && message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    log.info("*****消费者接收到消息：" + textMessage.getText());
                }
            }
        });
        //等待
        System.in.read();
        //9、关闭资源
        consumer.close();
        session.close();
        connection.close();
    }


}
