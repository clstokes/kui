## What is kui?

kui is a modern, browser-based administration tool for Java applications supporting the JMX specification. kui is designed to be deployed along-side existing web applications in an application server or servlet container.

## Deployment

kui is deployed as a web application in your application server or servlet container. Just drop the kui.war file where your other web applications are deployed, start your container and you're good to go. See the Configuration section below for configuration options.

## Configuration

The only configuration option in kui is to specify a username and password which are as system properties by kui at startup; the parameter names are kui.username and kui.password. Many application servers allow you to set system properties in your server configuration or in some cases they can be specified via your startup command.

Here is a simple example using the Resin application server:
java -Dkui.username=username -Dkui.password=password -jar lib/resin.jar console

## Logging

kui uses slf4j for logging and is packaged with the JDK14 implementation. This can be changed as needed by expanding the kui.war file and replacing the slf4j-jdk14.jar file with the slf4j implementation of your choice.

## Requirements

kui requires Java 5 or higher and a supported container: Apache Tomcat 5.x/6.x, Glassfish v3, JBoss Application Server 6.0.0M1, Jetty 6.1.22, and Resin 3.x/4.x.

## Browser Support

kui has been tested with the following web browsers: Chrome 4.0, Firefox 3.5, Internet Explorer 8.0, Opera 10.10, and Safari 4.0.

## License

<pre>
(New BSD License)

Copyright (c) 2011, Cameron Stokes
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the name of the <organization> nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
</pre>

