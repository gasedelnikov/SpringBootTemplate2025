SELECT 'CREATE DATABASE core'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'core')
\gexec
GRANT ALL PRIVILEGES ON DATABASE core TO postgres;