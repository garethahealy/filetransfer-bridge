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

import java.io.File;

import org.apache.camel.CamelExchangeException;
import org.apache.camel.Exchange;
import org.apache.camel.InvalidPayloadException;
import org.apache.camel.Processor;

public class IdValidatorProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String fileName = exchange.getIn().getHeader(Exchange.FILE_NAME_ONLY, String.class);
        if (!(fileName.charAt(0) == 'E' || fileName.charAt(0) == 'P')) {
            throw new CamelExchangeException("Expected file to start with an 'E' or 'P' - Found: " + fileName.charAt(0) + " / " + fileName, exchange,
                                             new InvalidPayloadException(exchange, File.class));
        }
    }
}
