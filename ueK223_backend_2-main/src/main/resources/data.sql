
--USERS
insert into users (id, email, first_name, last_name, password)
values ('ba804cb9-fa14-42a5-afaf-be488742fc54', 'admin@example.com', 'James', 'Bond',
        '$2a$10$TM3PAYG3b.H98cbRrHqWa.BM7YyCqV92e/kUTBfj85AjayxGZU7d6'), -- Password: 1234
       ('0d8fa44c-54fd-4cd0-ace9-2a7da57992de', 'user@example.com', 'Tyler', 'Durden',
        '$2a$10$TM3PAYG3b.H98cbRrHqWa.BM7YyCqV92e/kUTBfj85AjayxGZU7d6')  -- Password: 1234
ON CONFLICT DO NOTHING;


--ROLES
INSERT INTO role(id, name)
VALUES ('d29e709c-0ff1-4f4c-a7ef-09f656c390f1', 'DEFAULT'),
       ('ab505c92-7280-49fd-a7de-258e618df074', 'ADMIN'),
       ('c6aee32d-8c35-4481-8b3e-a876a39b0c02', 'USER')
ON CONFLICT DO NOTHING;

--AUTHORITIES
INSERT INTO authority(id, name)
VALUES ('2ebf301e-6c61-4076-98e3-2a38b31daf86', 'DEFAULT'),
       ('76d2cbf6-5845-470e-ad5f-2edb9e09a868', 'USER_MODIFY'),
       ('21c942db-a275-43f8-bdd6-d048c21bf5ab', 'USER_DELETE'),

       ('cdab1722-b887-4fef-8e9b-c94c56b4fa32', 'BLOGPOST_CREATE'),
       ('d62a5601-8e8f-49e3-a59e-6d8b5db40c41', 'BLOGPOST_UPDATE'),
       ('b258a1ad-84b6-44d0-8a63-c3ffa6f944c5', 'BLOGPOST_DELETE'),

       ('282db1ed-0ca1-4fd0-898a-95b7291f9784', 'BLOGPOST_UPDATE_ALL'),
       ('f103ca40-d033-4f78-b105-a8a0ef7f0c5a', 'BLOGPOST_DELETE_ALL')




ON CONFLICT DO NOTHING;

--assign roles to users
insert into users_role (users_id, role_id)
values ('0d8fa44c-54fd-4cd0-ace9-2a7da57992de', 'd29e709c-0ff1-4f4c-a7ef-09f656c390f1'),
       ('ba804cb9-fa14-42a5-afaf-be488742fc54', 'ab505c92-7280-49fd-a7de-258e618df074')

ON CONFLICT DO NOTHING;

--assign authorities to roles
INSERT INTO role_authority(role_id, authority_id)
VALUES ('d29e709c-0ff1-4f4c-a7ef-09f656c390f1', '2ebf301e-6c61-4076-98e3-2a38b31daf86'),
       ('ab505c92-7280-49fd-a7de-258e618df074', '76d2cbf6-5845-470e-ad5f-2edb9e09a868'),
       ('c6aee32d-8c35-4481-8b3e-a876a39b0c02', '21c942db-a275-43f8-bdd6-d048c21bf5ab'),

       -- Admin
       ('ab505c92-7280-49fd-a7de-258e618df074', 'cdab1722-b887-4fef-8e9b-c94c56b4fa32'),
       ('ab505c92-7280-49fd-a7de-258e618df074', 'd62a5601-8e8f-49e3-a59e-6d8b5db40c41'),
       ('ab505c92-7280-49fd-a7de-258e618df074', 'b258a1ad-84b6-44d0-8a63-c3ffa6f944c5'),
       ('ab505c92-7280-49fd-a7de-258e618df074', '282db1ed-0ca1-4fd0-898a-95b7291f9784'),
       ('ab505c92-7280-49fd-a7de-258e618df074', 'f103ca40-d033-4f78-b105-a8a0ef7f0c5a'),
       -- User
       ('c6aee32d-8c35-4481-8b3e-a876a39b0c02', 'cdab1722-b887-4fef-8e9b-c94c56b4fa32'),
       ('c6aee32d-8c35-4481-8b3e-a876a39b0c02', 'd62a5601-8e8f-49e3-a59e-6d8b5db40c41'),
       ('c6aee32d-8c35-4481-8b3e-a876a39b0c02', 'b258a1ad-84b6-44d0-8a63-c3ffa6f944c5')


ON CONFLICT DO NOTHING;

INSERT INTO blogpost(id, title, description, category, id_author)
VALUES ('1fd5b937-67fc-4842-b113-9149f95463e5', 'The Lost Ermal', 'Ermal is lost in a city.', 0, 'ba804cb9-fa14-42a5-afaf-be488742fc54'),
       (gen_random_uuid(), 'The Lost Anik', 'Anik is lost in a forest.', 0, '0d8fa44c-54fd-4cd0-ace9-2a7da57992de'),
       (gen_random_uuid(), 'The Lost Kenneth', 'Kenneth is lost in a city.', 0, 'ba804cb9-fa14-42a5-afaf-be488742fc54')