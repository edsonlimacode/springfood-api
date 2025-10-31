
delete from cities;
delete from states;
delete from groups;
delete from users;
delete from permissions;
delete from oauth2_registered_client;
delete from kitchens;

INSERT INTO public.states (id,name) VALUES(1,'ceara');
INSERT INTO public.cities (name,state_id) VALUES('várzea alegre',1);

INSERT INTO public.payments (description) VALUES ('Pix'),('cartão de crédito/débito'),('dinheiro'),('Outro');

INSERT INTO groups ("id","name") VALUES (1,'MASTER'),(2,'ADMIN');

INSERT INTO public.users (id, email, "name", "password", created_at, updated_at) VALUES(1, 'master@gmail.com', 'master', '$2a$10$SlAz/gLH0gMT7TSh5k8HAOiobIodOUwH2YPgLL0Z4sBFqOiE0QH6u', '2025-10-31 12:28:52.346', '2025-10-31 12:28:52.346');
INSERT INTO public.users (id, email, "name", "password", created_at, updated_at) VALUES(2, 'admin@gmail.com', 'amdin', '$2a$10$6hDUaA0to0LSREiuEAInEuthvn.QUza41Pg.SWc8iCPDEnMvUCnl.', '2025-10-31 12:29:27.464', '2025-10-31 12:29:27.464');
INSERT INTO public.users (id, email, "name", "password", created_at, updated_at) VALUES(3, 'cliente@gmail.com', 'cliente', '$2a$10$Fslnp1F4LibksmvlLmkFtOh.G67lDcxhrmAMUKz9e1wqf75LiYVFe', '2025-10-31 12:30:00.435', '2025-10-31 12:30:00.435');

INSERT INTO permissions (id,description, "name") VALUES (1,NULL, 'MASTER'), (2,NULL, 'ADMIN');

INSERT INTO group_permission (group_id, permission_id) VALUES(1, 1),(2, 2);
INSERT INTO user_group (user_id, group_id) VALUES(1, 1),(2, 2);

INSERT INTO kitchens ("name") VALUES ('brasileira'),('italiana'),('japonesa'),('mexicana'),('vegana');

INSERT INTO oauth2_registered_client (id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name, post_logout_redirect_uris, client_authentication_methods, authorization_grant_types, redirect_uris, scopes, client_settings, token_settings) VALUES('3', 'foodapi-web2', '2025-10-27 12:09:25.505', '$2a$10$hRqCIl9vuRsSESw.B6qGbukEb0lbtn0KNRBfVUxtk1xkxNiXlz7HK', NULL, '3', '', 'client_secret_basic', 'refresh_token,authorization_code', 'http://localhost:3000/api/auth/authorized,http://localhost:8080/swagger-ui/oauth2-redirect.html', '', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":false,"settings.token.x509-certificate-bound-access-tokens":false,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",2592000.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",1296000.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000],"settings.token.device-code-time-to-live":["java.time.Duration",300.000000000]}');