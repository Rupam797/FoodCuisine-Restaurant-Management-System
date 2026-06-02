# ==========================================
# STAGE 1: Compile the Java Application
# ==========================================
FROM tomcat:9.0-jdk11-openjdk-slim AS builder

WORKDIR /app

# Copy source code and web resources
COPY src /app/src
COPY web /app/web

# Create classes directory
RUN mkdir -p /app/web/WEB-INF/classes

# Download required Java EE libraries provided by Glassfish but missing in Tomcat
ADD https://repo1.maven.org/maven2/com/sun/mail/javax.mail/1.6.2/javax.mail-1.6.2.jar /app/web/WEB-INF/lib/
ADD https://repo1.maven.org/maven2/javax/activation/activation/1.1.1/activation-1.1.1.jar /app/web/WEB-INF/lib/
ADD https://repo1.maven.org/maven2/javax/servlet/jstl/1.2/jstl-1.2.jar /app/web/WEB-INF/lib/

# Compile Java classes with dependency classpath
RUN javac -d /app/web/WEB-INF/classes \
          -cp "/usr/local/tomcat/lib/servlet-api.jar:/usr/local/tomcat/lib/jsp-api.jar:/app/web/WEB-INF/lib/*" \
          $(find /app/src/java -name "*.java")

# Copy db.properties and other non-java resources into the classpath directory
RUN cd /app/src/java && find . -type f ! -name "*.java" -exec cp --parents {} /app/web/WEB-INF/classes/ \;


# ==========================================
# STAGE 2: Deploy Exploded WAR to Tomcat
# ==========================================
FROM tomcat:9.0-jre11-openjdk-slim

# Clear default Tomcat apps to serve ours as the root context
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy built application directories
COPY --from=builder /app/web /usr/local/tomcat/webapps/ROOT

# Expose Tomcat default port
EXPOSE 8080

CMD ["catalina.sh", "run"]
