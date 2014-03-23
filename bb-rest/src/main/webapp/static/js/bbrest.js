/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var bbRest = angular.module('bbRest',[]);
 
bbRest.controller('ConfigController', ['$scope','$http', function($scope, $http) {
        
        /*
        $http.get('../info')
        .success(function(data){
            $scope.info = data;
        });
        */
       
       var info = {"jvmInfo":{"systemProperties":{"wrapper.service":"TRUE","java.vm.version":"23.25-b01","wrapper.version":"3.2.3","log4j.filename":"/usr/local/blackboard/content/vi/BBLEARN/plugins/Bb-mobile/config/logs/bbdev/Bb-mobile-log.txt","java.vendor.url":"http://java.oracle.com/","sun.jnu.encoding":"UTF-8","wrapper.jvm.port.max":"31999","wrapper.jvm.port.min":"31000","java.vm.info":"mixed mode","user.dir":"/usr/local/blackboard/apps/service-wrapper/bin","java.awt.headless":"true","clover.initstring.basedir":"/usr/local/blackboard/system/clover","sun.cpu.isalist":"","java.awt.graphicsenv":"sun.awt.X11GraphicsEnvironment","wrapper.jvmid":"1","sun.os.patch.level":"unknown","catalina.useNaming":"true","org.apache.jasper.runtime.BodyContentImpl.LIMIT_BUFFER":"true","java.io.tmpdir":"/usr/local/blackboard/apps/tomcat/temp","user.home":"/home/bbuser","java.awt.printerjob":"sun.print.PSPrinterJob","bbservices_config":"/usr/local/blackboard/config/service-config.properties","java.version":"1.7.0_25","file.encoding.pkg":"sun.io","package.access":"sun.,org.apache.catalina.,org.apache.coyote.,org.apache.tomcat.,org.apache.jasper.","java.vendor.url.bug":"http://bugreport.sun.com/bugreport/","file.encoding":"UTF-8","line.separator":"\n","sun.java.command":"org.tanukisoftware.wrapper.WrapperStartStopApp blackboard.tomcat.startup.ThreadDumpWrapper 1 start blackboard.tomcat.startup.ThreadDumpWrapper true 1 stop","java.vm.specification.vendor":"Oracle Corporation","java.security.policy":"/usr/local/blackboard/apps/tomcat/conf/catalina.policy","wrapper.pid":"2032","java.util.logging.manager":"org.apache.juli.ClassLoaderLogManager","blackboard.wrapper.commandfile":"/usr/local/blackboard/apps/tomcat/temp/wrapper.command","org.apache.kahadb.util.LockFile.lock./usr/local/blackboard/content/vi/BBLEARN/activemq/messageQueueService/kaha/lock":"Fri Mar 21 23:04:00 CET 2014","tomcat.util.buf.StringCache.byte.enabled":"true","catalina.home":"/usr/local/blackboard/apps/tomcat","java.vm.vendor":"Oracle Corporation","java.class.path":"/usr/local/blackboard/apps/service-wrapper/lib/wrapper.jar:/usr/java/jdk1.7.0_25/lib/tools.jar:/usr/local/blackboard/system/build/clover-ant/lib/clover.jar:/usr/local/blackboard/apps/tomcat/bin/bootstrap.jar:/usr/local/blackboard/apps/tomcat/lib/bb-tomcat-bootstrap.jar:/usr/local/blackboard/apps/tomcat/bin/tomcat-juli.jar","sun.io.unicode.encoding":"UnicodeLittle","wrapper.port":"32001","os.arch":"amd64","user.language":"en","user.name":"bbuser","java.runtime.version":"1.7.0_25-b15","sun.boot.class.path":"/usr/java/jdk1.7.0_25/jre/lib/resources.jar:/usr/java/jdk1.7.0_25/jre/lib/rt.jar:/usr/java/jdk1.7.0_25/jre/lib/sunrsasign.jar:/usr/java/jdk1.7.0_25/jre/lib/jsse.jar:/usr/java/jdk1.7.0_25/jre/lib/jce.jar:/usr/java/jdk1.7.0_25/jre/lib/charsets.jar:/usr/java/jdk1.7.0_25/jre/lib/jfr.jar:/usr/java/jdk1.7.0_25/jre/classes","package.definition":"sun.,java.,org.apache.catalina.,org.apache.coyote.,org.apache.tomcat.,org.apache.jasper.","sun.cpu.endian":"little","Xythos.RunMode":"server","java.security.manager":"","tomcat.util.scan.DefaultJarScanner.jarsToSkip":"bootstrap.jar,commons-daemon.jar,tomcat-juli.jar,annotations-api.jar,el-api.jar,jsp-api.jar,servlet-api.jar,catalina.jar,catalina-ant.jar,catalina-ha.jar,catalina-tribes.jar,jasper.jar,jasper-el.jar,ecj-*.jar,tomcat-api.jar,tomcat-util.jar,tomcat-coyote.jar,tomcat-dbcp.jar,tomcat-jni.jar,tomcat-spdy.jar,tomcat-i18n-en.jar,tomcat-i18n-es.jar,tomcat-i18n-fr.jar,tomcat-i18n-ja.jar,tomcat-juli-adapters.jar,catalina-jmx-remote.jar,catalina-ws.jar,tomcat-jdbc.jar,tools.jar,commons-beanutils*.jar,commons-codec*.jar,commons-collections*.jar,commons-dbcp*.jar,commons-digester*.jar,commons-fileupload*.jar,commons-httpclient*.jar,commons-io*.jar,commons-lang*.jar,commons-logging*.jar,commons-math*.jar,commons-pool*.jar,jstl.jar,geronimo-spec-jaxrpc*.jar,wsdl4j*.jar,ant.jar,ant-junit*.jar,aspectj*.jar,jmx.jar,h2*.jar,hibernate*.jar,httpclient*.jar,jmx-tools.jar,jta*.jar,log4j*.jar,mail*.jar,slf4j*.jar,xercesImpl.jar,xmlParserAPIs.jar,xml-apis.jar,junit.jar,junit-*.jar,ant-launcher.jar,struts-core-*.jar,struts-extras-*.jar,xml-apis*.jar,dom4j*.jar,aopalliance*.jar,jackson-*.jar,commons-validator*.jar,weblogic-webservice-client-9.2.0.0.jar,activation.jar,aopalliance-1.0.jar,axis.jar,bcprov-jdk15-137.jar,bsh-bsf-2.0b4.jar,bsh-core-2.0b4.jar,cssparser-0.9.5.jar,dom4j-1.6.1.jar,dwr.jar,ehcache-core.jar,ehcache-jmsreplication.jar,guava-14.0.jar,guice-3.0.jar,slf4j-api.jar,slf4j-log4j12.jar,ezmorph-1.0.6.jar,java-getopt-1.0.13.jar,javax.inject-1.jar,jaxen-1.1.1.jar,jaxrpc.jar,jdom.jar,jep-3.2.0.jar,jldap.jar,js.jar,json-lib-2.2.3-jdk15.jar,jsse-1.0.2.jar,jta.jar,log4j.jar,mail.jar,oauth-20100601.jar,oauth-provider-20100527.jar,opencsv-1.8.jar,org.mortbay.jetty.jar,rome-1.0.jar,rome-fetcher-1.0.jar,sac.jar,sdostudent.jar,serializer.jar,sifworks-adk.jar,sigar.jar,soap.jar,Tidy.jar,velocity-1.5.jar,WebEQApplet.jar,wsdl4j.jar,xalan.jar,yuicompressor-css-2.4.7.jar,bb-castor.jar,bb-chalkbox.jar,bb-chat-client.jar,bb-client.jar,bb-cms-admin.jar,bb-collab-client.jar,bb-collab-platform.jar,bb-collab-server.jar,bb-content-exchange.jar,bb-encryption.jar,bb-exec.jar,bb-openldap.jar,bb-platform.jar,bb-qti.jar,bb-scorm.jar,bb-sif.jar,bb-snapshot.jar,bb-soap.jar,bb-telephony.jar,bb-tomcat-bootstrap.jar,bb-tomcatboot.jar,bb-vc-client.jar,bb-webcas.jar,bb-ws-announcement.jar,bb-ws-calendar.jar,bb-ws-NotificationDistributorOperations.jar,bb-ws-content.jar,bb-ws-context.jar,bb-ws-course.jar,bb-ws-coursemembership.jar,bb-ws-gradebook.jar,bb-ws-user.jar,bb-ws-util.jar,bbpatch-core-2.2.5.jar,blti-sandwich.jar,poi-3.7-20101029.jar,poi-ooxml-3.7-20101029.jar,poi-ooxml-schemas-3.7-20101029.jar,poi-scratchpad-3.7-20101029.jar,chartengineapi.jar,com.ibm.icu_4.0.1.v20090822.jar,commons-cli-1.0.jar,coreapi.jar,dataadapterapi.jar,dteapi.jar,engineapi.jar,flute.jar,modelapi.jar,modelodaapi.jar,odadesignapi.jar,org.eclipse.emf.common_2.5.0.v200906151043.jar,org.eclipse.emf.ecore.xmi_2.5.0.v200906151043.jar,org.eclipse.emf.ecore_2.5.0.v200906151043.jar,scriptapi.jar,commons-beanutils-1.8.0.jar,commons-chain-1.2.jar,commons-codec-1.3.jar,commons-collections-3.2.1.jar,commons-dbcp-1.4.jar,commons-digester-1.8.jar,commons-discovery-0.4.jar,commons-exec-1.0.1.jar,commons-fileupload-1.2.1.jar,commons-httpclient-3.1.jar,commons-io-1.4.jar,commons-lang-2.4.jar,commons-logging-1.1.1.jar,commons-pool-1.4.jar,commons-validator-1.3.1.jar,clover.jar,activemq-core-5.7.0.jar,geronimo-j2ee-management_1.1_spec-1.0.1.jar,geronimo-jms_1.1_spec-1.1.1.jar,geronimo-jta_1.0.1B_spec-1.0.1.jar,kahadb-5.7.0.jar,Merlia.jar,ojdbc6.jar,ucp.jar,postgresql.jdbc4.jar,axis2-adb-1.4.1.jar,axis2-adb-codegen-1.4.1.jar,axis2-ant-plugin-1.4.1.jar,axis2-clustering-1.4.1.jar,axis2-codegen-1.4.1.jar,axis2-corba-1.4.1.jar,axis2-fastinfoset-1.4.1.jar,axis2-java2wsdl-1.4.1.jar,axis2-jaxbri-1.4.1.jar,axis2-jaxws-1.4.1.jar,axis2-jaxws-api-1.4.1.jar,axis2-jibx-1.4.1.jar,axis2-json-1.4.1.jar,axis2-jws-api-1.4.1.jar,axis2-kernel-1.4.1.jar,axis2-metadata-1.4.1.jar,axis2-mtompolicy-1.4.1.jar,axis2-saaj-1.4.1.jar,axis2-saaj-api-1.4.1.jar,axis2-spring-1.4.1.jar,axis2-xmlbeans-1.4.1.jar,annogen-0.1.0.jar,axiom-api-1.2.7.jar,axiom-dom-1.2.7.jar,axiom-impl-1.2.7.jar,backport-util-concurrent.jar,geronimo-annotation_1.0_spec-1.1.jar,geronimo-stax-api_1.0_spec-1.0.1.jar,httpcore-4.0-beta1.jar,httpcore-nio-4.0-beta1.jar,jalopy-1.5rc3.jar,jettison-1.0-RC2.jar,jibx-bind-1.1.5.jar,jibx-run-1.1.5.jar,mex-1.4.1.jar,neethi-2.0.4.jar,soapmonitor-1.4.1.jar,woden-api-1.0M8.jar,woden-impl-dom-1.0M8.jar,wstx-asl-3.2.4.jar,xml-apis-1.3.04.jar,xml-resolver-1.2.jar,xmlbeans-2.3.0.jar,XmlSchema-1.4.2.jar,rampart-core-1.4.jar,rampart-policy-1.4.jar,rampart-trust-1.4.jar,opensaml-1.1.jar,wss4j-1.5.4.jar,xmlsec-1.4.1.jar,hibernate-commons-annotations-4.0.1.Final.jar,hibernate-core-4.1.9.Final.jar,hibernate-jpa-2.0-api-1.0.1.Final.jar,jboss-logging-3.1.2.GA.jar,lucene-core.jar,lucene-demos.jar,lucene-queries.jar,lucene-snowball.jar,bb-bbxythos-install.jar,bb-bbxythos.jar,bb-xythoswebui.jar,chardet.jar,fontbox-1.7.1.jar,hibernate2.jar,jlansrv.jar,pdfbox-1.7.1.jar,jempbox-1.7.1.jar,cglib2.jar,odmg.jar,quartz.jar,WorkflowAuthentication.jar,WorkflowCommon-6.0.7.jar,WorkflowConfig.jar,WorkflowEJB.jar,xss.jar,xsscore.jar,xssmssql.jar,xssoracle.jar","registration.log4j.filename":"/usr/local/blackboard/content/vi/BBLEARN/plugins/Bb-mobile/config/logs/bbdev/Bb-mobile-registration.txt","awt.toolkit":"sun.awt.X11.XToolkit","sun.boot.library.path":"/usr/java/jdk1.7.0_25/jre/lib/amd64","java.vm.name":"Java HotSpot(TM) 64-Bit Server VM","common.loader":"${catalina.base}/lib,${catalina.base}/lib/*.jar,${catalina.home}/lib,${catalina.home}/lib/*.jar","java.home":"/usr/java/jdk1.7.0_25/jre","java.endorsed.dirs":"/usr/java/jdk1.7.0_25/jre/lib/endorsed","wrapper.key":"W5lqd7rZHaiCvw1x","blackboard.wrapper.anchorfile":"/usr/local/blackboard/apps/tomcat/temp/tomcat.anchor","sun.management.compiler":"HotSpot 64-Bit Tiered Compilers","blackboard.home":"/usr/local/blackboard","java.naming.factory.initial":"org.apache.naming.java.javaURLContextFactory","java.runtime.name":"Java(TM) SE Runtime Environment","org.apache.catalina.startup.ContextConfig.jarsToSkip":"bb-caliper-api.jar,bb-cms-taglib.jar,bb-taglibs.jar,bb-wizard.jar,bsf.jar,standard.jar,struts-taglib-*.jar","catalina.base":"/usr/local/blackboard/apps/tomcat","java.util.logging.config.file":"/usr/local/blackboard/config/logging.properties","wrapper.disable_shutdown_hook":"TRUE","java.library.path":"/usr/local/blackboard/apps/service-wrapper/lib:/usr/local/blackboard/apps/tomcat/lib:/usr/local/blackboard/apps/exec/lib:/usr/local/blackboard/apps/oracle-client/lib64:/usr/local/blackboard/apps/sigar/lib","shared.loader":"","file.separator":"/","java.specification.vendor":"Oracle Corporation","java.rmi.server.randomIDs":"true","org.apache.catalina.startup.TldConfig.jarsToSkip":"","java.vm.specification.version":"1.7","server.loader":"","blackboard.shared.home":"/usr/local/blackboard/content","sun.net.inetaddr.ttl":"600","wrapper.native_library":"wrapper.64","sun.java.launcher":"SUN_STANDARD","user.timezone":"Europe/Brussels","wrapper.cpu.timeout":"10","os.name":"Linux","path.separator":":","java.ext.dirs":"/usr/java/jdk1.7.0_25/jre/lib/ext:/usr/java/packages/lib/ext","sun.arch.data.model":"64","java.specification.name":"Java Platform API Specification","os.version":"2.6.32-279.5.2.el6.x86_64","com.sun.management.jmxremote":"","java.class.version":"51.0","user.country":"US","java.vendor":"Oracle Corporation","java.vm.specification.name":"Java Virtual Machine Specification","wrapper.java.pid":"2034","java.specification.version":"1.7","java.naming.factory.url.pkgs":"org.apache.naming"},"startTime":1395439422788,"classPath":"/usr/local/blackboard/apps/service-wrapper/lib/wrapper.jar:/usr/java/jdk1.7.0_25/lib/tools.jar:/usr/local/blackboard/system/build/clover-ant/lib/clover.jar:/usr/local/blackboard/apps/tomcat/bin/bootstrap.jar:/usr/local/blackboard/apps/tomcat/lib/bb-tomcat-bootstrap.jar:/usr/local/blackboard/apps/tomcat/bin/tomcat-juli.jar","managementSpecVersion":"1.2","bootClassPath":"/usr/java/jdk1.7.0_25/jre/lib/resources.jar:/usr/java/jdk1.7.0_25/jre/lib/rt.jar:/usr/java/jdk1.7.0_25/jre/lib/sunrsasign.jar:/usr/java/jdk1.7.0_25/jre/lib/jsse.jar:/usr/java/jdk1.7.0_25/jre/lib/jce.jar:/usr/java/jdk1.7.0_25/jre/lib/charsets.jar:/usr/java/jdk1.7.0_25/jre/lib/jfr.jar:/usr/java/jdk1.7.0_25/jre/classes","specName":"Java Virtual Machine Specification","specVersion":"1.7","uptime":158765180,"vmName":"Java HotSpot(TM) 64-Bit Server VM","vmVendor":"Oracle Corporation","bootClassPathSupported":true,"inputArguments":["-enableassertions","-Dblackboard.wrapper.anchorfile=/usr/local/blackboard/apps/tomcat/temp/tomcat.anchor","-Dblackboard.wrapper.commandfile=/usr/local/blackboard/apps/tomcat/temp/wrapper.command","-Xss400k","-XX:MaxPermSize=512m","-Djava.io.tmpdir=/usr/local/blackboard/apps/tomcat/temp","-Djava.security.manager","-Djava.security.policy=/usr/local/blackboard/apps/tomcat/conf/catalina.policy","-Djava.util.logging.config.file=/usr/local/blackboard/config/logging.properties","-Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager","-Dfile.encoding=UTF-8","-Dcatalina.home=/usr/local/blackboard/apps/tomcat","-Dcatalina.base=/usr/local/blackboard/apps/tomcat","-Dblackboard.home=/usr/local/blackboard","-Dblackboard.shared.home=/usr/local/blackboard/content","-Dbbservices_config=/usr/local/blackboard/config/service-config.properties","-Djava.awt.headless=true","-Dcom.sun.management.jmxremote","-Dorg.apache.jasper.runtime.BodyContentImpl.LIMIT_BUFFER=true","-XX:+HeapDumpOnOutOfMemoryError","-XX:HeapDumpPath=/usr/local/blackboard/logs/tomcat","-Xloggc:/usr/local/blackboard/logs/tomcat/gc.log","-XX:+PrintGCDetails","-XX:+PrintGCDateStamps","-XX:+PrintGCTimeStamps","-Dclover.initstring.basedir=/usr/local/blackboard/system/clover","-XX:StackShadowPages=20","-XX:-UseSplitVerifier","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=2222","-Dsun.net.inetaddr.ttl=600","-XX:+UseConcMarkSweepGC","-XX:+ExplicitGCInvokesConcurrent","-Xms512m","-Xmx512m","-Djava.library.path=/usr/local/blackboard/apps/service-wrapper/lib:/usr/local/blackboard/apps/tomcat/lib:/usr/local/blackboard/apps/exec/lib:/usr/local/blackboard/apps/oracle-client/lib64:/usr/local/blackboard/apps/sigar/lib","-Dwrapper.key=W5lqd7rZHaiCvw1x","-Dwrapper.port=32001","-Dwrapper.jvm.port.min=31000","-Dwrapper.jvm.port.max=31999","-Dwrapper.pid=2032","-Dwrapper.version=3.2.3","-Dwrapper.native_library=wrapper.64","-Dwrapper.service=TRUE","-Dwrapper.disable_shutdown_hook=TRUE","-Dwrapper.cpu.timeout=10","-Dwrapper.jvmid=1"],"specVendor":"Oracle Corporation","libraryPath":"/usr/local/blackboard/apps/service-wrapper/lib:/usr/local/blackboard/apps/tomcat/lib:/usr/local/blackboard/apps/exec/lib:/usr/local/blackboard/apps/oracle-client/lib64:/usr/local/blackboard/apps/sigar/lib","jreVersion":"1.7.0_25","vmVersion":"23.25-b01","name":"2034@bbdev"},"osInfo":{"osName":"Linux","osVersion":"2.6.32-279.5.2.el6.x86_64","availableProcessors":1,"osArch":"amd64"},"app":"BbRest","version":"1.0.0-SNAPSHOT","time":1395598187968};
       console.log(info);
       $scope.info = info;
       
}]);