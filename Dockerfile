# FROM maven:3.8.2-jdk-11

# WORKDIR /home

# COPY . .

# RUN mvn clean package -DskipTests

# COPY ./entrypoint.sh ./entrypoint.sh

# RUN chmod +x ./entrypoint.sh
# EXPOSE 8080
# CMD bash ./entrypoint.sh
# Use a imagem oficial do Oracle
FROM container-registry.oracle.com/database/enterprise:19.3.0.0


# Configurações de ambiente (substitua conforme necessário)
ENV ORACLE_SID=PnlDeCtrl
ENV ORACLE_PDB=PainelDeControleDB
ENV ORACLE_PWD=oracle

# Copia os arquivos SQL para o container
COPY oracle-init.sql /docker-entrypoint-initdb.d/
