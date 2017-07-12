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

import org.apache.camel.CamelExchangeException;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.test.junit4.ExchangeTestSupport;
import org.junit.Test;

public class LengthValidatorProcessorTest extends ExchangeTestSupport {

    @Test
    public void handlesValidFileName() throws Exception {
        Exchange exchange = createExchange();
        Message in = exchange.getIn();
        in.setHeader(Exchange.FILE_NAME_ONLY, "this_file_is_long_enough");

        Processor processor = new LengthValidatorProcessor();
        processor.process(exchange);
    }

    @Test(expected = CamelExchangeException.class)
    public void throwsCamelExchangeExceptionInvalidFileName() throws Exception {
        Exchange exchange = createExchange();
        Message in = exchange.getIn();
        in.setHeader(Exchange.FILE_NAME_ONLY, "toshort");

        Processor processor = new LengthValidatorProcessor();
        processor.process(exchange);
    }
}
