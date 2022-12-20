INSERT INTO trainers (age,
                      email,
                      kvk_number,
                      name,
                      phone_number,
                      years_of_experience)
VALUES ('18','marloes@email.com','26347542','marloes','0645234567','2'),
       ('24','gabrielle@email.com','25632812','gabrielle','0645234875','1'),
       ('20','margo@email.com','25432353','margo','0634532456','5');


INSERT INTO athletes (age,
                      email,
                      name,
                      phone_number,
                      start_weight,
                      target_weight,
                      trainer_id)
VALUES ('16','trevor@email.com','trevor','0654362175','80.00','91.00','1'),
       ('18','pim@email.com','pim','0654363476','55.70','80.00','1'),
       ('30','samanta@email.com','samanta','0654095643','94.00','60.00','2'),
       ('45','jan@email.com','teresa','0698753640','87.00','70.00','3');


INSERT INTO exercises (id,
                       calorie,
                       name,
                       repetitions,
                       sets,
                       target_muscle,
                       weight)
VALUES (1,'10','exerciseone','10','3','back','23'),
       (2,'15','exercisetwo','12','3','chest','15'),
       (3,'30','exercisethree','12','3','biceps','10');

INSERT INTO routines (id,
                      description,
                      difficulty_level,
                      duration_in_weeks,
                      name)
VALUES ('1','a routine','easy','3','routineone'),
       ('2','a routine','mild','3','routinetwo'),
       ('3','a routine','hard','3','routinethree');

INSERT INTO routine_exercises(routine_id,
                              exercise_id)
VALUES ('1','3'),
       ('1','1'),
       ('1','2'),
       ('2','2'),
       ('2','1'),
       ('3','3'),
       ('3','2');

INSERT INTO users (username, password)
VALUES ('gabrielle', '$2a$12$JCHbUV6Gy/d0fZiODfPqE.ERVvMtXmZT3Hx6Wzd0ti/daWzvjtTt6'),
       ('bas', '$2a$12$j3Lz996X8OV0MNBzq.a/POmvVm/A.VKsg./2WxFEthlBdVjKap39C'),
       ('jan', '$2a$12$iplmHdqUfpx2uEicVGQ.mect.1k2BCm9Xmv.hDgN3Zy5n.PabQu4u'),
       ('matheus', '$2a$12$2NWANZbVSfVUUIUdcMl0K.F0nTDSqtjScBZWzEoEz/qOP712G0gRm');

INSERT INTO authorities (id,
                         username,
                         authority)
VALUES (01, 'gabrielle', 'ROLE_DEFAULT'),
       (02, 'bas', 'ROLE_ATHLETE'),
       (03, 'jan', 'ROLE_TRAINER'),
       (04, 'matheus', 'ROLE_ADMIN');
