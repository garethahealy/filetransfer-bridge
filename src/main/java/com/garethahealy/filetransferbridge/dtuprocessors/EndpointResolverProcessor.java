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

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

public class EndpointResolverProcessor implements Processor {
    
    @Override
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        String currentFileName = in.getHeader(Exchange.FILE_NAME, String.class);

        //i.e.: Do the magic which decides where the file should be written to and how, maybe this comes from a DB?
        in.setHeader("producer.username", exchange.getContext().resolvePropertyPlaceholders("{{garethahealy.sftp.username}}"));
        in.setHeader("producer.password", exchange.getContext().resolvePropertyPlaceholders("{{garethahealy.sftp.password}}"));
        in.setHeader("producer.host", exchange.getContext().resolvePropertyPlaceholders("{{garethahealy.sftp.host}}"));
        in.setHeader("producer.directory", exchange.getContext().resolvePropertyPlaceholders("{{garethahealy.sftp.homedir}}/e2e"));
        in.setHeader(Exchange.FILE_NAME, "mvd-" + currentFileName);
    }
}
