# Use a imagem oficial do MySQL
FROM mysql:8.0

# Defina variáveis de ambiente para configurar o MySQL
ENV MYSQL_ROOT_PASSWORD=root_password
ENV MYSQL_DATABASE=financeiro
ENV MYSQL_USER=usuario
ENV MYSQL_PASSWORD=senha

# Exponha a porta padrão do MySQL
EXPOSE 3307

# Copie um arquivo SQL para inicializar o banco de dados (opcional)
# Se você tiver um script SQL para criar tabelas ou popular o banco, coloque-o no diretório /docker-entrypoint-initdb.d/
# Ele será executado automaticamente na inicialização do container
COPY ./init.sql /docker-entrypoint-initdb.d/

# Comando padrão para iniciar o MySQL
CMD ["mysqld"]