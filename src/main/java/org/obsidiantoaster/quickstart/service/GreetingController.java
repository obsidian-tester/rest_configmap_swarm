/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.obsidiantoaster.quickstart.service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.wildfly.swarm.spi.runtime.annotations.ConfigurationValue;

@Path("/")
@ApplicationScoped
public class GreetingController {

    private static final AtomicLong counter = new AtomicLong();

    @Inject
    @ConfigurationValue("service.greeting")
    Optional<String> greetingValue;

    @GET
    @Path("/greeting")
    @Produces("application/json")
    public Greeting greeting() {
        String suffix = greetingValue.isPresent() ? greetingValue.get(): "n/a";
        return new Greeting(counter.incrementAndGet(), "Hello "+suffix);
    }
}
