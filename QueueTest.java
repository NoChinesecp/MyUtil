package com.qhit.RabbitMQ;

        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author name
 * @date 2019-05-17 9:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QueueTest {
        @Autowired
        private Sender helloSender;
        @Test
        public void hello() throws Exception {
                helloSender.send("我不是一条信息");


        }

}
