/*-
 * #%L
 * File Transfer Bridge
 * %%
 * Copyright (C) 2013 - 2017 Gareth Healy
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.garethahealy.filetransferbridge.dtuprocessors;

import java.util.Properties;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.test.junit4.ExchangeTestSupport;
import org.junit.Test;

public class EndpointResolverProcessorTest extends ExchangeTestSupport {

    @Override
    protected Properties useOverridePropertiesWithPropertiesComponent() {
        Properties answer = new Properties();
        answer.setProperty("garethahealy.sftp.username", "test");
        answer.setProperty("garethahealy.sftp.password", "test");
        answer.setProperty("garethahealy.sftp.host", "test");
        answer.setProperty("garethahealy.sftp.homedir", "test");

        return answer;
    }

    @Test
    public void canProcess() throws Exception {
        Exchange exchange = createExchange();
        Message in = exchange.getIn();
        in.setHeader(Exchange.FILE_NAME, "this_file_is_long_enough.txt");

        Processor processor = new EndpointResolverProcessor();
        processor.process(exchange);

        assertNotNull(in.getHeader("producer.username", String.class));
        assertNotNull(in.getHeader("producer.password", String.class));
        assertNotNull(in.getHeader("producer.host", String.class));
        assertNotNull(in.getHeader("producer.directory", String.class));
        assertNotNull(in.getHeader(Exchange.FILE_NAME, String.class));

        assertEquals("test", in.getHeader("producer.username", String.class));
        assertEquals("test", in.getHeader("producer.password", String.class));
        assertEquals("test", in.getHeader("producer.host", String.class));
        assertEquals("test/e2e", in.getHeader("producer.directory", String.class));
        assertEquals("mvd-this_file_is_long_enough.txt", in.getHeader(Exchange.FILE_NAME, String.class));
    }
}
