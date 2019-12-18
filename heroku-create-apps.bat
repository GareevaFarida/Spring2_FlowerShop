
heroku apps:create farida-admin-ui
heroku addons:create heroku-postgresql:hobby-dev --app farida-admin-ui
heroku config:set JDBC_DRIVER_CLASS=org.postgresql.Driver HIBERNATE_DIALECT=org.hibernate.dialect.PostgreSQLDialect --app farida-admin-ui
